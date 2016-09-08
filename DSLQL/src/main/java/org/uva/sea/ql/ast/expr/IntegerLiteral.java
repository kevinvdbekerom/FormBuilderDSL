package org.uva.sea.ql.ast.expr;

import org.uva.sea.ql.ast.Visitable;
import org.uva.sea.ql.ast.Visitor;

public class IntegerLiteral extends Expr implements Visitable {
	final int value;
	
	public IntegerLiteral(int value) {
		this.value = value;
		this.type = Type.INT;
	}
	
	@Override
	public Integer eval() {
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
