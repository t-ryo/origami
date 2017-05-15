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

package blue.origami.lang.type;

import java.util.HashMap;
import java.util.Map;

public class OUntypedType extends OTypeSystemType {

    public static Map<String,String> term2typeTable = new HashMap<String,String>();
    public static String typeVariableNameSpace = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm";
    private static int typeVariableNameSpaceIndex = typeVariableNameSpace.length();

	OUntypedType(OTypeSystem ts) {
		super(ts);
	}

	@Override
	public Class<?> unwrap() {
		return Object.class;
	}

	@Override
	public String getLocalName() {return typeVariableNameSpace.substring(--typeVariableNameSpaceIndex);}

	@Override
	public boolean isUntyped() {
		return true;
	}

	@Override
	public void typeDesc(StringBuilder sb, int levelGeneric) {
		sb.append("Ljava/lang/Object;");
	}

}
