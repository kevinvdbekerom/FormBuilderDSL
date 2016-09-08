package org.uva.sea.ql.ast.stat;

import java.util.ArrayList;
import java.util.List;

import org.uva.sea.ql.ast.ASTNode;
import org.uva.sea.ql.visit.Visitable;
import org.uva.sea.ql.visit.Visitor;

public class Block extends ASTNode implements Visitable {
	List<Statement> stmts;
	
	public Block() {
		super(-1);
		stmts = new ArrayList<Statement>();
	}
	
	public List<Statement> getStmts() {
		return stmts;
	}
	
	public void add(Statement stmt) {
		stmts.add(stmt);
	}

	public void accept(Visitor visitor, Object context) {
		visitor.visit(this, context);
	}
	
	@Override
	public String toString() {
		return "Block";
	}

}
