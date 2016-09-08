package org.uva.sea.ql.visit;

import org.uva.sea.ql.ast.ASTNode;
import org.uva.sea.ql.ast.expr.*;
import org.uva.sea.ql.ast.expr.logic.And;
import org.uva.sea.ql.ast.expr.logic.Eq;
import org.uva.sea.ql.ast.expr.logic.GEq;
import org.uva.sea.ql.ast.expr.logic.GT;
import org.uva.sea.ql.ast.expr.logic.LEq;
import org.uva.sea.ql.ast.expr.logic.LT;
import org.uva.sea.ql.ast.expr.logic.NEq;
import org.uva.sea.ql.ast.expr.logic.Not;
import org.uva.sea.ql.ast.expr.logic.Or;
import org.uva.sea.ql.ast.expr.math.Add;
import org.uva.sea.ql.ast.expr.math.Div;
import org.uva.sea.ql.ast.expr.math.Mul;
import org.uva.sea.ql.ast.expr.math.Neg;
import org.uva.sea.ql.ast.expr.math.Pos;
import org.uva.sea.ql.ast.expr.math.Sub;
import org.uva.sea.ql.ast.expr.terminals.BooleanLiteral;
import org.uva.sea.ql.ast.expr.terminals.IntegerLiteral;
import org.uva.sea.ql.ast.expr.terminals.StringLiteral;
import org.uva.sea.ql.ast.expr.terminals.Variable;
import org.uva.sea.ql.ast.stat.*;
import org.uva.sea.ql.ast.form.*;

/* Visitor that visits every node in the AST based on Depth First Search 
 * starting with the leftmost node.
 */
public class LeftDFSVisitor<U> implements Visitor<U> {
	protected Visitor v;
	
	protected LeftDFSVisitor() {
		this.v = this;
	}
	

	public void visit(BinaryExpr binExpr, U context) {
		dfs(binExpr, this, context);	
	}

	public void visit(UnaryExpr unExpr, U context) {
		
	}
	
	public void visit(ASTNode node, U context) {
		
	}

	public void visit(And and, U context) {
		dfs(and, this, context);
	}

	public void visit(Or or, U context) {
		dfs(or, this, context);
	}

	public void visit(Not not, U context) {
		not.getChild().accept(this, context);
	}

	public void visit(Eq eq, U context) {
		dfs(eq, this, context);
	}

	public void visit(GEq geq, U context) {
		dfs(geq, this, context);
	}

	public void visit(GT gt, U context) {
		dfs(gt, this, context);
	}

	public void visit(LEq leq, U context) {
		dfs(leq, this, context);
	}

	public void visit(LT lt, U context) {
		dfs(lt, this, context);
	}

	public void visit(NEq neq, U context) {
		dfs(neq, this, context);
	}

	public void visit(Add add, U context) {
		dfs(add, this, context);
	}

	public void visit(Sub sub, U context) {
		dfs(sub, this, context);
	}

	public void visit(Div div, U context) {
		dfs(div, this, context);
	}

	public void visit(Mul mul, U context) {
		dfs(mul, this, context);
	}

	public void visit(Neg neg, U context) {
		neg.getChild().accept(this, context);
	}

	public void visit(Pos pos, U context) {
		pos.getChild().accept(this, context);
	}

	public void visit(IntegerLiteral integerLiteral, U context) {
		// leaf node
	}

	public void visit(StringLiteral stringLiteral, U context) {
		// leaf node
	}

	public void visit(BooleanLiteral booleanLiteral, U context) {
		// leaf node
	}

	public void visit(Variable variable, U context) {
		// leaf node
	}

	public void visit(Block block, U context) {
		for (ASTNode stmt : block.getStmts()) {
			stmt.accept(this, context);
		}
	}

	public void visit(ElseStatement elseStatement, U context) {
		elseStatement.getBlock().accept(this, context); 
	}

	public void visit(IfStatement ifStatement, U context) {
		ifStatement.getCondition().accept(this, context);
		ifStatement.getBlock().accept(this, context);
	}

	public void visit(Question question, U context) {
		// no further action required
	}
	
	public void visit(ComputedQuestion computedQuestion, U context) {
		computedQuestion.getExpr().accept(this, context);
	}

	private <U> void dfs(BinaryExpr e, Visitor v, U context) {
		assert e.getLhs() != null;
		assert e.getRhs() != null;
		e.getLhs().accept(v, context);
		e.getRhs().accept(v, context);
	}
}
