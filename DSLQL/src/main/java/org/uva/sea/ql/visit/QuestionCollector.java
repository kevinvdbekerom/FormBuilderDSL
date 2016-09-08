package org.uva.sea.ql.visit;

import java.util.ArrayList;
import java.util.List;

import org.uva.sea.ql.ast.ASTNode;
import org.uva.sea.ql.ast.stat.*;

/* Visitor that extends LeftDFSVisitor and collects all questions in a block */

public class QuestionCollector extends LeftDFSVisitor<Void> {
	private List<Question> questions;
	private final boolean fullDepthSearch;
	
	private QuestionCollector(boolean fullDepthSearch) {
		this.fullDepthSearch = fullDepthSearch;
		questions = new ArrayList<Question>();
	}
	
	@Override
	public void visit(Question question, Void context) {
		questions.add(question);
	}
	
	@Override
	public void visit(ComputedQuestion question, Void context) {
		questions.add(question);
	}
	
	@Override
	public void visit(Block block, Void context) {
		super.visit(block, context);
	}
	
	@Override
	public void visit(IfStatement ifStatement, Void context) {
		// only visit questions on deeper levels of AST tree if selected by user.
		if (fullDepthSearch) {
			super.visit(ifStatement, context);
		}
	}
	
	/* Search the AST starting from a given block. 
	 * @param: fullDepthSearch: true => return all questions from root node *block*. 
	 * 							false => return all questions on the first level of root node *block*.
	 */
	public static List<Question> getQuestions(ASTNode block, boolean fullDepthSearch) {
		QuestionCollector collector = new QuestionCollector(fullDepthSearch);
		block.accept(collector, null);
		return collector.questions;
	}
	
	/* Search the AST starting from a given block. 
	 * @param: fullDepthSearch: true => return all questionsIDs from root node *block*. 
	 * 							false => return all questionsIDs on the first level of root node *block*.
	 */
	public static List<String> getQuestionIDs(ASTNode block, boolean fullDepthSearch) {
		QuestionCollector collector = new QuestionCollector(fullDepthSearch);
		block.accept(collector, null);
		List<String> questionIDs = new ArrayList<String>();
		//questions.forEach(question -> questionIDs.add(question.getIdentifier()));
		
		for (Question q : collector.questions) {
			questionIDs.add(q.getIdentifier());
		}
		
		return questionIDs;
	}
}
