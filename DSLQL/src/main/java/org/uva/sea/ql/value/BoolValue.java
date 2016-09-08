package org.uva.sea.ql.value;

public class BoolValue extends Value {
	private boolean value;
	
	public BoolValue(boolean value) {
		this.value = value;
	}
	
	@Override
	public Boolean getValue() {
		return this.value;
	}

}
