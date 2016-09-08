package org.uva.sea.ql.ast.expr;

public abstract class UnaryExpr extends Expr {
	protected final Expr child;
	
	public UnaryExpr(Expr child, int startLine) {
		super(startLine);
		this.child = child;
	}
	
	public Expr getChild() {
		return child;
	}
}
