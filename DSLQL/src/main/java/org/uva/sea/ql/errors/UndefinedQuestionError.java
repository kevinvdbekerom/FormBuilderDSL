package org.uva.sea.ql.errors;

import org.uva.sea.ql.ast.ASTNode;
import org.uva.sea.ql.ast.stat.Question;

public class UndefinedQuestionError extends QLError {
	
	public UndefinedQuestionError(ASTNode n, String message) {
		super(n, message);
	}
	
}
