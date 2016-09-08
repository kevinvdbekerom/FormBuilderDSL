package org.uva.sea.ql.ast.expr.math;

import org.uva.sea.ql.ast.expr.BinaryExpr;
import org.uva.sea.ql.ast.expr.Expr;
import org.uva.sea.ql.ast.form.TypeMap;
import org.uva.sea.ql.ast.form.ValueMap;
import org.uva.sea.ql.type.*;
import org.uva.sea.ql.value.Value;
import org.uva.sea.ql.visit.Visitor;

public class Add extends BinaryExpr {
	
	public Add(Expr lhs, Expr rhs, int startLine) {
		super(lhs, rhs, startLine);
	}
	
	@Override
	public Value eval(ValueMap valueMap) {
		return lhs.eval(valueMap).add(rhs.eval(valueMap)); 
	}
	
	public void accept(Visitor visitor, Object context) {
		visitor.visit(this, context);
	}

	@Override
	public String toString() {
		return super.toString() + " (+)";
	}

	@Override
	public Type getType(TypeMap context) {
		return new NumericalType();
	}
}
