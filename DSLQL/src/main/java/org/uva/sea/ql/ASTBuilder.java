package org.uva.sea.ql;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.uva.sea.ql.ast.form.Form;
import org.uva.sea.ql.parser.QLLexer;
import org.uva.sea.ql.parser.QLParser;
import org.uva.sea.ql.parser.QLParser.FormContext;

public class ASTBuilder {
	
	public Form buildAST(String fileLocation) throws IOException {
		QLParser parser = getParser(fileToString(fileLocation));
		FormContext fc = parser.form(); // begin parsing at init rule
		Form form = new Form(fc.formName.getText(), fc.b.result);
		return form;
	}
	
	private String fileToString(String location) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(location));
		try {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		    
		    return sb.toString();
		} finally {
			br.close(); 
		}
	}
	
	private static QLParser getParser(String in){
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
