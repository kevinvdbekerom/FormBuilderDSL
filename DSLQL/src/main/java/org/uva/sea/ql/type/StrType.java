package org.uva.sea.ql.type;

import org.uva.sea.ql.value.StrValue;
import org.uva.sea.ql.value.Value;

public class StrType extends Type {
	
	@Override
	public boolean equals(Object t) {
		return t instanceof StrType;
	}

	@Override
	public String toString() {
		return "String";
	}

	@Override
	public Value parse(String message) {
		return new StrValue(message);
	}
	
}
