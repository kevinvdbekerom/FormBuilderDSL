package org.uva.sea.ql.ast.expr;

import org.uva.sea.ql.ast.Visitable;
import org.uva.sea.ql.ast.Visitor;

public class Sub extends BinaryExpr implements Visitable {
	
	public Sub(Expr lhs, Expr rhs) {
		super.lhs = lhs;
		super.rhs = rhs;
		super.type = Type.INT;
	}
	
	@Override
	public Integer eval() {
		return (Integer) lhs.eval() - (Integer) rhs.eval();
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
