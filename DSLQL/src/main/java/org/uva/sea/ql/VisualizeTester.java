package org.uva.sea.ql;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.swing.JPanel;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.uva.sea.ql.ast.form.Form;
import org.uva.sea.ql.experiment.ASTVisualizer;
import org.uva.sea.ql.parser.QLLexer;
import org.uva.sea.ql.parser.QLParser;
import org.uva.sea.ql.parser.QLParser.FormContext;

public class VisualizeTester {
	
	static String FA = "D:\\Master\\Software Construction\\Github\\Kevin van den Bekerom\\DSLQL\\src\\main\\resources\\SampleForm.txt";
	String FB = "D:\\Master\\Software Construction\\Github\\Kevin van den Bekerom\\DSLQL\\src\\main\\resources\\DependancyCheckUnsafe.txt";
	String FC = "D:\\Master\\Software Construction\\Github\\Kevin van den Bekerom\\DSLQL\\src\\main\\resources\\DependancyCheckSafe.txt";
	String FD = "D:\\Master\\Software Construction\\Github\\Kevin van den Bekerom\\DSLQL\\src\\main\\resources\\TypeCheckTest.txt";
	
	public static void main(String [] args)  {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ASTVisualizer frame = new ASTVisualizer();
					testDrawVisitor(getParser(parseFile(FA)), frame);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void testDrawVisitor(QLParser parser, ASTVisualizer visualizer) {
		FormContext fc = parser.form(); // begin parsing at init rule
		Form f = new Form("testForm", fc.b.result);
		visualizer.drawQuestions(f, visualizer.contentPane);
	}
	
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
	
	public  static String parseFile(String filepath) throws Exception {

		BufferedReader br = new BufferedReader(new FileReader(filepath));
		try {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		    String everything = sb.toString();
		    return everything;
		}
		catch (Exception e) {
			return e.getMessage();
		} finally {
			br.close();
		}
	}
}
