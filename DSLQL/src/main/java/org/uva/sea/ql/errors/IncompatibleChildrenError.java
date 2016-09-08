package org.uva.sea.ql.errors;

import org.uva.sea.ql.ast.ASTNode;

public class IncompatibleChildrenError extends QLError {
	
	public IncompatibleChildrenError(ASTNode n, String message) {
		super(n, message);
	}
}
