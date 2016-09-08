package org.uva.sea.ql;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.uva.sea.ql.ast.expr.*;
/**
 * Unit test for simple App.
 */
public class AppTest 

	//
	

    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
    
    public void testEvalLiteral() {
    	IntegerLiteral il = new IntegerLiteral(4);
    	int value = il.eval();
    	assertEquals(4, value);
    }
    
    public void testEvalAdd() {
    	IntegerLiteral il = new IntegerLiteral(4);
    	Add add = new Add(il, il);
    	int value = add.eval();
    	assertEquals(8, value); // 4 + 4 = 8
    }
    
    public void testEvalFull() {
    	IntegerLiteral il = new IntegerLiteral(4);
    	Add add = new Add(il, il);
    	int value = add.eval();
    	assertEquals(8, value); // 4 + 4 = 8
    }
    
    public void testEvalIncompatibleLiterals() {
    	IntegerLiteral il = new IntegerLiteral(4);
    	StringLiteral sl = new StringLiteral("sl");
    	Add add = new Add(il, sl);
    	int value = add.eval();
    	assertEquals(0, value); // throws ClassCastException and returns 0;
    }
}
