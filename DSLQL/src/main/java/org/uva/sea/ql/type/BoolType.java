package org.uva.sea.ql.type;

import org.uva.sea.ql.value.BoolValue;
import org.uva.sea.ql.value.Value;

public class BoolType extends Type {

	@Override
	public boolean equals(Object t) {
		return t instanceof BoolType;
	}

	@Override
	public String toString() {
		return "Boolean";
	}

	@Override
	public Value parse(String message) {
		if (message.equals("true")) {
			return new BoolValue(true);
		} else {
			return new BoolValue(false);
		}
	}
}
