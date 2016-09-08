package org.uva.sea.ql;

import java.io.BufferedReader;
import java.io.FileReader;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.gui.TreeViewer;
import org.uva.sea.ql.parser.QLLexer;
import org.uva.sea.ql.parser.QLParser;
import org.uva.sea.ql.parser.QLParser.FormContext;
import org.uva.sea.ql.typechecker.TypeChecker;
import org.uva.sea.ql.typechecker.TypesChecker;
import org.uva.sea.ql.ast.ASTNode;
import org.uva.sea.ql.errors.QLError;
import edu.emory.mathcs.backport.java.util.Arrays;

/**
 * Hello world!
 *
 */
public class App 
{
	public static void main(String [] args) throws Exception {
		String FA = "D:\\Master\\Software Construction\\Github\\Kevin van den Bekerom\\DSLQL\\src\\main\\resources\\SampleForm.txt";
		String FB = "D:\\Master\\Software Construction\\Github\\Kevin van den Bekerom\\DSLQL\\src\\main\\resources\\DependancyCheckIfStatements.txt";
		String FC = "D:\\Master\\Software Construction\\Github\\Kevin van den Bekerom\\DSLQL\\src\\main\\resources\\DependancyCheckSafe.txt";
		String FD = "D:\\Master\\Software Construction\\Github\\Kevin van den Bekerom\\DSLQL\\src\\main\\resources\\TypeCheckTest.txt";
		String FE = "D:\\Master\\Software Construction\\Github\\Kevin van den Bekerom\\DSLQL\\src\\main\\resources\\DuplicateQuestionsAndLabels.ql";
		
		
		BufferedReader br = new BufferedReader(new FileReader(FB));
		try {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		    String everything = sb.toString();
		    
		  //  testGrammar(getParser(everything));
		 //   getAST(getParser(everything));
		 //   System.out.println("Now testing dependancy!");
		   System.out.println("Now testing type checker");
		    testTypeChecker(getParser(everything));
		    
		//   System.out.println("Now testing type check");
		 //   testTypeCheck(getParser(everything));
		    
		//  System.out.println("Now testing question painter");
		 // testDrawVisitor(getParser(everything));
		    System.out.println(Integer.toString(5/2));
		    System.out.println(Double.toString(Math.round(5.0/2)));
		} finally {
		    br.close();
		    
		}
			
	}

	public static void testGrammar(QLParser parser){
		ParseTree tree = parser.form(); // begin parsing at init rule
		
		//show AST in GUI
		TreeViewer viewr = new TreeViewer(Arrays.asList(parser.getRuleNames()), tree);
        viewr.setScale(1.5);// scale a little

        viewr.open();
	}
	
	public static void testTypeChecker(QLParser parser) {
		FormContext fc = parser.form();
		ASTNode startNode = fc.b.result;
		TypeChecker tp = new TypeChecker();
		tp.printErrors(startNode);
	}

	
	public static void testTypeCheck(QLParser parser) {
		FormContext fc = parser.form();
		ASTNode startNode = fc.b.result;
		
		for (QLError error : TypesChecker.getErrorMessages(startNode, null)) {
			System.out.println(error.getErrorMessage());
		}
		
	}

	
	/*public static void getAST(QLParser parser){
		FormContext fc = parser.form(); // begin parsing at init rule
		NodeCollector v = new NodeCollector();
		fc.b.result.accept(v);
		
		for (Expr literal : v.getLiterals()) {
			System.out.println(literal.toString() + " " + literal.getType());
		}
		
	}*/

	public static QLParser getParser(String in){
		// create a CharStream that reads from standard input
			ANTLRInputStream input = new ANTLRInputStream(in);
			// create a lexer that feeds off of input CharStream
			QLLexer lexer = new QLLexer(input);
			// create a buffer of tokens pulled from the lexer
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			// create a parser that feeds off the tokens buffer
			QLParser parser = new QLParser(tokens);
			
			return parser;
	}

}
