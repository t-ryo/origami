package blue.origami.transpiler.code;

import blue.origami.transpiler.CodeTemplate;
import blue.origami.transpiler.TCodeSection;
import blue.origami.transpiler.TEnv;
import blue.origami.transpiler.Template;
import blue.origami.transpiler.type.Ty;
import blue.origami.util.OStrings;

public class CastCode extends Code1 implements CallCode {
	public CastCode(Ty ret, Template tp, Code inner) {
		super(ret, inner);
		this.setTemplate(tp);
		// ODebug.trace("CAST *****(%s => %s) %s", inner.getType(), ret, inner);
	}

	public CastCode(Ty ret, Code inner) {
		this(ret, null, inner);
	}

	private Template tp;

	public void setTemplate(Template tp) {
		this.tp = tp;
	}

	@Override
	public Template getTemplate() {
		return this.tp;
	}

	@Override
	public Code asType(TEnv env, Ty ret) {
		if (this.tp == null) {
			Code in = this.getInner().asType(env, this.getType());
			// ODebug.trace("casting %s %s %s => %s => %s",
			// in.getClass().getSimpleName(), in, in.getType(),
			// this.getType(), ret);
			return in.castType(env, ret);
		}
		return this.castType(env, ret);
	}

	@Override
	public void emitCode(TEnv env, TCodeSection sec) {
		sec.pushCast(env, this);
	}

	@Override
	public void strOut(StringBuilder sb) {
		sb.append("(");
		OStrings.append(sb, this.getType());
		sb.append(")");
		OStrings.append(sb, this.getInner());
	}

	// constants
	public static final int SAME = 0;
	public static final int BESTCAST = 1;
	public static final int CAST = 3;
	public static final int BESTCONV = 8;
	public static final int CONV = 12;
	public static final int BADCONV = 64;
	public static final int STUPID = 256;

	public static class TConvTemplate extends CodeTemplate {

		public static final TConvTemplate Stupid = new TConvTemplate("stupid", Ty.tVoid, Ty.tVoid, STUPID, "%s");

		public TConvTemplate(String name, Ty fromType, Ty returnType, int mapCost, String template) {
			super(name, returnType, new Ty[] { fromType }, template);
			this.setMapCost(mapCost);
		}

	}

	public static class MutableCode extends CastCode {

		public MutableCode(Code inner) {
			super(null, inner);
		}

	}

	public static class BoxCastCode extends CastCode {

		public BoxCastCode(Ty ret, Code inner) {
			super(ret, null, inner);
		}

	}

	public static class UnboxCastCode extends CastCode {

		public UnboxCastCode(Ty ret, Code inner) {
			super(ret, null, inner);
		}

	}

	public static class FuncCastCode extends CastCode {

		public FuncCastCode(Ty ty, Template tp, Code inner) {
			super(ty, tp, inner);
		}

	}

}