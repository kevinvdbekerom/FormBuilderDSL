package org.uva.sea.ql.typechecker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.uva.sea.ql.ast.stat.*;
import org.uva.sea.ql.errors.AbstractQLError;
import org.uva.sea.ql.errors.QLError;
import org.uva.sea.ql.errors.QLWarning;
import org.uva.sea.ql.graph.DependencyGraphBuilder;
import org.uva.sea.ql.graph.Graph;
import org.uva.sea.ql.graph.Vertex;
import org.uva.sea.ql.ast.ASTNode;
import org.uva.sea.ql.ast.expr.*;
import org.uva.sea.ql.ast.form.TypeMap;
import org.uva.sea.ql.type.Type;
import org.uva.sea.ql.visit.QuestionCollector;

public class TypeChecker {
	
	private List<AbstractQLError> qlErrors;

	/*
		@returns: true if there exists a duplicate question identifier in the list <questions>
	*/
	private List<QLError> duplicateQuestionVariable(List<Question> questions) {
		List<QLError> errors = new ArrayList<QLError>();
		Map<String, Type> questionTypeTable = new HashMap<String, Type>();
		
		for (Question q : questions) { 
			String key = q.getIdentifier();
			if (questionTypeTable.containsKey(key)) {
				// same question identifier -> check if types mismatch. If so, duplicate question.
				if (! q.getType().equals(questionTypeTable.get(key))) {
					String errMessage = "Duplicate question. Make sure questions with the same identifier have the same type!";
					errors.add(new QLError(q, errMessage)); // error message here!
				}
			}
			questionTypeTable.put(q.getIdentifier(), q.getType());
		}

		return errors;
	}

	private List<QLWarning> duplicateQuestionLabel(List<Question> questions) {
		List<QLWarning> errors = new ArrayList<QLWarning>();
		Set<String> labels = new HashSet<String>();

		for (Question q : questions) { //TODO: document note -> q to not confuse with the list 'questions'
			if (labels.contains(q.getLabel())) {
				String errMessage = "Duplicate labels. This might confuse users!";
				errors.add(new QLWarning(q, errMessage));
			}
			labels.add(q.getLabel());
		}

		return errors;
	}

	public final List<AbstractQLError> collectErrors(ASTNode root) {
		List<AbstractQLError> qlErrors = new ArrayList<AbstractQLError>();
		// 0.1 Get list of all questions
		List<Question> questions = QuestionCollector.getQuestions(root, true);

		// 0.2 Get dependency graph
		Graph dependencyGraph = new DependencyGraphBuilder((Block) root).getDependencyGraph();

		TypeMap context = new TypeMap();

		TypeMapBuilder cv = new TypeMapBuilder();
		root.accept(cv, context);

		// 1. duplicate questions:
		qlErrors.addAll(duplicateQuestionVariable(questions));

		// 2. duplicate labels:
		qlErrors.addAll(duplicateQuestionLabel(questions));

		// 3. dependency check:
		qlErrors.addAll(DependencyChecker.getErrors(root, dependencyGraph));

		// 4. Type check expressions, computed questions and if statements.
		qlErrors.addAll(TypesChecker.getErrorMessages(root, context));		

		return qlErrors;
	}

	public void printErrors(ASTNode root) {
		
		for (AbstractQLError error: collectErrors(root)) {
			System.out.println(error.getErrorMessage());
		}

	}

}
