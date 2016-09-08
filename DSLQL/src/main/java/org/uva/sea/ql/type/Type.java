package org.uva.sea.ql.type;

import org.uva.sea.ql.value.Value;

public abstract class Type {
	
	@Override
	public abstract boolean equals(Object t);
	
	@Override 
	public abstract String toString();
	
	public abstract Value parse(String message);
}
