package org.uva.sea.ql.ast.form;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.uva.sea.ql.ast.stat.*;
import org.uva.sea.ql.ast.expr.*;

public class TypeChecker {
	
	/*
		@returns: true if there exists a duplicate question identifier in the list <questions>
	*/
	public boolean duplicateQuestionVariable(List<Question> questions) {
		Map<String, Type> questionTypeTable = new HashMap<String, Type>();
		
		for (Question q : questions) { 
			String key = q.getIdentifier();
			if (questionTypeTable.containsKey(key)) {
				// same question identifier -> check if types match. If so, duplicate question.
				if (q.getType().equals(questionTypeTable.get(key))) {
					return false;
				}
			}
			questionTypeTable.put(q.getIdentifier(), q.getType());
		}
		
		return true;
	}
	
	public boolean duplicateQuestionLabel(List<Question> questions) {
		Set<String> identifiers = new HashSet<String>();
		
		for (Question q : questions) { //TODO: document note -> q to not confuse with the list 'questions'
			identifiers.add(q.getLabel());
		}
		
		return (! (questions.size() == identifiers.size()) );
	}
	
	public boolean cyclicDependancy() {
		return false;
		//TODO: implement skeleton method
	}
	
	public boolean typeMismatch() {
		return false;
		//TODO: implement skeleton method
	}
	
	public boolean referenceToUndefinedQuestion(List<Question> questions, List<Variable> variables) {
		List<String> qIdentifiers = new ArrayList<String>();
		
		for (Question q : questions) {
			qIdentifiers.add(q.getIdentifier());
		}
		
		for (Variable v : variables) {
			if (qIdentifiers.contains(v.getIdentifier())) {
				return false;
			}
		}
		
		return true;
	}
	
	public boolean typeMismatchBooleanExpressions() {
		return true;
		//TODO: implement skeleton method
	}
	
	public boolean typeMismatchIntegerExpressions() {
		return true;
		//TODO: implement skeleton method
	}
	
	public boolean typeMismatchUnaryExpression() {
		return true;
		//TODO: implement skeleton method
	}
	

}
