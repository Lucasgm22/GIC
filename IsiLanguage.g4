grammar IsiLanguage;

@header{
    import java.util.List;
	import java.util.Stack;
	import symbols.DataType;
	import symbols.Identifier;
	import symbols.SymbolTable;
	import expressions.*;
	import ast.*;
	import exception.*;
}

@members{
	private SymbolTable symbolTable = new SymbolTable();
	private DataType currentType;
	private ExpressionTree expression;
	private ExpressionTree _leftExpression;
	private ExpressionTree _rightExpression;
	private String _relOp;
	private char operator;
	private DataType leftDT;
	private DataType rightDT;
	private String   idAtribuido;
	private String   text;
	private String textContent;
	private Program  program = new Program();
	private int blockLvl = 0;
	private Stack<List<AbstractCommand>> stack = new Stack<>();
	private Stack<CmdIf> stackIfCmds = new Stack<>();
	private Stack<CmdWhile> stackWhileCmds = new Stack<>();
	private List<AbstractCommand> curThread;
	private BinaryRelationalExpression _bExpression;
	
	public void init() {
		program.setSymbolTable(symbolTable);
	}

	public void generateObjectCode(String filename, TargetLang target) {
	    if (target == TargetLang.ALL) {
           throw new IllegalStateException("ALL does not generate code");
        }
		program.generateTarget(filename, target);
	}
	
	public void runInterpreter() {
		program.run();
	}

	public void stopInterpreter() {
	    program.stop();
	}

	public List<Identifier> getUnassignedIdentifiers() {
	    return program.getUnassignedIdentifiers();
	}

    public List<Identifier> getAssignedUnusedIdentifiers() {
    	return program.getAssignedUnusedIdentifiers();
    }

	private void validateBinaryOperation(int line, int column) {
		if (leftDT != null && leftDT != rightDT) {
    	    throw new IsiTypeMismatchException(leftDT, rightDT, line, column);
        }
    }

    private void validateId(String idTxt, Identifier id, boolean validateValue, int line, int column) {
    	if (id == null) {
    	    throw new IsiUndeclaredVariableException(idTxt, line, column);
    	}
    	if (validateValue) {
    	    if ((id == null || !id.isAssigned())) {
                throw new IsiUnassignedVariableException(idTxt, line, column);
            }
            id.setUsed(true);
        }
    }
}
programa  : 'programa' {
             stack.push(program.getComandos());
           } decl* cmd* 'fimprog.'
           {
             program.setComandos(stack.pop());
           }
		  ;
		  
decl	  : tipo lista_var PF
		  ;
		 
tipo	  : 'INTEIRO' { currentType = DataType.INTEGER; }
          | 'REAL'    { currentType = DataType.REAL; }
          | 'TEXTO'    { currentType = DataType.TEXT; }
          ;

lista_var : ID {
                 Identifier dcId = new Identifier(_input.LT(-1).getText(), currentType);
                 symbolTable.add(_input.LT(-1).getText(), dcId);
                 CmdDecl _decl = new CmdDecl(dcId, blockLvl, _input.LT(-1).getLine(), _input.LT(-1).getCharPositionInLine());
                 stack.peek().add(_decl);
                 }
           (VIRG
           	ID {
           	     Identifier dcId2 = new Identifier(_input.LT(-1).getText(), currentType);
           	     symbolTable.add(_input.LT(-1).getText(), dcId2);
           	     CmdDecl _decl2 = new CmdDecl(dcId2, blockLvl, _input.LT(-1).getLine(), _input.LT(-1).getCharPositionInLine());
           	     stack.peek().add(_decl2);
           	     }
           )*
   		  ;
   		  
cmd		  : cmdAttr | cmdRead | cmdWrite | cmdIf | cmdWhile | cmdDoWhile
		  ;

cmdDoWhile : 'execute' {
                curThread = new ArrayList<AbstractCommand>();
                stack.push(curThread);
                blockLvl += 1;
              }
              cmd+
              'enquanto'
              AP bRelationalExpr FP {
                blockLvl -= 1;
                CmdDoWhile _cmdDoWhile = new CmdDoWhile(blockLvl, _bExpression, stack.pop());
                stack.peek().add(_cmdDoWhile);
                _bExpression = null;
              }  PF
           ;

cmdWhile  : 'enquanto' AP bRelationalExpr FP 'execute'
            {
                CmdWhile _cmdWhile = new CmdWhile(blockLvl, _bExpression);
                _bExpression = null;
                stackWhileCmds.push(_cmdWhile);
                blockLvl += 1;
                curThread = new ArrayList<AbstractCommand>();
                stack.push(curThread);
            }
            cmd+
            {
                stackWhileCmds.peek().setListCommands(stack.pop());
            }'fimenquanto' PF {
                stack.peek().add(stackWhileCmds.pop());
                blockLvl -=1;
            }
          ;
		  
cmdIf     : 'se' AP bRelationalExpr FP 'execute'
            {
                CmdIf _cmdIf = new CmdIf(blockLvl, _bExpression);
                _bExpression = null;
                stackIfCmds.push(_cmdIf);
                blockLvl += 1;
                curThread = new ArrayList<AbstractCommand>();
                stack.push(curThread);
            }cmd+
            {
               stackIfCmds.peek().setListTrue(stack.pop());
            }('senao'
             {
               curThread = new ArrayList<AbstractCommand>();
               stack.push(curThread);
             }cmd+
             {
               stackIfCmds.peek().setListFalse(stack.pop());
             })? 'fimse' PF {
                stack.peek().add(stackIfCmds.pop());
                blockLvl -= 1;
             }
		  ; 
		  
