package org.uva.sea.ql.ast.expr;

import org.uva.sea.ql.ast.Visitable;
import org.uva.sea.ql.ast.Visitor;

public class Pos extends UnaryExpr implements Visitable {
	
	public Pos(Expr expr) {
		super.child = expr;
		super.type = Type.INT;
	}
	
	@Override
	public Integer eval() {
		return Math.abs( (Integer) child.eval());
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
