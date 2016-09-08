package org.uva.sea.ql.typechecker;

import java.util.ArrayList;
import java.util.List;

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
import org.uva.sea.ql.type.BoolType;
import org.uva.sea.ql.type.MoneyType;
import org.uva.sea.ql.type.Type;
import org.uva.sea.ql.visit.LeftDFSVisitor;
import org.uva.sea.ql.ast.form.TypeMap;
import org.uva.sea.ql.ast.stat.ComputedQuestion;
import org.uva.sea.ql.ast.stat.IfStatement;
import org.uva.sea.ql.errors.QLError;

public class TypesChecker extends LeftDFSVisitor<TypeMap> {

	//of context param weg, of 
	private List<QLError> errors;
	private TypeMap context;

	private TypesChecker(TypeMap context) {
		this.context = context;
		errors = new ArrayList<QLError>();
	}
	//TODO: encapsulate static method calls private copnstructor
	//

	@Override
	public void visit(And and, TypeMap context) {
		childrenOfType(and);
		super.visit(and, null);
	}

	@Override
	public void visit(Or or, TypeMap context) {
		childrenOfType(or);
		super.visit(or, null);
	}

	@Override
	public void visit(Not not, TypeMap context) {
		childrenOfType(not);
		super.visit(not, null);
	}

	@Override
	public void visit(Eq eq, TypeMap context) {
		childrenEqualityWrapper(eq);
		super.visit(eq, null);
	}

	@Override
	public void visit(GEq geq, TypeMap context) {
		childrenOfType(geq);
		super.visit(geq, null);
	}

	@Override
	public void visit(GT gt, TypeMap context) {
		childrenOfType(gt);
		super.visit(gt, null);
	}

	@Override
	public void visit(LEq leq, TypeMap context) {
		childrenOfType(leq);
		super.visit(leq, null);
	}

	@Override
	public void visit(LT lt, TypeMap context) {
		childrenOfType(lt);
		super.visit(lt, null);
	}

	@Override
	public void visit(NEq neq, TypeMap context) {
		childrenEqualityWrapper(neq);
		super.visit(neq, null);
	}

	@Override
	public void visit(Add add, TypeMap context) {
		childrenOfType(add);
		super.visit(add, null);
	}

	@Override
	public void visit(Sub sub, TypeMap context) {
		childrenOfType(sub);
		super.visit(sub, null);
	}

	@Override
	public void visit(Div div, TypeMap context) {
		Type lhs = div.getLhs().getType(context);
		Type rhs = div.getRhs().getType(context);
		if (lhs.equals(new MoneyType()) && lhs.equals(rhs)) {
			String message = "You cannot divide money by money!";
			errors.add(new QLError(div, message));
		}
		childrenOfType(div);
		super.visit(div, null);
	}
// extract method
	@Override
	public void visit(Mul mul, TypeMap context) {
		Type lhs = mul.getLhs().getType(context);
		Type rhs = mul.getRhs().getType(context);
		if (lhs.equals(new MoneyType()) && lhs.equals(rhs)) {
			String message = "You cannot multiply money by money!";
			errors.add(new QLError(mul, message));
		}
		childrenOfType(mul);
		super.visit(mul, null);
	}

	@Override
	public void visit(Neg neg, TypeMap context) {
		childrenOfType(neg);
		super.visit(neg, null);
	}

	@Override
	public void visit(Pos pos, TypeMap context) {
		childrenOfType(pos);
		super.visit(pos, null);
	}

	@Override
	public void visit(IfStatement ifStatement, TypeMap context) {
		Expr root = ifStatement.getCondition();
		if (! root.getType(context).equals(new BoolType())) { 
			String message = "evaluates to the wrong type! Type should be Boolean but is " 
					+ root.getType(context).toString();
			errors.add(new QLError(ifStatement, message)); 
		}
		super.visit(ifStatement, context);
	}

	@Override 
	public void visit(ComputedQuestion compQuestion, TypeMap context) {
		Expr root = compQuestion.getExpr();
		if (! root.getType(context).equals(new BoolType())) { 
			String message = "evaluates to the wrong type! Type should be " 
					+ compQuestion.getType().toString() 
					+ " but is " 
					+ root.getType(context).toString();
			errors.add(new QLError(compQuestion, message)); 
		}
		super.visit(compQuestion, context);
	}

	private void childrenEqualityWrapper(BinaryExpr expr) {
		if (! childrenEqualType(expr)) {
			String errorMessage = "has children of unequal type: (" 
					+ expr.getLhs().getType(context).toString() 
					+ " | "
					+ expr.getRhs().getType(context).toString()
					+ ")";
			errors.add(new QLError(expr, errorMessage));
		}
	}

	private void childrenOfType(BinaryExpr expr) {
		if (! exprTypeCheck(expr)) {
			String errorMessage = "of type " + expr.getType(context).toString()
					+ " has children of the wrong type: ("
					+ expr.getLhs().getType(context).toString() 
					+ " | "
					+ expr.getRhs().getType(context).toString()
					+ ")";
			errors.add(new QLError(expr, errorMessage));
		}
	}

	private void childrenOfType(UnaryExpr expr) {
		if (! exprTypeCheck(expr)) {
			String errorMessage = "of type " + expr.getType(context).toString()
					+ " has children of the wrong type: ("
					+ expr.getChild().getType(context).toString() 
					+ ")";
			errors.add(new QLError(expr, errorMessage));
		}
	}
//TODO: names!!!!
	private boolean childrenEqualType(BinaryExpr expr) {
		Expr lhs = expr.getLhs();
		Expr rhs = expr.getRhs();
		return lhs.getType(context).equals(rhs.getType(context));
	}
//childrenAreOfCompatibleType
	private boolean exprTypeCheck(BinaryExpr expr) {
		Expr lhs = expr.getLhs();
		Expr rhs = expr.getRhs();
		Type type = expr.getType(context);
		return type.equals(lhs.getType(context)) && type.equals(rhs.getType(context));
	}

	private boolean exprTypeCheck(UnaryExpr expr) {
		Expr lhs = expr.getChild();
		return lhs.getType(context).equals(expr.getType(context));
	}

	public static List<QLError> getErrorMessages(ASTNode startNode, TypeMap context) {
		TypesChecker v = new TypesChecker(context);
		startNode.accept(v, context);
		return v.errors;
	}

}
