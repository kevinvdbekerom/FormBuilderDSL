package org.uva.sea.ql.ast;

import java.util.ArrayList;
import java.util.List;

import org.uva.sea.ql.ast.expr.*;
import org.uva.sea.ql.ast.stat.*;

public class QuestionPainter extends LeftDFSVisitor {
	private List<Question> drawQuestions;
	
	public QuestionPainter() {
		drawQuestions = new ArrayList<Question>();
	}
	
	@Override
	public void visit(Block block) {
		for (ASTNode stmt : block.getStmts()) {
			System.out.println(stmt.toString());
			stmt.accept(this);
		}
	}

	@Override
	public void visit(ElseStatement elseStatement) {
		//elseStatement.getBlock().accept(this); 
	}

	@Override
	public void visit(IfStatement ifStatement) {
		if (ifStatement.getClauseValue()) {
			ifStatement.getBlock().accept(this);
		}
		
	}

	@Override
	public void visit(Question question) {
		drawQuestions.add(question);
	}
	
	public List<Question> getQuestions() {
		return drawQuestions;
	}
}
