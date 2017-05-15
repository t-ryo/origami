/***********************************************************************
 * Copyright 2017 Kimio Kuramitsu and ORIGAMI project
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ***********************************************************************/

package blue.origami.ocode;

import blue.origami.lang.OEnv;

import java.util.List;

public class AndCode extends OParamCode<Void> {

	public AndCode(OEnv env, OCode left, OCode right) {
		super(null, env.t(boolean.class), new OCode[] { left, right });
	}

	@Override
	public Object eval(OEnv env) throws Throwable {
		Boolean b = (Boolean) this.getParams()[0].eval(env);
		if (b) {
			return this.getParams()[1].eval(env);
		}
		return b;
	}

	@Override
	public void generate(OGenerator gen) {
		gen.pushAnd(this);
	}

    @Override
    public void getConstraints(OTypeInfer infer) {
        List<String> tyOfLeft = null;// Bool -> Int == [Bool,Int]
        List<String> tyOfRight = null;

        //inversion
        for (int i = 0; i < 1; i++) {
            tyOfLeft.add(this.getParams()[i].getType().getLocalName());
            tyOfRight.add("Bool");
            infer.addConstraintEquation(tyOfLeft,tyOfRight);
            tyOfLeft.clear();
            tyOfRight.clear();
        }
        // typing
        tyOfLeft.add(this.getType().getLocalName());
        tyOfRight.add("Bool");
        infer.addConstraintEquation(tyOfLeft,tyOfRight);
    }
}
