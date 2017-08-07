package blue.origami.transpiler.code;

import blue.origami.transpiler.TCodeSection;
import blue.origami.transpiler.TEnv;
import blue.origami.transpiler.Ty;
import blue.origami.transpiler.Template;
import blue.origami.util.StringCombinator;

public class TemplateCode extends CodeN {

	public TemplateCode(Code... codes) {
		super(Ty.tString, codes);
	}

	@Override
	public Template getTemplate(TEnv env) {
		return env.getTemplate("String", "\"%s\"");
	}

	@Override
	public String strOut(TEnv env) {
		return "TODO"; // this.getTemplate(env).format(this.value);
	}

	@Override
	public void emitCode(TEnv env, TCodeSection sec) {
		sec.pushTemplate(env, this);
	}

	@Override
	public void strOut(StringBuilder sb) {
		sb.append("(");
		for (int i = 0; i < this.args.length; i++) {
			if (i > 1) {
				sb.append("+");
			}
			StringCombinator.append(sb, this.args[i]);
		}
		sb.append(")");
	}

}