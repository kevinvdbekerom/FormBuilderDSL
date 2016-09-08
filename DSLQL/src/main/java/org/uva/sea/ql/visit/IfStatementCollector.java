package org.uva.sea.ql.visit;

import java.util.ArrayList;
import java.util.List;

import org.uva.sea.ql.ast.ASTNode;
import org.uva.sea.ql.ast.stat.Block;
import org.uva.sea.ql.ast.stat.IfStatement;

/* Visitor that extends LeftDFSVisitor and collects all ifStatements in a block */
public class IfStatementCollector extends LeftDFSVisitor<Void> {
	
	private List<IfStatement> ifStatements;
	
	private IfStatementCollector() {
		ifStatements = new ArrayList<IfStatement>();
	}
	
	@Override
	public void visit(Block block, Void context) {
		super.visit(block, context);
	}
	
	@Override
	public void visit(IfStatement ifStatement, Void context) {
		ifStatements.add(ifStatement);
	}
	
	public static List<IfStatement> getIfStatements(ASTNode root) {
		IfStatementCollector collector = new IfStatementCollector();
		root.accept(collector, null);
		return collector.ifStatements;
	}

}

