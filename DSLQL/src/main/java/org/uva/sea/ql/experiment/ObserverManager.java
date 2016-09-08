package org.uva.sea.ql.experiment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ObserverManager {
	private List<ValueObserver> observers;
	
	public ObserverManager() {
		observers = new ArrayList<ValueObserver>();
	}
	
	public void attach(ValueObserver observer) {
		observers.add(observer);
	}
	
	public List<ValueObserver> getObservers() {
		return this.observers;
	}
	
	public void notifyAllObservers(Map<String, String> dataMap) {
		for (ValueObserver o : observers) {
			o.update(dataMap);
		}
	}

}
