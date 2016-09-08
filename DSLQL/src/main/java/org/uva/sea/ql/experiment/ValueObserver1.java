package org.uva.sea.ql.experiment;

import org.uva.sea.ql.ast.form.ValueMap;
import org.uva.sea.ql.gui.FormDataManager;

public abstract class ValueObserver1 {
	protected FormDataManager subject;
	
	public abstract void update(ValueMap valueMap);
}
