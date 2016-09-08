package org.uva.sea.ql.ast;

import org.uva.sea.ql.ast.expr.*;
import org.uva.sea.ql.ast.stat.*;
import org.uva.sea.ql.ast.form.*;

public interface Visitor { 
	public void visit(ASTNode node);
	
	// visit expression nodes
	public void visit(Expr expr);
	public void visit(BinaryExpr binExpr);
	public void visit(UnaryExpr unExpr);
	
	public void visit(And and);
	public void visit(Or or);
	public void visit(Not not);
	
	public void visit(Eq eq);
	public void visit(GEq geq);
	public void visit(GT gt);
	public void visit(LEq leq);
	public void visit(LT lt);
	public void visit(NEq neq);
	
	public void visit(Add add);
	public void visit(Sub sub);
	public void visit(Div div);
	public void visit(Mul mul);
	
	public void visit(Neg neg);
	public void visit(Pos pos);
	
	public void visit(IntegerLiteral integerLiteral);
	public void visit(StringLiteral stringLiteral);
	public void visit(BooleanLiteral booleanLiteral);
	public void visit(Variable variable);
	
	// visit statement nodes
	public void visit(Block block);
	public void visit(ElseStatement elseStatement);
	public void visit(IfStatement ifStatement);
	public void visit(Question question);
	public void visit(ComputedQuestion question);
	
}