cmdRead   : 'leia' AP ID {
				Identifier id = symbolTable.get(_input.LT(-1).getText());
				validateId(id.getText(), id, true, _input.LT(-1).getLine(), _input.LT(-1).getCharPositionInLine());
				CmdRead _read = new CmdRead(id, blockLvl);
				stack.peek().add(_read);
			 }
			 FP PF
		  ;		 
		  
cmdWrite  : 'escreva' AP (
	         ID {
	         	Identifier id = symbolTable.get(_input.LT(-1).getText());
				validateId(id.getText(), id, true, _input.LT(-1).getLine(), _input.LT(-1).getCharPositionInLine());
	         	CmdWrite _write = new CmdWrite(id, blockLvl);
	         	stack.peek().add(_write);
	         } 
	         | 
	         TEXT {
	         	CmdWrite _write = new CmdWrite(_input.LT(-1).getText(), blockLvl);
	         	stack.peek().add(_write);
	         	
	         }
             ) FP PF
          ;		      		  
   		  
cmdAttr   : ID {
                idAtribuido = _input.LT(-1).getText();
				validateId(idAtribuido, symbolTable.get(idAtribuido), false,_input.LT(-1).getLine(), _input.LT(-1).getCharPositionInLine());
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
                        if (blockLvl == 0) {
                            id.setValue(expression.eval());
                            symbolTable.add(idAtribuido, id);
                        }

					    _attr = new CmdAttrib(id, expression, blockLvl);
					} else {
                        if (blockLvl == 0) {
                            id.setValueText(textContent);
                            symbolTable.add(idAtribuido, id);
					    }
					    _attr = new CmdAttrib(id, textContent, blockLvl);
					}
					stack.peek().add(_attr);
					textContent = null;
					expression = null;
					leftDT = null;
					rightDT = null;
				}
		  ;   		  
		  
expr	  : termo exprl*
          ;

bRelationalExpr: {
                                 expression = new ExpressionTree();
                             }
                             expr {
                                 _leftExpression = expression;
                                 expression = null;
                             } OPREL {
                                 _relOp = _input.LT(-1).getText();
                                 expression = new ExpressionTree();
                             } expr {
                                 _rightExpression = expression;
                                 expression = null;
                                 _bExpression = new BinaryRelationalExpression(_leftExpression, _rightExpression,_relOp);
                                 _leftExpression = null;
                                 _rightExpression = null;
                                 _relOp = null;
                             }
               ;
          
termo     : (NUMBER | NUMBERDEC)
			{
			    if (leftDT != null) {
				    rightDT = _input.LT(-1).getType() == 15 ? DataType.REAL : DataType.INTEGER;
				    validateBinaryOperation(_input.LT(-1).getLine(), _input.LT(-1).getCharPositionInLine());
				}
				else {
				    leftDT = _input.LT(-1).getType() == 15 ? DataType.REAL : DataType.INTEGER;
				}

				expression.addOperand(new NumberExpression(Double.parseDouble(_input.LT(-1).getText()), rightDT));
			}
		  |
		    TEXT {
		       rightDT = DataType.TEXT;
		       validateBinaryOperation(_input.LT(-1).getLine(), _input.LT(-1).getCharPositionInLine());

		       textContent = _input.LT(-1).getText();
		       textContent = textContent.substring(1, textContent.length() -1);
		    }
		  |
			ID {
				if (!symbolTable.exists(_input.LT(-1).getText())){
					throw new IsiUndeclaredVariableException(_input.LT(-1).getText(), _input.LT(-1).getLine(), _input.LT(-1).getCharPositionInLine());
				}
				rightDT = symbolTable.get(_input.LT(-1).getText()).getType();
				validateBinaryOperation(_input.LT(-1).getLine(), _input.LT(-1).getCharPositionInLine());

				Identifier id = symbolTable.get(_input.LT(-1).getText());
				validateId(id.getText(), id, true,_input.LT(-1).getLine(), _input.LT(-1).getCharPositionInLine());

				if (rightDT == DataType.TEXT) {
				    textContent = id.getValueText();
				} else {

                	if (id.getValue() != null || id.getValueText() != null){
                		expression.addOperand(new IDExpression(id));
                	}
                	else{
                		throw new IsiUndeclaredVariableException(_input.LT(-1).getText(), _input.LT(-1).getLine(), _input.LT(-1).getCharPositionInLine());
                	}
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
				    throw new IsiIllegalOperationException(operator, leftDT, _input.LT(-1).getLine(), _input.LT(-1).getCharPositionInLine());
				}
				expression.addOperator(new OperatorExpression(operator));
			} 
			termo

          ;		         
		  
NUMBER	  : ('-')?[0-9]+
		  ;

NUMBERDEC : ('-')?[0-9]+('.'[0-9]+)
          ;
		  
TEXT 	  : '"' ([a-z]|[A-Z]|[0-9]|' '|'\t'|'!'|'-'|'<'|'>'|'=')* '"'
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
          
OPREL     : '>' | '>=' | '<' | '<=' | '==' | '!='
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