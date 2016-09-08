package org.uva.sea.ql.graph;

import java.util.HashSet;
import java.util.Set;

import org.uva.sea.ql.ast.stat.Block;
import org.uva.sea.ql.ast.stat.ComputedQuestion;
import org.uva.sea.ql.ast.stat.IfStatement;
import org.uva.sea.ql.ast.stat.Question;
import org.uva.sea.ql.visit.LeftDFSVisitor;
import org.uva.sea.ql.visit.QuestionCollector;
import org.uva.sea.ql.visit.VariableCollector;

public class DependencyGraphBuilder extends LeftDFSVisitor<Graph> {

	Graph dependencyGraph;

	public DependencyGraphBuilder(Block root) {
		dependencyGraph = new Graph();
		root.accept(this, dependencyGraph);
	}

	@Override
	public void visit(IfStatement ifStatement, Graph dependencyGraph) {
		Set<String> vertexIDs = new HashSet<String>();
		Set<String> varIDs = new HashSet<String>();
		vertexIDs.addAll(QuestionCollector.getQuestionIDs(ifStatement.getBlock(), false));
		varIDs.addAll(VariableCollector.geVariableIDs(ifStatement.getCondition()));

		for (String qID : vertexIDs) {
			// Add all questions to the graph as vertices
			dependencyGraph.addVertex(new Vertex(qID));

			for (String varID : varIDs) {
				// For each question add the edge from the question to the variable.
				dependencyGraph.addEdge(qID, varID);
			}

		}

		ifStatement.getBlock().accept(this, dependencyGraph);
	}

	@Override 
	public void visit(ComputedQuestion compQuestion, Graph dependencyGraph) {
		dependencyGraph.addVertex(new Vertex(compQuestion.getIdentifier()));
		Set<String> varIDs = new HashSet<String>();
		varIDs.addAll(VariableCollector.geVariableIDs(compQuestion.getExpr()));

		for(String varID : varIDs) {
			dependencyGraph.addEdge(compQuestion.getIdentifier(), varID);
		}

	}

	@Override 
	public void visit(Question question, Graph dependencyGraph) {
		if (! dependencyGraph.containsVertex(question.getIdentifier())) {
			dependencyGraph.addVertex(new Vertex(question.getIdentifier()));
		}
	}

	public Graph getDependencyGraph() {
		Graph result = dependencyGraph;
		//dependencyGraph.resetGraph();
		return result;
	}

}
