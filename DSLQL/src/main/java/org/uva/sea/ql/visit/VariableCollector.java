package org.uva.sea.ql.visit;

import java.util.ArrayList;
import java.util.List;

import org.uva.sea.ql.ast.ASTNode;
import org.uva.sea.ql.ast.expr.terminals.Variable;

/* Visitor that extends LeftDFSVisitor and collects all questions in a block */

public class VariableCollector extends LeftDFSVisitor<Void> {
	private List<Variable> variables;
	
	private VariableCollector() {
		variables = new ArrayList<Variable>();
	}
	
	@Override
	public void visit(Variable variable, Void context) {
		variables.add(variable);
	}
	
	public static List<Variable> getVariables(ASTNode root) {
		VariableCollector collector = new VariableCollector();
		root.accept(collector, null);
		return collector.variables;
	}
	
	public static List<String> geVariableIDs(ASTNode root) {
		VariableCollector collector = new VariableCollector();
		root.accept(collector, null);
		List<String> variableIDs = new ArrayList<String>();
		
		for (Variable v : collector.variables) {
			variableIDs.add(v.getIdentifier());
		}
		
		return variableIDs;
	}
}

