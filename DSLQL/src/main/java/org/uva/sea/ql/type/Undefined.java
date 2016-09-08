package org.uva.sea.ql.type;

import org.uva.sea.ql.value.Value;

public class Undefined extends Type {

	@Override
	public boolean equals(Object t) {
		return t instanceof Undefined;
	}

	@Override
	public String toString() {
		return "Undefined";
	}

	@Override
	public Value parse(String message) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
