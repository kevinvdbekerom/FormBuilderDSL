package org.uva.sea.ql.ast.expr;

import org.uva.sea.ql.ast.Visitable;
import org.uva.sea.ql.ast.Visitor;

public class And extends BinaryExpr implements Visitable{
	
	public And(Expr lhs, Expr rhs) {
		super.lhs = lhs;
		super.rhs = rhs;
		super.type = Type.BOOLEAN;
	}
	
	@Override
	public Boolean eval() {
		return (Boolean) lhs.eval() && (Boolean) rhs.eval();
	}
	
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	@Override
	public String toString() {
		return "&&";
	}
}
