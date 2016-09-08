package org.uva.sea.ql.ast.expr.math;

import org.uva.sea.ql.ast.expr.Expr;
import org.uva.sea.ql.ast.expr.UnaryExpr;
import org.uva.sea.ql.ast.form.TypeMap;
import org.uva.sea.ql.ast.form.ValueMap;
import org.uva.sea.ql.type.NumericalType;
import org.uva.sea.ql.type.Type;
import org.uva.sea.ql.value.Value;
import org.uva.sea.ql.visit.Visitor;

public class Neg extends UnaryExpr {
	
	public Neg(Expr expr, int startLine) {
		super(expr, startLine);
	}
	
	
	@Override
	public Value eval(ValueMap valueMap) {
		Value value = child.eval(valueMap);
		return value.neg();
	}

	public void accept(Visitor visitor, Object context) {
		visitor.visit(this, context);
	}
	//TODO{Check if this way of computation is correct}

	@Override
	public String toString() {
		return super.toString() + " (negation)";
	}
	
	@Override
	public Type getType(TypeMap context) {
		return new NumericalType(); 
	}
}
