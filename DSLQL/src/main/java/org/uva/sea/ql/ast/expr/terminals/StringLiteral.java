package org.uva.sea.ql.ast.expr.terminals;

import org.uva.sea.ql.ast.expr.Expr;
import org.uva.sea.ql.ast.form.TypeMap;
import org.uva.sea.ql.ast.form.ValueMap;
import org.uva.sea.ql.type.StrType;
import org.uva.sea.ql.type.Type;
import org.uva.sea.ql.value.StrValue;
import org.uva.sea.ql.value.Value;
import org.uva.sea.ql.visit.Visitor;

public class StringLiteral extends Expr {
	final String value;
	
	public StringLiteral(String value) {
		super(-1);
		this.value = value;
	}
	
	@Override
	public Value eval(ValueMap valueMap) {
		return new StrValue(value);
	}
	
	public void accept(Visitor visitor, Object context) {
		visitor.visit(this, context);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Type getType(TypeMap context) {
		return new StrType();
	}
}
