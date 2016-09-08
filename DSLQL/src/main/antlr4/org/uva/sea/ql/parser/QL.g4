grammar QL;


@parser::header
{
import org.uva.sea.ql.ast.expr.logic.*;
import org.uva.sea.ql.ast.expr.math.*;
import org.uva.sea.ql.ast.expr.terminals.*;
import org.uva.sea.ql.ast.expr.*;
import org.uva.sea.ql.ast.stat.*;
import org.uva.sea.ql.ast.form.*;
import org.uva.sea.ql.type.*;
import org.uva.sea.ql.value.*;
}

/* Form Grammar Rules = Entry Point */
form returns [Form result]
	: FORM formName '{' b = block '}' EOF {
		$result = new Form($formName.text, $b.result);		
	};

formName : Ident;


block returns [Block result]
	@init {
		$result = new Block();	
	}
	: (ifStatement[$result] | elseStatement[$result] | question[$result])+;

ifStatement [Block result]
	: IF '(' orExpr ')' '{' block '}' {
		$result.add(new IfStatement($block.result, $orExpr.result, $IF.getLine()));
	}
	;
	
elseStatement [Block result]
	: ELSE '{' b = block '}' {
		$result.add(new ElseStatement($b.result));	
	}
	;
	
question [Block result]
	: v=variable ':' label t = type ('(' orExpr ')')+ {
		$result.add(new ComputedQuestion($v.text, $label.text, $t.result, $orExpr.result, $v.start.getLine()));}
	| v=variable ':' label t = type {
		$result.add(new Question($v.text, $label.text, $t.result, $v.start.getLine()));}
	;

variable returns [Expr result]
	: id=Ident {$result = new Variable($id.getText()); };
	
label : Str;

type returns [Type result]
	: INT {$result = new IntType();}
	| STR {$result = new StrType();}
	| BOOL {$result = new BoolType();}
	| MONEY {$result = new MoneyType();}
	;


/* Expression Grammar Rules */
unExpr returns [Expr result]
    :  '+' x=unExpr { $result = new Pos($x.result, $x.start.getLine()); }
    |  '-' x=unExpr { $result = new Neg($x.result, $x.start.getLine()); }
    |  '!' x=unExpr { $result = new Not($x.result, $x.start.getLine()); }
    |  y=primary    { $result = $y.result; }
    ;  
    
primary returns [Expr result]
	: literal {$result = $literal.result;}
	| variable {$result = $variable.result;}
	| '(' orExpr + ')' {$result = $orExpr.result;}
	;

literal returns [Expr result]
	: booleanLiteral {$result = $booleanLiteral.result;}
	| integerLiteral {$result = $integerLiteral.result;}
	| stringLiteral {$result = $stringLiteral.result;}
	;

booleanLiteral returns [Expr result]
	: Bool {$result = new BooleanLiteral(Boolean.valueOf($Bool.getText()));}
	;

integerLiteral returns [Expr result]
	: Int {$result = new IntegerLiteral(Integer.valueOf($Int.getText()));}
	;

stringLiteral returns [Expr result]
	: Str {$result = new StringLiteral($Str.getText());}
	;
    
mulExpr returns [Expr result]
    :   lhs=unExpr { $result=$lhs.result; } ( op=( '*' | '/' ) rhs=unExpr 
    { 
      if ($op.text.equals("*")) {
        $result = new Mul($result, $rhs.result, $rhs.start.getLine());
      }
      if ($op.text.equals("/")) {
        $result = new Div($result, $rhs.result, $rhs.start.getLine());      
      }
    })*
    ;
    
addExpr returns [Expr result]
    :   lhs=mulExpr { $result=$lhs.result; } ( op=('+' | '-') rhs=mulExpr
    { 
      if ($op.text.equals("+")) {
        $result = new Add($result, $rhs.result, $rhs.start.getLine());
      }
      if ($op.text.equals("-")) {
        $result = new Sub($result, $rhs.result, $rhs.start.getLine());      
      }
    })*
    ;
  
relExpr returns [Expr result]
    :   lhs=addExpr { $result=$lhs.result; } ( op=('<'|'<='|'>'|'>='|'=='|'!=') rhs=addExpr 
    { 
      if ($op.text.equals("<")) {
        $result = new LT($result, $rhs.result, $rhs.start.getLine());
      }
      if ($op.text.equals("<=")) {
        $result = new LEq($result, $rhs.result, $rhs.start.getLine());      
      }
      if ($op.text.equals(">")) {
        $result = new GT($result, $rhs.result, $rhs.start.getLine());
      }
      if ($op.text.equals(">=")) {
        $result = new GEq($result, $rhs.result, $rhs.start.getLine());      
      }
      if ($op.text.equals("==")) {
        $result = new Eq($result, $rhs.result, $rhs.start.getLine());
      }
      if ($op.text.equals("!=")) {
        $result = new NEq($result, $rhs.result, $rhs.start.getLine());
      }
    })*
    ;
    
andExpr returns [Expr result]
    :   lhs=relExpr { $result=$lhs.result; } ( op='&&' rhs=relExpr { $result = new And($result, $rhs.result, $rhs.start.getLine()); } )*
    ;
    

orExpr returns [Expr result]
    :   lhs=andExpr { $result = $lhs.result; } ( op='||' rhs=andExpr { $result = new Or($result, $rhs.result, $rhs.start.getLine()); } )*
    ;

// Tokens
WS  :	(' ' | '\t' | '\n' | '\r') -> channel(HIDDEN)
    ;

COMMENT 
     : '/*' .* '*/' -> channel(HIDDEN)
    ;
    
SLCOMMENT
	: '//' .* '\n' -> channel(HIDDEN)
	;

/* Keyword reservation */
BOOL : 'boolean';
INT : 'int';
STR : 'str';
IF : 'if';
ELSE : 'else';
FORM : 'form';
MONEY : 'money';
 
/* Literals */
Bool: ('true' | 'false');
Int: ('0'..'9')+;
Str: '"' ~('"')* '"' ;
Ident:   ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;
