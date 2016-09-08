package org.uva.sea.ql.type;

import org.uva.sea.ql.value.Value;

public class WildType extends Type {

	@Override
	public boolean equals(Object t) {
		return t instanceof WildType;
	}

	@Override
	public String toString() {
		return "Wildcard";
	}

	@Override
	public Value parse(String message) {
		return null;
	}

}
