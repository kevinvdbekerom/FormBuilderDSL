package org.uva.sea.ql.ast.form;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReachableQuestionsMap {
	private Map<String, Boolean> questionReachabilityMap;
	
	public ReachableQuestionsMap(List<String> keys) {
		questionReachabilityMap = new HashMap<String, Boolean>();
		
		for (String key : keys) {
			questionReachabilityMap.put(key, false);
		}
		
	}
	
	public void putValueInMap(String ID, Boolean reachable) {
		questionReachabilityMap.put(ID, reachable);
	}
	
	public void updateValueInMap(String ID, Boolean reachable) {
		questionReachabilityMap.put(ID, reachable);
	}
	
	public Boolean getValueFromMap(String questionID) {
		if (questionReachabilityMap.containsKey(questionID)) {
			return questionReachabilityMap.get(questionID);
		} else {
			return null; //TODO refactor this into something more meaningfull!
		}
	}
	
	public Map<String, Boolean> getValueMap() {
		return this.questionReachabilityMap;
	}
	
	public void setEveryValueToFalse() {
		for (String key : questionReachabilityMap.keySet()) {
			questionReachabilityMap.put(key, false);
		}
	}
	
}
