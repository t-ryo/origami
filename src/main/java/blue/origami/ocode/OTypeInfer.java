package blue.origami.ocode;

import blue.origami.lang.OEnv;

public class OTypeInfer {

	public void getTypeRule(OEnv env, OCode code) {

	}

	public void typeInfer(OEnv env, OCode node) {
		this.getTypeRule(env, node);
	}

}
