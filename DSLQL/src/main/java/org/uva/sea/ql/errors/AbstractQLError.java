package org.uva.sea.ql.errors;

import org.uva.sea.ql.ast.ASTNode;

public abstract class AbstractQLError {
	protected String message;
	protected ASTNode source;
	
	public abstract String getErrorMessage();
}
