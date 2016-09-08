package org.uva.sea.ql.ast.expr;

public abstract class BinaryExpr extends Expr {
	protected final Expr lhs, rhs;
	
	public BinaryExpr(Expr lhs, Expr rhs, int startLine) {
		super(startLine);
		this.lhs = lhs;
		this.rhs = rhs;
	}
	
	public Expr getLhs() {
		return lhs;
	}

	public Expr getRhs() {
		return rhs;
	}

}
