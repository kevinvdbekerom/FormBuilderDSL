package org.uva.sea.ql.ast.expr;

import org.uva.sea.ql.ast.Visitable;
import org.uva.sea.ql.ast.Visitor;

public class Div extends BinaryExpr implements Visitable {
	
	public Div(Expr lhs, Expr rhs) {
		super.lhs = lhs;
		super.rhs = rhs;
		super.type = Type.INT;
	}
	
	@Override
	public Integer eval() {
		return (Integer) lhs.eval() / (Integer) rhs.eval();
	}
	
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
}
