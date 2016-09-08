package org.uva.sea.ql.ast.form;

import org.uva.sea.ql.type.*;

import java.util.HashMap;
import java.util.Map;

public class TypeMap {
	private Map<String, Type> questionTypes;
	
	public TypeMap() {
		questionTypes = new HashMap<String, Type>();
	}
	
	public void putQuestionType(String key, Type value) {
		questionTypes.put(key, value);
	}
	
	public Type getTypeFromQuestion(String questionID) {
		if (questionTypes.containsKey(questionID)) {
			return questionTypes.get(questionID);
		} else {
			return new Undefined();
		}
		
	}
	
}
