package org.uva.sea.ql.gui;

import org.uva.sea.ql.ast.form.ValueMap;

public abstract class FormObserver {
	protected FormDataManager dataManager;
	
	public abstract void update(ValueMap valueMap);
	public abstract void update();
}
