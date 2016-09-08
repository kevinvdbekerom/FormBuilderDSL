package org.uva.sea.ql.value;

public class UndefinedValue extends Value {

	@Override
	public Object getValue() {
		return this;
	}
	
	@Override
	public boolean equals(Object value) {
		return value instanceof UndefinedValue;
	}
	
	@Override
	public String toString() {
		return "undefined value";
	}

}
