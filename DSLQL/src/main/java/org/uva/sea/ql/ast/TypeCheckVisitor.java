package org.uva.sea.ql.ast;

import java.util.ArrayList;
import java.util.List;

import org.uva.sea.ql.ast.expr.*;
import org.uva.sea.ql.errors.QLError;

public class TypeCheckVisitor extends LeftDFSVisitor {
	
	private List<QLError> errorMessages;
	
	public TypeCheckVisitor() {
		errorMessages = new ArrayList<QLError>();
	}
	
	@Override
	public void visit(And and) {
		childrenEqualityWrapper(and);
		childrenOfType(and, Type.BOOLEAN);
		super.visit(and);
	}

	@Override
	public void visit(Or or) {
		childrenEqualityWrapper(or);
		childrenOfType(or, Type.BOOLEAN);
		super.visit(or);
	}

	@Override
	public void visit(Not not) {
		childrenOfType(not, Type.BOOLEAN);
		super.visit(not);
	}

	@Override
	public void visit(Eq eq) {
		childrenEqualityWrapper(eq);
		super.visit(eq);
	}

	@Override
	public void visit(GEq geq) {
		childrenEqualityWrapper(geq);
		childrenOfType(geq, Type.INT);
		super.visit(geq);
	}

	@Override
	public void visit(GT gt) {
		childrenEqualityWrapper(gt);
		childrenOfType(gt, Type.INT);
		super.visit(gt);
	}

	@Override
	public void visit(LEq leq) {
		childrenEqualityWrapper(leq);
		childrenOfType(leq, Type.INT);
		super.visit(leq);
	}

	@Override
	public void visit(LT lt) {
		childrenEqualityWrapper(lt);
		childrenOfType(lt, Type.INT);
		super.visit(lt);
	}

	@Override
	public void visit(NEq neq) {
		childrenEqualityWrapper(neq);
		super.visit(neq);
	}

	@Override
	public void visit(Add add) {
		childrenEqualityWrapper(add);
		childrenOfType(add, Type.INT);
		super.visit(add);
	}

	@Override
	public void visit(Sub sub) {
		childrenEqualityWrapper(sub);
		childrenOfType(sub, Type.INT);
		super.visit(sub);
	}

	@Override
	public void visit(Div div) {
		childrenEqualityWrapper(div);
		childrenOfType(div, Type.INT);
		super.visit(div);
	}

	@Override
	public void visit(Mul mul) {
		childrenEqualityWrapper(mul);
		childrenOfType(mul, Type.INT);
		super.visit(mul);
	}

	@Override
	public void visit(Neg neg) {
		childrenOfType(neg, Type.INT);
		super.visit(neg);
	}

	@Override
	public void visit(Pos pos) {
		childrenOfType(pos, Type.INT);
		super.visit(pos);
	}

	private void childrenEqualityWrapper(BinaryExpr expr) {
		if (! childrenEqualType(expr)) {
			String errorMessage = "has children of unequal type: (" 
					+ expr.getLhs().getType().name() 
					+ " | "
					+ expr.getRhs().getType().name()
					+ ")";
			errorMessages.add(new QLError(expr, errorMessage));
		}
	}
	
	private void childrenOfType(BinaryExpr expr, Type type) {
		if (! exprTypeCheck(expr, type)) {
			String errorMessage = "has children of the wrong type: ("
					+ expr.getLhs().getType().name() 
					+ " | "
					+ expr.getRhs().getType().name()
					+ ") \n"
					+ "All children should be of type " + type.name();
			errorMessages.add(new QLError(expr, errorMessage));
		}
	}
	
	private void childrenOfType(UnaryExpr expr, Type type) {
		if (! exprTypeCheck(expr, type)) {
			String errorMessage = "has a child of the wrong type: ("
					+ expr.getChild().getType().name() 
					+ ")"
					+ "The child should be of type " + type.name();
			errorMessages.add(new QLError(expr, errorMessage));
		}
	}
	
	private boolean childrenEqualType(BinaryExpr expr) {
		Expr lhs = expr.getLhs();
		Expr rhs = expr.getRhs();
		return lhs.getType().equals(rhs.getType());
	}

	private boolean exprTypeCheck(BinaryExpr expr, Type type) {
		Expr lhs = expr.getLhs();
		Expr rhs = expr.getRhs();
		return lhs.getType().equals(type) && rhs.getType().equals(type);
	}
	
	private boolean exprTypeCheck(UnaryExpr expr, Type type) {
		Expr lhs = expr.getChild();
		return lhs.getType().equals(type);
	}
	
	public List<QLError> getErrorMessages() {
		return this.errorMessages;
	}
	
}
