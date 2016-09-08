package org.uva.sea.ql.ast.stat;

import org.uva.sea.ql.ast.ASTNode;

public abstract class Statement extends ASTNode {
	
	public Statement(int startLine) {
		super(startLine);
	}
}
