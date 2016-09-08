package org.uva.sea.ql.ast.expr;

import org.uva.sea.ql.ast.*;
import org.uva.sea.ql.ast.form.TypeMap;
import org.uva.sea.ql.ast.form.ValueMap;
import org.uva.sea.ql.type.Type;
import org.uva.sea.ql.value.Value;
import org.uva.sea.ql.visit.Visitable;

public abstract class Expr extends ASTNode implements Visitable {
	
	public Expr(int startLine) {
		super(startLine);
	}
	
	public abstract Value eval(ValueMap valueMap);
	public abstract Type getType(TypeMap context);
	
	@Override
	public String toString() {
		return "expression";
	}
}
