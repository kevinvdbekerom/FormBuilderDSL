package org.uva.sea.ql.experiment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataObject {
	Map<String, String> dataMap = new HashMap<String, String>();
	private List<ValueObserver> observers = new ArrayList<ValueObserver>();
	
	public void updateState(String key, String value) {
		dataMap.put(key, value);
		for (String s : dataMap.keySet()) {
			System.out.println(s + " : " + dataMap.get(s));
		}
		notifyAllObservers();
	}
	
	public void attach(ValueObserver observer) {
		observers.add(observer);
	}
	
	public void notifyAllObservers() {
		for (ValueObserver o : observers) {
			o.update(dataMap);
		}
	}
}
