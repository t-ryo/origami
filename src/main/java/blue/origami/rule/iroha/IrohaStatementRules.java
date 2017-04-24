package blue.origami.rule.iroha;

import java.util.ArrayList;

import blue.nez.ast.Tree;
import blue.origami.asm.OAnno;
import blue.origami.ffi.OImportable;
import blue.origami.lang.OEnv;
import blue.origami.lang.OMethodHandle;
import blue.origami.lang.OUntypedMethod;
import blue.origami.lang.type.OType;
import blue.origami.lang.type.OUntypedType;
import blue.origami.ocode.EmptyCode;
import blue.origami.ocode.OCode;
import blue.origami.rule.TypeRule;
import blue.origami.util.OTypeRule;

public class IrohaStatementRules implements OImportable {
	public OTypeRule IFuncDecl = new TypeRule() {
		@Override
		public OCode typeRule(OEnv env, Tree<?> t) {
			OAnno anno = this.parseAnno(env, "public,static,final", t.get(_anno, null));
			String name = t.getStringAt(_name, null);
			String[] paramNames = this.parseParamNames(env, t.get(_param, null));
			// modified default type
			OType[] paramTypes = this.parseParamTypes(env, paramNames, t.get(_param, null), env.t(OUntypedType.class));
			OType returnType = this.parseType(env, t.get(_type, null), env.t(OUntypedType.class));

			// type inference
			boolean unType = false;
			for (OType param : paramTypes) {
				if (param.equals(OUntypedType.class)) {
					unType = true;
					break;
				}
			}
			if (unType == true) {
				paramTypes = this.typeChecker(env, t.get(_body, null), paramNames, paramTypes);
			}

			OType[] exceptions = this.parseExceptionTypes(env, t.get(_throws, null));
			OCode body = this.parseFuncBody(env, t.get(_body, null));
			OMethodHandle mh = OUntypedMethod.newFunc(env, anno, returnType, name, paramNames, paramTypes, exceptions,
					body);
			this.defineName(env, t, mh);
			return new EmptyCode(env);
		}

		public OType[] typeChecker(OEnv env, Tree<?> body, String[] paramNames, OType[] paramTypes) {
			ArrayList<String[]> tList = this.getTypeExpr(env, body);
			return this.evalTypeExpr(tList, paramNames);
		}

		public ArrayList<String[]> getTypeExpr(OEnv env, Tree<?> body) {
			ArrayList<String[]> tList = new ArrayList<>();
			for (Tree<?> sub : body) {
				// load(body,tList)

				// OType IntExpr = env.t(int.class);

				// NameExpr ->
				// if tList.has(name) return Otype
				// else return name
				// TODO String[] -> something can be search

				// AddExpr -> tList.add(load(left),load(right))
				// TODO decision key - value
			}
			return tList;
		}

		public OType[] evalTypeExpr(ArrayList<String[]> tList, String[] paramNames) {
			return null;
		}
	};
}
