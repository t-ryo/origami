package blue.origami.transpiler;

import java.util.Arrays;

import blue.origami.nez.ast.Tree;
import blue.origami.transpiler.code.Code;
import blue.origami.transpiler.code.FuncRefCode;
import blue.origami.transpiler.rule.NameExpr.NameInfo;
import blue.origami.transpiler.type.Ty;

public class CodeTemplate extends Template implements NameInfo {

	protected final String template;

	public CodeTemplate(String name, Ty returnType, Ty[] paramTypes, String template) {
		super(name, returnType, paramTypes);
		this.template = template;
	}

	public CodeTemplate(String template) {
		this(template, Ty.tVoid, TArrays.emptyTypes, template);
	}

	@Override
	public String getDefined() {
		return this.template;
	}

	@Override
	public boolean isAbstract() {
		return this.template.length() == 0;
	}

	public void nomAll() {
		this.paramTypes = Arrays.stream(this.paramTypes).map(x -> x.finalTy()).toArray(Ty[]::new);
		this.returnType = this.getReturnType().finalTy();
	}

	@Override
	public String format(Object... args) {
		return String.format(this.template, args);
	}

	@Override
	public String toString() {
		return super.toString() + "=" + this.template;
	}

	@Override
	public boolean isNameInfo(TEnv env) {
		return true;
	}

	@Override
	public Code newCode(TEnv env, Tree<?> s) {
		return new FuncRefCode(this.name, this).setSource(s);
	}

}