package org.uva.sea.ql.ast.expr;

import org.uva.sea.ql.ast.Visitable;
import org.uva.sea.ql.ast.Visitor;

public class Add extends BinaryExpr implements Visitable {
	
	public Add(Expr lhs, Expr rhs) {
		super.lhs = lhs;
		super.rhs = rhs;
		super.type = Type.INT;
	}
	
	@Override
	public Integer eval() {
		try {
			return (Integer) lhs.eval() + (Integer) rhs.eval();
		} catch (ClassCastException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	@Override
	public String toString() {
		return "+";
	}
}
