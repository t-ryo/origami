package blue.origami.ocode;

import blue.origami.lang.OEnv;

import java.util.ArrayList;
import java.util.List;

public class OTypeInfer {

    public OEnv env;
    public OCode code;
    private List<ConstraintEquation> constraint;

	public void getConstraints(OTypeInfer infer) {
        //制約を手にいれる関数(各Ocodeに対する)，infer
        //型を取得する(各Ocodeに対する型を返すメソッドから, :: env-> [String])
        infer.constraint.add();
	}

	public void typeInfer(OEnv env, OCode node) {
		this.env = env;
        this.code = node;
        this.constraint = new ArrayList<ConstraintEquation>();
        this.getConstraints(this);
        //この段階でconstraintsが出来上がっている．
	}

    public static class ConstraintEquation {
        List<String> lefthand;
        List<String> righthand;
    }
}
