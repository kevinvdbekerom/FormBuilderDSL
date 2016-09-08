package org.uva.sea.ql.type;

import org.uva.sea.ql.value.IntValue;
import org.uva.sea.ql.value.UndefinedValue;
import org.uva.sea.ql.value.Value;

public class IntType extends Type {
	
	@Override
	public boolean equals(Object t) {
		return t instanceof IntType;
	}
	
	@Override
	public String toString() {
		return "Integer";
	}

	@Override
	public Value parse(String message) {
		if (message.matches("^-?\\d+$")) {
			return new IntValue(Integer.parseInt(message));
		} else {
			return new UndefinedValue();
		}
		
	}
	
}
