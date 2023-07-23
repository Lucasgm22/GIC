grammar IsiLanguage;

@header{
	import java.util.ArrayList;
	import symbols.DataType;
	import symbols.Identifier;
	import symbols.SymbolTable;
	import expressions.*;
	import ast.*;
	
}

@members{
	private SymbolTable symbolTable = new SymbolTable();
	private DataType currentType;
	private ExpressionTree expression;
	private char operator;
	private DataType leftDT;
	private DataType rightDT;
	private String   idAtribuido;
	private String   text;
	private String textContent;
	private Program  program = new Program();
	
	public void init(){
		program.setSymbolTable(symbolTable);
	}
		
	public void exibirID(){
		symbolTable.getSymbols().values().stream().forEach((id)->System.out.println(id));
	}
	
	public void generateObjectCode(){
		program.generateTarget();
	}
	
	public void runInterpreter(){
		program.run();
	}

	private void validateBinaryOperation() {
		if (leftDT != rightDT) {
    	    throw new RuntimeException("Semantic ERROR - Type Mismatching "+leftDT+ "-"+rightDT);
        }
    }
}
programa  : 'programa' decl+ cmd+ 'fimprog.'
		  ;
		  
decl	  : tipo lista_var PF
		  ;
		 
tipo	  : 'INTEGER' { currentType = DataType.INTEGER; }
          | 'REAL'    { currentType = DataType.REAL; }
          | 'TEXT'    { currentType = DataType.TEXT; }
          ;
         
lista_var : ID { symbolTable.add(_input.LT(-1).getText(), new Identifier(_input.LT(-1).getText(), currentType)); } 
           (VIRG 
           	ID { symbolTable.add(_input.LT(-1).getText(), new Identifier(_input.LT(-1).getText(), currentType)); }
           )*
   		  ;
   		  
cmd		  : cmdAttr | cmdRead | cmdWrite | cmdIf
		  ;
		  
cmdIf     : 'se' AP expr OPREL expr FP 'entao' cmd+ ('senao' cmd+)? 'fimse' PF		 
		  ; 
		  
cmdRead   : 'leia' AP ID {
				Identifier id = symbolTable.get(_input.LT(-1).getText());
				if (id == null){
					throw new RuntimeException("Undeclared Variable");
				}
				CmdRead _read = new CmdRead(id);
				program.getComandos().add(_read);
			 }
			 FP PF
		  ;		 
		  
cmdWrite  : 'escreva' AP (
	         ID {
	         	Identifier id = symbolTable.get(_input.LT(-1).getText());
	         	if (id == null){
	         		throw new RuntimeException("Undeclared Variable");	         		
	         	}
	         	CmdWrite _write = new CmdWrite(id);
	         	program.getComandos().add(_write);
	         } 
	         | 
	         TEXT {
	         	CmdWrite _write = new CmdWrite(_input.LT(-1).getText());
	         	program.getComandos().add(_write);
	         	
	         }
             ) FP PF
          ;		      		  
   		  
cmdAttr   : ID {
				idAtribuido = _input.LT(-1).getText();
				if (!symbolTable.exists(_input.LT(-1).getText())){
					throw new RuntimeException("Semantic ERROR - Undeclared Identifier");
				}
				leftDT = symbolTable.get(_input.LT(-1).getText()).getType();
				rightDT = null;
			}
         	ATTR
         	{
         	    expression = new ExpressionTree();
         	}
         	expr PF
				{
					// logica para atribuir o valor da expressao no identificador
					AbstractCommand _attr;
                    Identifier id = symbolTable.get(idAtribuido);
					if (!DataType.TEXT.equals(id.getType())) {

                        id.setValue(expression.eval());
                        symbolTable.add(idAtribuido, id);

                        System.out.println("EVAL ["+expression+"] = "+expression.eval());
					
					    _attr = new CmdAttrib(id, expression);
					} else {
					    _attr = new CmdAttribString(id, textContent);
					}
					program.getComandos().add(_attr);
					expression = null;					
				}
		  ;   		  
		  
expr	  : termo exprl*
          ;
          
termo     : (NUMBER | NUMBERDEC)
			{
				rightDT = _input.LT(-1).getType() == 13 ? DataType.REAL : DataType.INTEGER;
				validateBinaryOperation();
				expression.addOperand(new NumberExpression(Double.parseDouble(_input.LT(-1).getText()), rightDT));
			}
		  |
		    TEXT {
		       rightDT = DataType.TEXT;
		       validateBinaryOperation();

		       textContent = _input.LT(-1).getText();
		    }
		  |
			ID {
				if (!symbolTable.exists(_input.LT(-1).getText())){
					throw new RuntimeException("Semantic ERROR - Undeclared Identifier: "+_input.LT(-1).getText());
				}
				rightDT = symbolTable.get(_input.LT(-1).getText()).getType();
				validateBinaryOperation();
				
				Identifier id = symbolTable.get(_input.LT(-1).getText());
				if (id.getValue() != null){
					expression.addOperand(new IDExpression(id));
				}
				else{
					throw new RuntimeException("Semantic ERROR - Unassigned variable");
				}
			}
			|
			AP {
               		        expression.addOperator(new OperatorExpression('('));
            }
			expr
			FP {
               		        expression.addOperator(new OperatorExpression(')'));
            }
		  ;
		  
exprl     : (SUM | SUB | MUL | DIV) {
				operator = _input.LT(-1).getText().charAt(0);
				if (leftDT == DataType.TEXT) {
				    throw new RuntimeException("Semantic ERROR - Cannot apply operand " + operator + " to TEXT");
				}
				expression.addOperator(new OperatorExpression(operator));
			} 
			termo

          ;		         
		  
NUMBER	  : ('-')?[0-9]+
		  ;

NUMBERDEC : ('-')?[0-9]+('.'[0-9]+)
          ;
		  
TEXT 	  : '"' ([a-z]|[A-Z]|[0-9]|' '|'\t'|'!'|'-')* '"'
		  ;		  
		  
ATTR	  : ':='
   		  ;		
   		  
SUM	      : '+'
		  ;
		  
SUB		  : '-'		     		    
          ;

MUL       : '*'
          ;

DIV       : '/'
          ;
          
OPREL     : '>' | '>=' | '<' | '<=' | '==' | '<>'
 		  ;          
   		  
ID		  : [a-z] ([a-z]|[A-Z]|[0-9])*
          ;
          
VIRG      : ','
          ;
          
PF        : '.'
          ;
          
AP	      : '('          
	      ;
	      
FP 		  : ')'
          ;	      
BLANK     : (' '| '\t' | '\n' | '\r') -> skip
          ;