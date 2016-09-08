package org.uva.sea.ql.ast.expr;

import org.uva.sea.ql.ast.Visitable;
import org.uva.sea.ql.ast.Visitor;

public class LT extends BinaryExpr implements Visitable {
	
	public LT(Expr lhs, Expr rhs) {
		super.lhs = lhs;
		super.rhs = rhs;
		super.type = Type.BOOLEAN;
	}
	
	@Override
	public Boolean eval() {
		return (Integer) lhs.eval() < (Integer) rhs.eval();
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
