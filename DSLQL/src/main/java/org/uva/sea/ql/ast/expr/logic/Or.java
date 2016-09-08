package org.uva.sea.ql.ast.expr.logic;

import org.uva.sea.ql.ast.expr.BinaryExpr;
import org.uva.sea.ql.ast.expr.Expr;
import org.uva.sea.ql.ast.form.TypeMap;
import org.uva.sea.ql.ast.form.ValueMap;
import org.uva.sea.ql.type.BoolType;
import org.uva.sea.ql.type.Type;
import org.uva.sea.ql.value.BoolValue;
import org.uva.sea.ql.value.UndefinedValue;
import org.uva.sea.ql.value.Value;
import org.uva.sea.ql.visit.Visitor;

public class Or extends BinaryExpr {
	
	public Or(Expr lhs, Expr rhs, int startLine) {
		super(lhs, rhs, startLine);
	}
	
	@Override
	public Value eval(ValueMap valueMap) {
		Value undefined = new UndefinedValue();
		if (lhs.eval(valueMap).equals(undefined) || 
				rhs.eval(valueMap).equals(undefined)) {
				return undefined;
		} else {
			return new BoolValue((Boolean) lhs.eval(valueMap).getValue() 
							|| (Boolean) rhs.eval(valueMap).getValue());
		}
	}
	
	public void accept(Visitor visitor, Object context) {
		visitor.visit(this, context);
	}

	@Override
	public String toString() {
		return super.toString() + " (||)";
	}
	
	@Override
	public Type getType(TypeMap context) {
		return new BoolType();
	}
}
