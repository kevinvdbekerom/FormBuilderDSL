package org.uva.sea.ql.ast;

import org.uva.sea.ql.visit.Visitable;

public abstract class ASTNode implements Visitable {
	private int startLine = -1; // default value
	
	public ASTNode (int startLine) {
		this.startLine = startLine;
	}
	
	public int getStartLine() {
		return this.startLine;
	}
	
	@Override
	public abstract String toString();

	
}
