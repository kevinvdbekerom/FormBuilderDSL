package org.uva.sea.ql.ast.stat;

import org.uva.sea.ql.ast.ASTNode;
import org.uva.sea.ql.ast.expr.Expr;
import org.uva.sea.ql.ast.form.ValueMap;
import org.uva.sea.ql.value.UndefinedValue;
import org.uva.sea.ql.value.Value;
import org.uva.sea.ql.visit.Visitable;
import org.uva.sea.ql.visit.Visitor;

public class IfStatement extends Statement implements Visitable {
	private Block block;
	private Expr condition;
	
	public IfStatement(Block block, Expr clause, int startLine) {
		super(startLine);
		this.block = block;
		this.condition = clause;
	}
	
	public void accept(Visitor visitor, Object context) {
		visitor.visit(this, context);
	}

	public Block getBlock() {
		return block;
	}

	public Expr getCondition() {
		return condition;
	}
	
	@Override
	public String toString() {
		return "IfStatement";
	}
	
	public boolean getConditionValue(ValueMap valueMap) {
		Value conditionValue = condition.eval(valueMap);
		if (conditionValue.equals(new UndefinedValue())) {
			return false;
		} else {
			return (Boolean) conditionValue.getValue();
		}
	}
}
