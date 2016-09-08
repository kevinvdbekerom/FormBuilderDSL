package org.uva.sea.ql.ast.expr.terminals;

import org.uva.sea.ql.ast.expr.Expr;
import org.uva.sea.ql.ast.form.TypeMap;
import org.uva.sea.ql.ast.form.ValueMap;
import org.uva.sea.ql.type.Type;
import org.uva.sea.ql.value.Value;
import org.uva.sea.ql.visit.Visitor;

public class Variable extends Expr {
	final String identifier;

	public Variable(String identifier) {
		super(-1);
		this.identifier = identifier;
	}

	public String getIdentifier() {
		return this.identifier;
	}

	@Override
	public Value eval(ValueMap valueMap) {
		return valueMap.getValueFromMap(identifier);
	}
	
	public void accept(Visitor visitor, Object context) {
		visitor.visit(this, context);
	}

	@Override
	public String toString() {
		return "TODO!!!";
	}
	
	@Override
	public Type getType(TypeMap context) {
		return context.getTypeFromQuestion(identifier);
	}
}
