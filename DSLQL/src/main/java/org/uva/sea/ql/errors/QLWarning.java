package org.uva.sea.ql.errors;

import org.uva.sea.ql.ast.ASTNode;

public class QLWarning extends AbstractQLError{
	
	public QLWarning(ASTNode source, String message) {
		this.source = source;
		this.message = message;
	}
	
	public String getErrorMessage() {
		StringBuilder sb = new StringBuilder();
		sb.append("[WARNING on line ")
		.append(source.getStartLine())
		.append("] source: ")
		.append(source.toString())
		.append(". ")
		.append(message);
		return sb.toString();
	}
}
