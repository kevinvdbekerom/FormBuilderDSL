package org.uva.sea.ql.typechecker;

import org.uva.sea.ql.ast.stat.*;
import org.uva.sea.ql.visit.LeftDFSVisitor;
import org.uva.sea.ql.ast.form.*;

public class TypeMapBuilder extends LeftDFSVisitor<TypeMap> {
	
	@Override
	public void visit(Question question, TypeMap context) {
		context.putQuestionType(question.getIdentifier(), question.getType());
	}
	
	@Override
	public void visit(ComputedQuestion question, TypeMap context) {
		context.putQuestionType(question.getIdentifier(), question.getType());
	}
}
