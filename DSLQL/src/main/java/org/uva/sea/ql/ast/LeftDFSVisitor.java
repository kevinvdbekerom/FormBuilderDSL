package org.uva.sea.ql.ast;

import org.uva.sea.ql.ast.expr.*;
import org.uva.sea.ql.ast.stat.*;


/* Visitor that visits every node in the AST based on Depth First Search 
 * starting with the leftmost node.
 */
public class LeftDFSVisitor implements Visitor {
	protected Visitor v;
	
	protected LeftDFSVisitor() {
		this.v = this;
	}
	
	public void visit(Expr expr) {

	}

	public void visit(BinaryExpr binExpr) {
		dfs(binExpr, this);	
	}

	public void visit(UnaryExpr unExpr) {
		
	}
	
	public void visit(ASTNode node) {
		
	}

	public void visit(And and) {
		dfs(and, this);
	}

	public void visit(Or or) {
		dfs(or, this);
	}

	public void visit(Not not) {
		not.getChild().accept(this);
	}

	public void visit(Eq eq) {
		dfs(eq, this);
	}

	public void visit(GEq geq) {
		dfs(geq, this);
	}

	public void visit(GT gt) {
		dfs(gt, this);
	}

	public void visit(LEq leq) {
		dfs(leq, this);
	}

	public void visit(LT lt) {
		dfs(lt, this);
	}

	public void visit(NEq neq) {
		dfs(neq, this);
	}

	public void visit(Add add) {
		dfs(add, this);
	}

	public void visit(Sub sub) {
		dfs(sub, this);
	}

	public void visit(Div div) {
		dfs(div, this);
	}

	public void visit(Mul mul) {
		dfs(mul, this);
	}

	public void visit(Neg neg) {
		neg.getChild().accept(this);
	}

	public void visit(Pos pos) {
		pos.getChild().accept(this);
	}

	public void visit(IntegerLiteral integerLiteral) {
		// leaf node
	}

	public void visit(StringLiteral stringLiteral) {
		// leaf node
	}

	public void visit(BooleanLiteral booleanLiteral) {
		// leaf node
	}

	public void visit(Variable variable) {
		// leaf node
	}

	public void visit(Block block) {
		for (ASTNode stmt : block.getStmts()) {
			stmt.accept(this);
		}
	}

	public void visit(ElseStatement elseStatement) {
		elseStatement.getBlock().accept(this); 
	}

	public void visit(IfStatement ifStatement) {
		ifStatement.getClause().accept(this);
		ifStatement.getBlock().accept(this);
	}

	public void visit(Question question) {
		// no further action required
	}
	
	public void visit(ComputedQuestion computedQuestion) {
		computedQuestion.getComputedQuestion().accept(this);
	}

	public void dfs(BinaryExpr e, Visitor v) {
		assert e.getLhs() != null;
		assert e.getRhs() != null;
		e.getLhs().accept(v);
		e.getRhs().accept(v);
	}
}
