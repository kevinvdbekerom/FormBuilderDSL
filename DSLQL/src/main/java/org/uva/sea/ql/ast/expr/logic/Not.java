package org.uva.sea.ql.ast.expr.logic;

import org.uva.sea.ql.ast.expr.Expr;
import org.uva.sea.ql.ast.expr.UnaryExpr;
import org.uva.sea.ql.ast.form.TypeMap;
import org.uva.sea.ql.ast.form.ValueMap;
import org.uva.sea.ql.type.BoolType;
import org.uva.sea.ql.type.Type;
import org.uva.sea.ql.value.BoolValue;
import org.uva.sea.ql.value.Value;
import org.uva.sea.ql.visit.Visitor;

public class Not extends UnaryExpr {
	
	public Not(Expr expr, int startLine) {
		super(expr, startLine);
	}
	
	@Override
	public Value eval(ValueMap valueMap) {
		return new BoolValue(! (Boolean) child.eval(valueMap).getValue());
	}
	
	public void accept(Visitor visitor, Object context) {
		visitor.visit(this, context);
	}

	@Override
	public String toString() {
		return super.toString() + " (!)";
	}
	
	@Override
	public Type getType(TypeMap context) {
		return new BoolType();
	}
}
