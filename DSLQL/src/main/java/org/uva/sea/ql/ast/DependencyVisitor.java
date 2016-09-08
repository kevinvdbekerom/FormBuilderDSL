package org.uva.sea.ql.ast;

import org.uva.sea.ql.ast.stat.*;
import org.uva.sea.ql.errors.QLError;

import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.uva.sea.ql.ast.expr.*;

// TODO: Check if refactoring went ok.
// TODO: Same question ID, but different type!
public class DependencyVisitor extends LeftDFSVisitor {
	
	private Set<String> visited; // keep track of visited questions that are safe.
	private Set<String> undefinedQuestionIDs;
	private Set<Question> undefinedQuestions;
	private NodeCollector collector;
	
	public DependencyVisitor() {
		undefinedQuestions = new HashSet<Question>();
		collector = new NodeCollector();
		visited = new HashSet<String>();
		undefinedQuestionIDs = new HashSet<String>();
	}
	
	@Override
	public void visit(Question question) {
		if (! undefinedQuestionIDs.contains(question.getIdentifier())) {
			visited.add(question.getIdentifier());
		} // refactor, i.e. remove 
		else {
			String errorMessage = "can not be reached!";
			System.out.println(new QLError(question, errorMessage).getErrorMessage());
		}// refactor, i.e. remove 
		
	}
	
	@Override
	public void visit(ComputedQuestion computedQuestion) {
		if (! undefinedQuestionIDs.contains(computedQuestion.getIdentifier()) ) {
			visited.add(computedQuestion.getIdentifier());
		} // refactor, i.e. remove 
		else {
			String errorMessage = "can not be reached!";
			System.out.println(new QLError(computedQuestion, errorMessage).getErrorMessage());
		}// refactor, i.e. remove 
		
		computedQuestion.getComputedQuestion().accept(collector);
		Set<String> clauseVariables = new HashSet<String>(collector.getVariableNames());
		collector.reset();
		clauseVariables.removeAll(visited); // now contains all conflicting variable identifiers.
		undefinedQuestionIDs.addAll(clauseVariables);
		if (clauseVariables.size() > 0) {
			String errorMessage = "contains variables that are not yet defined!";
			System.out.println(new QLError(computedQuestion, errorMessage).getErrorMessage());
		}
		undefinedQuestions.add(computedQuestion);
		collector.reset();
		
		super.visit(computedQuestion);	
	}
	
	//TODO: Fix bug of overreporting errors!!!
	@Override
	public void visit(IfStatement ifStatement) {

		ifStatement.getClause().accept(collector);
		Set<String> clauseVariables = new HashSet<String>(collector.getVariableNames());
	
		clauseVariables.removeAll(visited); // now contains all conflicting variable identifiers.
		undefinedQuestionIDs.addAll(clauseVariables);
		for (String var : clauseVariables) {
			String errorMessage = "contains variable " + var + " which is not yet defined!";
			System.out.println(new QLError(ifStatement, errorMessage).getErrorMessage());
		}
		
		if (! clauseVariables.isEmpty()) {
			collector.reset();
			ifStatement.getBlock().accept(collector);
			undefinedQuestionIDs.addAll(collector.getQuestionIdentifiers());
		}
		collector.reset();
		super.visit(ifStatement);
	}
	
	public Set<String> getUndefinedQuestionIDs() {
		return this.undefinedQuestionIDs;
	}
	
	
}
