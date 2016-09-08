package org.uva.sea.ql.ast.expr;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.uva.sea.ql.ast.expr.*;
/**
 * Unit test for simple App.
 */
public class ExprTest extends TestCase {
	
	// Building Blocks for making Expressions
	private IntegerLiteral il = new IntegerLiteral(4);
	private StringLiteral sl = new StringLiteral("sl");
	private BooleanLiteral bTrue = new BooleanLiteral(true);
	private BooleanLiteral bFalse = new BooleanLiteral(false);
	
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public ExprTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( ExprTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
    
    public void testEvalLiteral() {
    	int value = il.eval(); // il.eval() == 4
    	assertEquals(4, value);
    }
    
    public void testEvalAdd() {
    	Add add = new Add(il, il); // il.eval() == 4
    	int value = add.eval(); // 4 + 4 = 8
    	assertEquals(8, value); 
    }
    
    public void testEvalOrderFull() {
    	IntegerLiteral il = new IntegerLiteral(4);
    	Add add = new Add(il, il); // 8
    	Mul mul = new Mul(add, il); // 32
    	assertEquals(32, (int) mul.eval());
    	LT lt = new LT(mul, new IntegerLiteral(33)); // true
    	assertEquals(true, (boolean) lt.eval());
    	And and = new And(lt, bTrue); // true
    	assertEquals(true, (boolean) and.eval());
    	Or or = new Or(and, bFalse); // true
    	boolean value = or.eval();
    	assertEquals(true, value);
    }
    
    public void testEvalNeg() {
    	IntegerLiteral ilNeg = new IntegerLiteral(-4);
    	IntegerLiteral ilPos = new IntegerLiteral(4);
    	Expr negToPos = new Neg(ilNeg);
    	Expr posToNeg = new Neg(ilPos);
    	assertEquals(4, negToPos.eval());
    	assertEquals(-4, posToNeg.eval());
    }
    
    public void testEvalComparison() {
    	
    }
    
    public void testEvalEqualityString() {
    	StringLiteral sl = new StringLiteral("sl");
    	StringLiteral sl2 = new StringLiteral("sl2");
    	Expr eq = new Eq(sl, sl);
    	Expr eq2 = new Eq(sl, sl2);
    	assertEquals(true, eq.eval());
    	assertEquals(false, eq2.eval());
    }
    
    //TODO: Fix this testcase to catch the right exception.
    public void testEvalIncompatibleLiterals() {
    	IntegerLiteral il = new IntegerLiteral(4);
    	StringLiteral sl = new StringLiteral("sl");
    	Add add = new Add(il, sl);
    	int value = add.eval();
    	assertEquals(0, value); // throws ClassCastException and returns 0;
    }
}
