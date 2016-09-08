package org.uva.sea.ql.value;

public class StrValue extends Value {
	private String value;
	
	public StrValue(String value) {
		this.value = value;
	}
	
	@Override
	public String getValue() {
		return this.value;
	}

}
