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

package origami.rule.java;

import origami.code.OCode;
import origami.code.OMultiCode;
import origami.code.OSugarCode;
import origami.code.OWhileCode;
import origami.lang.OEnv;

public class JavaForCode extends OSugarCode {

	public JavaForCode(OEnv env, OCode initCode, OCode condCode, OCode nextCode, OCode bodyCode) {
		super(env, env.t(void.class), initCode, condCode, nextCode, bodyCode);
	}

	public OCode initCode() {
		return this.nodes[0];
	}

	public OCode condCode() {
		return this.nodes[1];
	}

	public OCode nextCode() {
		return this.nodes[2];
	}

	public OCode bodyCode() {
		return this.nodes[3];
	}

	@Override
	public OCode desugar() {
		OCode whileCode = new OWhileCode(this.env(), this.condCode(), this.nextCode(), this.bodyCode());
		return new OMultiCode(this.initCode(), whileCode);
	}
}
