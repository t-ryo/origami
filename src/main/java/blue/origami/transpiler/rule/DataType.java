package blue.origami.transpiler.rule;

import blue.origami.nez.ast.Tree;
import blue.origami.transpiler.TEnv;
import blue.origami.transpiler.TFmt;
import blue.origami.transpiler.TNameHint;
import blue.origami.transpiler.Ty;
import blue.origami.transpiler.code.Code;
import blue.origami.transpiler.code.ErrorCode;
import blue.origami.transpiler.code.TypeCode;

public class DataType implements ParseRule {
	boolean isMutable = true;

	public DataType() {
		this(true);
	}

	public DataType(boolean isMutable) {
		this.isMutable = isMutable;
	}

	@Override
	public Code apply(TEnv env, Tree<?> t) {
		String[] names = new String[t.size()];
		int c = 0;
		for (Tree<?> sub : t) {
			String name = sub.getString();
			TNameHint hint = env.findNameHint(env, name);
			if (hint == null) {
				throw new ErrorCode(sub, TFmt.undefined_name__YY0, name);
			}
			names[c] = name;
			c++;
		}
		return new TypeCode(this.isMutable ? Ty.tMRecord(names) : Ty.tImRecord(names));
	}
}