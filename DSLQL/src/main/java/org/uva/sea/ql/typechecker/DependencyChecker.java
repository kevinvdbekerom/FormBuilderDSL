package org.uva.sea.ql.typechecker;

import java.util.ArrayList;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.uva.sea.ql.ast.ASTNode;
import org.uva.sea.ql.ast.stat.*;
import org.uva.sea.ql.errors.QLError;
import org.uva.sea.ql.graph.Graph;
import org.uva.sea.ql.graph.Vertex;
import org.uva.sea.ql.visit.LeftDFSVisitor;
import org.uva.sea.ql.visit.VariableCollector;

public class DependencyChecker extends LeftDFSVisitor<Graph> {
	private List<QLError> errors;
	
	// internal state management, used in cyclic detection algorithm.
	private Set<String> cycles; 
	private Set<String> visited; 
	
	//inner class

	private DependencyChecker() {
		errors = new ArrayList<QLError>();
		cycles = new HashSet<String>();
		visited = new HashSet<String>();
	}

	public static List<QLError> getErrors(ASTNode root, Graph dependencyGraph) {
		DependencyChecker depChecker = new DependencyChecker();
		root.accept(depChecker, dependencyGraph);
		List<QLError> result = depChecker.errors;
		//depChecker.errors.removeAll(depChecker.errors);
		return result;
	}

	@Override
	public void visit(Question question, Graph dependencyGraph) {
		// skip
	}

	@Override
	public void visit(ComputedQuestion compQuestion, Graph dependencyGraph) {
		Set<String> varIDs = new HashSet<String>();
		varIDs.addAll(VariableCollector.geVariableIDs(compQuestion.getExpr()));

		addCyclicErrors(compQuestion, varIDs, dependencyGraph);

		for (String varID : varIDs) {
			Vertex start = dependencyGraph.getVertex(varID);

			if (start == null) {
				if (dependencyGraph.containsVertex(varID)) {
					// no error
				} else {
					String errMessage = "There is a dependency on question " 
							+ varID + " which is not defined!";
					errors.add(new QLError(compQuestion, errMessage));
				}
			}

		}
	}

	@Override
	public void visit(IfStatement ifStatement, Graph dependencyGraph) {
		Set<String> varIDs = new HashSet<String>();
		varIDs.addAll(VariableCollector.geVariableIDs(ifStatement.getCondition()));

		addCyclicErrors(ifStatement, varIDs, dependencyGraph);

		for (String varID : varIDs) {
			Vertex start = dependencyGraph.getVertex(varID);
			if (start == null) {
				if (dependencyGraph.containsVertex(varID)) {
					// ok
				} else {
					String errMessage = "There is a dependency on question " 
							+ varID + " which is not defined!";
					errors.add(new QLError(ifStatement, errMessage));
				}

			}
		}

	}

	private void addCyclicErrors(ASTNode node, Set<String> varIDs, Graph dependencyGraph) {
		// collect cyclic dependencies.
		List<Vertex> vertices = new ArrayList<Vertex>();

		for (String vID : varIDs) {
			if (! dependencyGraph.containsVertex(vID)) {
				String errMessage = "There is a dependency on question " 
						+ vID + " which is not defined!";
				errors.add(new QLError(node, errMessage));
			} else {
				vertices.add(dependencyGraph.getVertex(vID));
			}
		}

		// state management
		cycles.removeAll(cycles);
		visited.removeAll(visited);

		collectCycles(vertices, dependencyGraph, node); // class variable cycles now contains all cycles.

		// state management
		cycles.removeAll(cycles);
		visited.removeAll(visited);
	}

	private void collectCycles(List<Vertex> startVertices, Graph g, ASTNode node) {

		for (Vertex v : startVertices) {
			if  (visited.contains(v)) {
				continue;
			}
			DFSGraph(v, visited, new ArrayList<String>(), cycles, g, node);
		}

	}

	private void DFSGraph(Vertex start, Set<String> vist,
			List<String> path, Set<String> cycls,
			Graph g, ASTNode node) {
		visited.add(start.getIdentifier());
		path.add(start.getIdentifier());

		for (String neighbor : start.getNeighbors()) {

			// cycle detect
			if (path.contains(neighbor)) { // cycle;
				String message = "cycle detected! ";
				for (String p : path) {
					message += p + " -> ";
				}
				message += neighbor;

				errors.add(new QLError(node, message));
			}

			// infeasible path detect
			if (!g.containsVertex(neighbor)) {
				break;
			}

			if (visited.contains(neighbor)) {
				continue;
			}

			DFSGraph(g.getVertex(neighbor), visited, path, cycles, g, node);
		}
		path.remove(start.getIdentifier());

	}
}
