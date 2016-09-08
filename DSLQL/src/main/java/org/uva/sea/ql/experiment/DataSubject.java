package org.uva.sea.ql.experiment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataSubject extends ValueObserver {
	
	ObserverManager manager;
	private String id;
	public List<DataSubject> subjects;
	
	public DataSubject(String id, DataObject subject) {
		this.subject = subject;
		manager = new ObserverManager();
		subjects = new ArrayList<DataSubject>();
		this.id = id;
	}
	
	@Override
	public void update(Map<String, String> dataMap) {
		String oldValue = dataMap.get(id);
		String newValue = "";

		//update state with new knowledge
		for (DataSubject s : this.subjects) {
			String str = dataMap.get(s.getID());
			newValue += str;
		}
		
		if (! newValue.equals(oldValue) && !newValue.isEmpty()) {
			dataMap.put(id, newValue);
			subject.updateState(id, newValue);
		} 

	}
	
	public String getID() {
		return this.id;
	}

}
