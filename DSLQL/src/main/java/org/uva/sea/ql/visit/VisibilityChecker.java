package org.uva.sea.ql.visit;

import org.uva.sea.ql.ast.form.ReachableQuestionsMap;
import org.uva.sea.ql.ast.form.ValueMap;
import org.uva.sea.ql.ast.stat.ComputedQuestion;
import org.uva.sea.ql.ast.stat.IfStatement;
import org.uva.sea.ql.ast.stat.Question;

public class VisibilityChecker extends LeftDFSVisitor<ReachableQuestionsMap> {
	final private ValueMap valueMap;
	
	public VisibilityChecker(ValueMap valueMap) {
		this.valueMap = valueMap;
	}
	
	@Override 
	public void visit(Question question, ReachableQuestionsMap map) {
		map.updateValueInMap(question.getIdentifier(), true);
	}
	
	@Override 
	public void visit(ComputedQuestion compQuestion, ReachableQuestionsMap map) {
		map.updateValueInMap(compQuestion.getIdentifier(), true);
	}
	
	@Override 
	public void visit(IfStatement ifStatement, ReachableQuestionsMap map) {
		if (ifStatement.getConditionValue(valueMap)) {
			super.visit(ifStatement, map);
		}
	}
}
