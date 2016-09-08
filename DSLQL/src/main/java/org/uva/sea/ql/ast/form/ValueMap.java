package org.uva.sea.ql.ast.form;

import java.util.HashMap;
import java.util.Map;

import org.uva.sea.ql.value.Value;

public class ValueMap {
	private Map<String, Value> valueMap;
	
	public ValueMap() {
		valueMap = new HashMap<String, Value>();
	}
	
	public void putValueInMap(String ID, Value value) {
		valueMap.put(ID, value);
	}
	
	public void updateValueInMap(String ID, Value value) {
		valueMap.put(ID, value);
	}
	
	public Value getValueFromMap(String questionID) {
		if (valueMap.containsKey(questionID)) {
			return valueMap.get(questionID);
		} else {
			return null; //TODO refactor this into something more meaningfull!
		}
	}
	
	public Map<String, Value> getValueMap() {
		return this.valueMap;
	}
	
}
