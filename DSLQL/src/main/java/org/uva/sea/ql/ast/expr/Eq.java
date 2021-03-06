package org.uva.sea.ql.ast.expr;

import org.uva.sea.ql.ast.Visitable;
import org.uva.sea.ql.ast.Visitor;

public class Eq extends BinaryExpr implements Visitable {	
	
	public Eq(Expr lhs, Expr rhs) {
		super.lhs = lhs;
		super.rhs = rhs;
		super.type = Type.BOOLEAN;
	}
	
	//TODO: different check for Strings and booleans!!! Need to know the type
	@Override
	public Boolean eval() {
		return lhs.eval().equals(rhs.eval());
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
