package org.uva.sea.ql.ast.expr;

import org.uva.sea.ql.ast.Visitable;
import org.uva.sea.ql.ast.Visitor;

public class StringLiteral extends Expr implements Visitable {
	final String value;
	
	public StringLiteral(String value) {
		this.value = value;
		this.type = Type.STRING;
	}
	
	@Override
	public String eval() {
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
