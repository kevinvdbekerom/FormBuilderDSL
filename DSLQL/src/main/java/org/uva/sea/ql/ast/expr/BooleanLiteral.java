package org.uva.sea.ql.ast.expr;

import org.uva.sea.ql.ast.Visitable;
import org.uva.sea.ql.ast.Visitor;

public class BooleanLiteral extends Expr implements Visitable {
	final boolean value;
	
	public BooleanLiteral(boolean value) {
		this.value = value;
		super.type = Type.BOOLEAN;
	}
	
	@Override
	public Boolean eval() {
		return value;
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
