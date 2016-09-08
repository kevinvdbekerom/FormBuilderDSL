package org.uva.sea.ql.experiment;

import org.uva.sea.ql.ast.form.ReachableQuestionsMap;

public abstract class VisibilityObserver {
	protected ReachableQuestionsMap questionReachabilityMap;
	
	public abstract void update();
}
