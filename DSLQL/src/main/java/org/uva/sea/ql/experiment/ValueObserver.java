package org.uva.sea.ql.experiment;

import java.util.Map;

public abstract class ValueObserver {
	protected DataObject subject;
	
	public abstract void update(Map<String, String> dataMap);
}
