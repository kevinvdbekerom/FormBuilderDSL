package org.uva.sea.ql.visit;

public interface Visitable {
	public <U> void accept(Visitor<U> visitor, U context);
}
