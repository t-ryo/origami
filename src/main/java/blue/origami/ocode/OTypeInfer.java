package blue.origami.ocode;

import java.util.ArrayList;
import java.util.List;

import blue.origami.lang.OEnv;

public class OTypeInfer {

	public OEnv env;
	public OCode code;
	private List<String[][]> constraint;

	public OTypeInfer(OEnv env, OCode node) {
		this.env = env;
		this.code = node;
		this.constraint = new ArrayList<>();
		node.getConstraints(this);
		// この段階でconstraintsが出来上がっている．
	}

	public void addConstraintEquation(List<String[][]> typelist) {
		for (String[][] rest : typelist) {
			this.constraint.add(rest);
		}
	}

	// public static class ConstraintEquation {
	// List<String> lefthand;
	// List<String> righthand;
	// }

}
