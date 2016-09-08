package org.uva.sea.ql.ast;

public interface Visitable {
	public void accept(Visitor visitor);
}
