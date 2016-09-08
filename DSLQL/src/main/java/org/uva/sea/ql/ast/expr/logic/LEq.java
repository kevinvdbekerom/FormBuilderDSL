package org.uva.sea.ql.ast.expr.logic;

import org.uva.sea.ql.ast.expr.BinaryExpr;
import org.uva.sea.ql.ast.expr.Expr;
import org.uva.sea.ql.ast.form.TypeMap;
import org.uva.sea.ql.ast.form.ValueMap;
import org.uva.sea.ql.type.NumericalType;
import org.uva.sea.ql.type.Type;
import org.uva.sea.ql.value.Value;
import org.uva.sea.ql.visit.Visitor;

public class LEq extends BinaryExpr {
	
	public LEq(Expr lhs, Expr rhs, int startLine) {
		super(lhs, rhs, startLine);
	}
	
	@Override
	public Value eval(ValueMap valueMap) {
		return  rhs.eval(valueMap).lt(lhs.eval(valueMap), true);
	}
	
	public void accept(Visitor visitor, Object context) {
		visitor.visit(this, context);
	}

	@Override
	public String toString() {
		return super.toString() + " (<=)";
	}
	
	@Override
	public Type getType(TypeMap context) {
		return new NumericalType();
	}
}
