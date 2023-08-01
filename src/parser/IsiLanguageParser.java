// Generated from IsiLanguage.g4 by ANTLR 4.13.0
package parser;

    import java.util.List;
	import java.util.Stack;
	import symbols.DataType;
	import symbols.Identifier;
	import symbols.SymbolTable;
	import expressions.*;
	import ast.*;
	import exception.*;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class IsiLanguageParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, NUMBER=14, NEG_NUMBER=15, NUMBERDEC=16, 
		NEG_NUMBER_DEC=17, TEXT=18, ATTR=19, SUM=20, SUB=21, MUL=22, DIV=23, OPREL=24, 
		ID=25, VIRG=26, PF=27, AP=28, FP=29, BLANK=30;
	public static final int
		RULE_programa = 0, RULE_decl = 1, RULE_tipo = 2, RULE_lista_var = 3, RULE_cmd = 4, 
		RULE_cmdDoWhile = 5, RULE_cmdWhile = 6, RULE_cmdIf = 7, RULE_cmdRead = 8, 
		RULE_cmdWrite = 9, RULE_cmdAttr = 10, RULE_expr = 11, RULE_bRelationalExpr = 12, 
		RULE_termo = 13, RULE_exprl = 14;
	private static String[] makeRuleNames() {
		return new String[] {
			"programa", "decl", "tipo", "lista_var", "cmd", "cmdDoWhile", "cmdWhile", 
			"cmdIf", "cmdRead", "cmdWrite", "cmdAttr", "expr", "bRelationalExpr", 
			"termo", "exprl"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'programa'", "'fimprog.'", "'INTEIRO'", "'REAL'", "'TEXTO'", "'execute'", 
			"'enquanto'", "'fimenquanto'", "'se'", "'senao'", "'fimse'", "'leia'", 
			"'escreva'", null, null, null, null, null, "':='", "'+'", "'-'", "'*'", 
			"'/'", null, null, "','", "'.'", "'('", "')'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, "NUMBER", "NEG_NUMBER", "NUMBERDEC", "NEG_NUMBER_DEC", "TEXT", 
			"ATTR", "SUM", "SUB", "MUL", "DIV", "OPREL", "ID", "VIRG", "PF", "AP", 
			"FP", "BLANK"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "IsiLanguage.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


		private SymbolTable symbolTable = new SymbolTable();
		private DataType currentType;
		private ExpressionTree expression;
		private ExpressionTree _leftExpression;
		private ExpressionTree _rightExpression;
		private String _relOp;
		private String operator;
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

	public IsiLanguageParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramaContext extends ParserRuleContext {
		public List<DeclContext> decl() {
			return getRuleContexts(DeclContext.class);
		}
		public DeclContext decl(int i) {
			return getRuleContext(DeclContext.class,i);
		}
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public ProgramaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_programa; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).enterPrograma(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).exitPrograma(this);
		}
	}

	public final ProgramaContext programa() throws RecognitionException {
		ProgramaContext _localctx = new ProgramaContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_programa);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(30);
			match(T__0);

			             stack.push(program.getComandos());
			           
			setState(35);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 56L) != 0)) {
				{
				{
				setState(32);
				decl();
				}
				}
				setState(37);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(41);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 33567424L) != 0)) {
				{
				{
				setState(38);
				cmd();
				}
				}
				setState(43);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(44);
			match(T__1);

			             program.setComandos(stack.pop());
			           
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeclContext extends ParserRuleContext {
		public TipoContext tipo() {
			return getRuleContext(TipoContext.class,0);
		}
		public Lista_varContext lista_var() {
			return getRuleContext(Lista_varContext.class,0);
		}
		public TerminalNode PF() { return getToken(IsiLanguageParser.PF, 0); }
		public DeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).enterDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).exitDecl(this);
		}
	}

	public final DeclContext decl() throws RecognitionException {
		DeclContext _localctx = new DeclContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_decl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(47);
			tipo();
			setState(48);
			lista_var();
			setState(49);
			match(PF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TipoContext extends ParserRuleContext {
		public TipoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tipo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).enterTipo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).exitTipo(this);
		}
	}

	public final TipoContext tipo() throws RecognitionException {
		TipoContext _localctx = new TipoContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_tipo);
		try {
			setState(57);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
				enterOuterAlt(_localctx, 1);
				{
				setState(51);
				match(T__2);
				 currentType = DataType.INTEGER; 
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 2);
				{
				setState(53);
				match(T__3);
				 currentType = DataType.REAL; 
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 3);
				{
				setState(55);
				match(T__4);
				 currentType = DataType.TEXT; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Lista_varContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(IsiLanguageParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(IsiLanguageParser.ID, i);
		}
		public List<TerminalNode> VIRG() { return getTokens(IsiLanguageParser.VIRG); }
		public TerminalNode VIRG(int i) {
			return getToken(IsiLanguageParser.VIRG, i);
		}
		public Lista_varContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lista_var; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).enterLista_var(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).exitLista_var(this);
		}
	}

	public final Lista_varContext lista_var() throws RecognitionException {
		Lista_varContext _localctx = new Lista_varContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_lista_var);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(59);
			match(ID);

			                Identifier idST1 = symbolTable.get(_input.LT(-1).getText());
			                if (idST1 != null) {
			                    throw new IsiAlreadyDeclaredException(idST1.getText(), idST1.getLine(), idST1.getColumn(), _input.LT(-1).getLine(), _input.LT(-1).getCharPositionInLine());
			                }
			                Identifier dcId = new Identifier(_input.LT(-1).getText(), currentType);
			                symbolTable.add(_input.LT(-1).getText(), dcId);
			                CmdDecl _decl = new CmdDecl(dcId, blockLvl, _input.LT(-1).getLine(), _input.LT(-1).getCharPositionInLine());
			                stack.peek().add(_decl);
			            
			setState(66);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VIRG) {
				{
				{
				setState(61);
				match(VIRG);
				setState(62);
				match(ID);

				                Identifier idST2 = symbolTable.get(_input.LT(-1).getText());
				                if (idST2 != null) {
				                    throw new IsiAlreadyDeclaredException(idST2.getText(), idST2.getLine(), idST2.getColumn(), _input.LT(-1).getLine(), _input.LT(-1).getCharPositionInLine());
				                }
				           	    Identifier dcId2 = new Identifier(_input.LT(-1).getText(), currentType);
				           	    symbolTable.add(_input.LT(-1).getText(), dcId2);
				           	    CmdDecl _decl2 = new CmdDecl(dcId2, blockLvl, _input.LT(-1).getLine(), _input.LT(-1).getCharPositionInLine());
				           	    stack.peek().add(_decl2);
				           	
				}
				}
				setState(68);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdContext extends ParserRuleContext {
		public CmdAttrContext cmdAttr() {
			return getRuleContext(CmdAttrContext.class,0);
		}
		public CmdReadContext cmdRead() {
			return getRuleContext(CmdReadContext.class,0);
		}
		public CmdWriteContext cmdWrite() {
			return getRuleContext(CmdWriteContext.class,0);
		}
		public CmdIfContext cmdIf() {
			return getRuleContext(CmdIfContext.class,0);
		}
		public CmdWhileContext cmdWhile() {
			return getRuleContext(CmdWhileContext.class,0);
		}
		public CmdDoWhileContext cmdDoWhile() {
			return getRuleContext(CmdDoWhileContext.class,0);
		}
		public CmdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).enterCmd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).exitCmd(this);
		}
	}

	public final CmdContext cmd() throws RecognitionException {
		CmdContext _localctx = new CmdContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_cmd);
		try {
			setState(75);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(69);
				cmdAttr();
				}
				break;
			case T__11:
				enterOuterAlt(_localctx, 2);
				{
				setState(70);
				cmdRead();
				}
				break;
			case T__12:
				enterOuterAlt(_localctx, 3);
				{
				setState(71);
				cmdWrite();
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 4);
				{
				setState(72);
				cmdIf();
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 5);
				{
				setState(73);
				cmdWhile();
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 6);
				{
				setState(74);
				cmdDoWhile();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdDoWhileContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLanguageParser.AP, 0); }
		public BRelationalExprContext bRelationalExpr() {
			return getRuleContext(BRelationalExprContext.class,0);
		}
		public TerminalNode FP() { return getToken(IsiLanguageParser.FP, 0); }
		public TerminalNode PF() { return getToken(IsiLanguageParser.PF, 0); }
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public CmdDoWhileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdDoWhile; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).enterCmdDoWhile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).exitCmdDoWhile(this);
		}
	}

	public final CmdDoWhileContext cmdDoWhile() throws RecognitionException {
		CmdDoWhileContext _localctx = new CmdDoWhileContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_cmdDoWhile);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(77);
			match(T__5);

			                  curThread = new ArrayList<AbstractCommand>();
			                  stack.push(curThread);
			                  blockLvl += 1;
			              
			setState(80); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(79);
					cmd();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(82); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(84);
			match(T__6);
			setState(85);
			match(AP);
			setState(86);
			bRelationalExpr();
			setState(87);
			match(FP);

			                  blockLvl -= 1;
			                  CmdDoWhile _cmdDoWhile = new CmdDoWhile(blockLvl, _bExpression, stack.pop());
			                  stack.peek().add(_cmdDoWhile);
			                  _bExpression = null;
			              
			setState(89);
			match(PF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdWhileContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLanguageParser.AP, 0); }
		public BRelationalExprContext bRelationalExpr() {
			return getRuleContext(BRelationalExprContext.class,0);
		}
		public TerminalNode FP() { return getToken(IsiLanguageParser.FP, 0); }
		public TerminalNode PF() { return getToken(IsiLanguageParser.PF, 0); }
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public CmdWhileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdWhile; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).enterCmdWhile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).exitCmdWhile(this);
		}
	}

	public final CmdWhileContext cmdWhile() throws RecognitionException {
		CmdWhileContext _localctx = new CmdWhileContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_cmdWhile);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(91);
			match(T__6);
			setState(92);
			match(AP);
			setState(93);
			bRelationalExpr();
			setState(94);
			match(FP);
			setState(95);
			match(T__5);

			                CmdWhile _cmdWhile = new CmdWhile(blockLvl, _bExpression);
			                _bExpression = null;
			                stackWhileCmds.push(_cmdWhile);
			                blockLvl += 1;
			                curThread = new ArrayList<AbstractCommand>();
			                stack.push(curThread);
			            
			setState(98); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(97);
				cmd();
				}
				}
				setState(100); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 33567424L) != 0) );

			                stackWhileCmds.peek().setListCommands(stack.pop());
			            
			setState(103);
			match(T__7);
			setState(104);
			match(PF);

			                stack.peek().add(stackWhileCmds.pop());
			                blockLvl -=1;
			            
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdIfContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLanguageParser.AP, 0); }
		public BRelationalExprContext bRelationalExpr() {
			return getRuleContext(BRelationalExprContext.class,0);
		}
		public TerminalNode FP() { return getToken(IsiLanguageParser.FP, 0); }
		public TerminalNode PF() { return getToken(IsiLanguageParser.PF, 0); }
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public CmdIfContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdIf; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).enterCmdIf(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).exitCmdIf(this);
		}
	}

	public final CmdIfContext cmdIf() throws RecognitionException {
		CmdIfContext _localctx = new CmdIfContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_cmdIf);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(107);
			match(T__8);
			setState(108);
			match(AP);
			setState(109);
			bRelationalExpr();
			setState(110);
			match(FP);
			setState(111);
			match(T__5);

			                CmdIf _cmdIf = new CmdIf(blockLvl, _bExpression);
			                _bExpression = null;
			                stackIfCmds.push(_cmdIf);
			                blockLvl += 1;
			                curThread = new ArrayList<AbstractCommand>();
			                stack.push(curThread);
			            
			setState(114); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(113);
				cmd();
				}
				}
				setState(116); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 33567424L) != 0) );

			               stackIfCmds.peek().setListTrue(stack.pop());
			            
			setState(128);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__9) {
				{
				setState(119);
				match(T__9);

				                curThread = new ArrayList<AbstractCommand>();
				                stack.push(curThread);
				             
				setState(122); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(121);
					cmd();
					}
					}
					setState(124); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 33567424L) != 0) );

				                 stackIfCmds.peek().setListFalse(stack.pop());
				             
				}
			}

			setState(130);
			match(T__10);
			setState(131);
			match(PF);

			                  stack.peek().add(stackIfCmds.pop());
			                  blockLvl -= 1;
			             
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdReadContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLanguageParser.AP, 0); }
		public TerminalNode ID() { return getToken(IsiLanguageParser.ID, 0); }
		public TerminalNode FP() { return getToken(IsiLanguageParser.FP, 0); }
		public TerminalNode PF() { return getToken(IsiLanguageParser.PF, 0); }
		public CmdReadContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdRead; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).enterCmdRead(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).exitCmdRead(this);
		}
	}

	public final CmdReadContext cmdRead() throws RecognitionException {
		CmdReadContext _localctx = new CmdReadContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_cmdRead);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(134);
			match(T__11);
			setState(135);
			match(AP);
			setState(136);
			match(ID);

						     Identifier id = symbolTable.get(_input.LT(-1).getText());
						     validateId(_input.LT(-1).getText(), id, true, _input.LT(-1).getLine(), _input.LT(-1).getCharPositionInLine());
						     CmdRead _read = new CmdRead(id, blockLvl);
						     stack.peek().add(_read);
						 
			setState(138);
			match(FP);
			setState(139);
			match(PF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdWriteContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLanguageParser.AP, 0); }
		public TerminalNode FP() { return getToken(IsiLanguageParser.FP, 0); }
		public TerminalNode PF() { return getToken(IsiLanguageParser.PF, 0); }
		public TerminalNode ID() { return getToken(IsiLanguageParser.ID, 0); }
		public TerminalNode TEXT() { return getToken(IsiLanguageParser.TEXT, 0); }
		public CmdWriteContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdWrite; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).enterCmdWrite(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).exitCmdWrite(this);
		}
	}

	public final CmdWriteContext cmdWrite() throws RecognitionException {
		CmdWriteContext _localctx = new CmdWriteContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_cmdWrite);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(141);
			match(T__12);
			setState(142);
			match(AP);
			setState(147);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(143);
				match(ID);

					             Identifier id = symbolTable.get(_input.LT(-1).getText());
							     validateId(_input.LT(-1).getText(), id, true, _input.LT(-1).getLine(), _input.LT(-1).getCharPositionInLine());
					             CmdWrite _write = new CmdWrite(id, blockLvl);
					             stack.peek().add(_write);
					         
				}
				break;
			case TEXT:
				{
				setState(145);
				match(TEXT);

					             String content = _input.LT(-1).getText();
					         	 CmdWrite _write = new CmdWrite(content.substring(1, content.length() -1), blockLvl);
					         	 stack.peek().add(_write);
					         
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(149);
			match(FP);
			setState(150);
			match(PF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdAttrContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(IsiLanguageParser.ID, 0); }
		public TerminalNode ATTR() { return getToken(IsiLanguageParser.ATTR, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode PF() { return getToken(IsiLanguageParser.PF, 0); }
		public CmdAttrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdAttr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).enterCmdAttr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).exitCmdAttr(this);
		}
	}

	public final CmdAttrContext cmdAttr() throws RecognitionException {
		CmdAttrContext _localctx = new CmdAttrContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_cmdAttr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(152);
			match(ID);

			                idAtribuido = _input.LT(-1).getText();
							validateId(idAtribuido, symbolTable.get(idAtribuido), false,_input.LT(-1).getLine(), _input.LT(-1).getCharPositionInLine());
							leftDT = symbolTable.get(_input.LT(-1).getText()).getType();
							rightDT = null;
						
			setState(154);
			match(ATTR);

			         	    expression = new ExpressionTree();
			         	
			setState(156);
			expr();
			setState(157);
			match(PF);

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
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprContext extends ParserRuleContext {
		public TermoContext termo() {
			return getRuleContext(TermoContext.class,0);
		}
		public List<ExprlContext> exprl() {
			return getRuleContexts(ExprlContext.class);
		}
		public ExprlContext exprl(int i) {
			return getRuleContext(ExprlContext.class,i);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_expr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(160);
			termo();
			setState(164);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 15728640L) != 0)) {
				{
				{
				setState(161);
				exprl();
				}
				}
				setState(166);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BRelationalExprContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode OPREL() { return getToken(IsiLanguageParser.OPREL, 0); }
		public BRelationalExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bRelationalExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).enterBRelationalExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).exitBRelationalExpr(this);
		}
	}

	public final BRelationalExprContext bRelationalExpr() throws RecognitionException {
		BRelationalExprContext _localctx = new BRelationalExprContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_bRelationalExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{

			                     expression = new ExpressionTree();
			                 
			setState(168);
			expr();

			                     _leftExpression = expression;
			                     expression = null;
			                 
			setState(170);
			match(OPREL);

			                     _relOp = _input.LT(-1).getText();
			                     if (leftDT == DataType.TEXT) {
			                         throw new IsiIllegalOperationException(_relOp, leftDT, _input.LT(-1).getLine(), _input.LT(-1).getCharPositionInLine());
			                     }
			                     expression = new ExpressionTree();
			                 
			setState(172);
			expr();

			                     if (leftDT == DataType.TEXT) {
			                         throw new IsiIllegalOperationException(_relOp, leftDT, _input.LT(-1).getLine(), _input.LT(-1).getCharPositionInLine());
			                     }
			                     _rightExpression = expression;
			                     expression = null;
			                     _bExpression = new BinaryRelationalExpression(_leftExpression, _rightExpression,_relOp);
			                     _leftExpression = null;
			                     _rightExpression = null;
			                     leftDT = null;
			                     rightDT = null;
			                     _relOp = null;
			                 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TermoContext extends ParserRuleContext {
		public TerminalNode NUMBER() { return getToken(IsiLanguageParser.NUMBER, 0); }
		public TerminalNode NUMBERDEC() { return getToken(IsiLanguageParser.NUMBERDEC, 0); }
		public TerminalNode TEXT() { return getToken(IsiLanguageParser.TEXT, 0); }
		public TerminalNode ID() { return getToken(IsiLanguageParser.ID, 0); }
		public TerminalNode AP() { return getToken(IsiLanguageParser.AP, 0); }
		public TerminalNode FP() { return getToken(IsiLanguageParser.FP, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode NEG_NUMBER() { return getToken(IsiLanguageParser.NEG_NUMBER, 0); }
		public TerminalNode NEG_NUMBER_DEC() { return getToken(IsiLanguageParser.NEG_NUMBER_DEC, 0); }
		public TermoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_termo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).enterTermo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).exitTermo(this);
		}
	}

	public final TermoContext termo() throws RecognitionException {
		TermoContext _localctx = new TermoContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_termo);
		int _la;
		try {
			setState(190);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NUMBER:
			case NUMBERDEC:
				enterOuterAlt(_localctx, 1);
				{
				setState(175);
				_la = _input.LA(1);
				if ( !(_la==NUMBER || _la==NUMBERDEC) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}

							    if (leftDT != null) {
								    rightDT = _input.LT(-1).getType() == 16 ? DataType.REAL : DataType.INTEGER;
								    validateBinaryOperation(_input.LT(-1).getLine(), _input.LT(-1).getCharPositionInLine());
								}
								else {
								    leftDT = _input.LT(-1).getType() == 16 ? DataType.REAL : DataType.INTEGER;
								}

				                DataType curType = rightDT != null ? rightDT : leftDT;
								expression.addOperand(new NumberExpression(Double.parseDouble(_input.LT(-1).getText()), curType));
							
				}
				break;
			case TEXT:
				enterOuterAlt(_localctx, 2);
				{
				setState(177);
				match(TEXT);

						       if (leftDT != null) {
				                   rightDT = DataType.TEXT;
				                   validateBinaryOperation(_input.LT(-1).getLine(), _input.LT(-1).getCharPositionInLine());
						       } else {
						           leftDT = DataType.TEXT;
						       }

						       textContent = _input.LT(-1).getText();
						       textContent = textContent.substring(1, textContent.length() -1);
						    
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(179);
				match(ID);

								if (!symbolTable.exists(_input.LT(-1).getText())){
									throw new IsiUndeclaredVariableException(_input.LT(-1).getText(), _input.LT(-1).getLine(), _input.LT(-1).getCharPositionInLine());
								}

								if (leftDT != null) {
				                    rightDT = symbolTable.get(_input.LT(-1).getText()).getType();
				                    validateBinaryOperation(_input.LT(-1).getLine(), _input.LT(-1).getCharPositionInLine());
								} else {
								    leftDT = symbolTable.get(_input.LT(-1).getText()).getType();
								}

								Identifier id = symbolTable.get(_input.LT(-1).getText());
								validateId(_input.LT(-1).getText(), id, true,_input.LT(-1).getLine(), _input.LT(-1).getCharPositionInLine());

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
				break;
			case AP:
				enterOuterAlt(_localctx, 4);
				{
				setState(181);
				match(AP);

				                expression.addOperator(new OperatorExpression('('));
				            
				setState(186);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case NUMBER:
				case NUMBERDEC:
				case TEXT:
				case ID:
				case AP:
					{
					setState(183);
					expr();
					}
					break;
				case NEG_NUMBER:
				case NEG_NUMBER_DEC:
					{
					setState(184);
					_la = _input.LA(1);
					if ( !(_la==NEG_NUMBER || _la==NEG_NUMBER_DEC) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}

					                        if (leftDT != null) {
					                            rightDT = _input.LT(-1).getType() == 17 ? DataType.REAL : DataType.INTEGER;
					                            validateBinaryOperation(_input.LT(-1).getLine(), _input.LT(-1).getCharPositionInLine());
					                        }
					                        else {
					                            leftDT = _input.LT(-1).getType() == 17 ? DataType.REAL : DataType.INTEGER;
					                        }

					                        expression.addOperand(new NumberExpression(Double.parseDouble(_input.LT(-1).getText()), rightDT));
					                    
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(188);
				match(FP);

				                expression.addOperator(new OperatorExpression(')'));
				            
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprlContext extends ParserRuleContext {
		public TermoContext termo() {
			return getRuleContext(TermoContext.class,0);
		}
		public TerminalNode SUM() { return getToken(IsiLanguageParser.SUM, 0); }
		public TerminalNode SUB() { return getToken(IsiLanguageParser.SUB, 0); }
		public TerminalNode MUL() { return getToken(IsiLanguageParser.MUL, 0); }
		public TerminalNode DIV() { return getToken(IsiLanguageParser.DIV, 0); }
		public ExprlContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).enterExprl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLanguageListener ) ((IsiLanguageListener)listener).exitExprl(this);
		}
	}

	public final ExprlContext exprl() throws RecognitionException {
		ExprlContext _localctx = new ExprlContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_exprl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(192);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 15728640L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}

							operator = _input.LT(-1).getText();
							if (leftDT == DataType.TEXT) {
							    throw new IsiIllegalOperationException(operator, leftDT, _input.LT(-1).getLine(), _input.LT(-1).getCharPositionInLine());
							}
							expression.addOperator(new OperatorExpression(operator.charAt(0)));
						
			setState(194);
			termo();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u001e\u00c5\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001"+
		"\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004"+
		"\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007"+
		"\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b"+
		"\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0001\u0000\u0001"+
		"\u0000\u0001\u0000\u0005\u0000\"\b\u0000\n\u0000\f\u0000%\t\u0000\u0001"+
		"\u0000\u0005\u0000(\b\u0000\n\u0000\f\u0000+\t\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003"+
		"\u0002:\b\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0005\u0003A\b\u0003\n\u0003\f\u0003D\t\u0003\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0003\u0004L\b"+
		"\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0004\u0005Q\b\u0005\u000b"+
		"\u0005\f\u0005R\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0004\u0006c\b\u0006\u000b"+
		"\u0006\f\u0006d\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0004\u0007s\b\u0007\u000b\u0007\f\u0007t\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0004\u0007{\b\u0007\u000b\u0007"+
		"\f\u0007|\u0001\u0007\u0001\u0007\u0003\u0007\u0081\b\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0003"+
		"\t\u0094\b\t\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0005\u000b\u00a3\b"+
		"\u000b\n\u000b\f\u000b\u00a6\t\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0003\r\u00bb\b\r\u0001\r\u0001"+
		"\r\u0003\r\u00bf\b\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0000\u0000\u000f\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012"+
		"\u0014\u0016\u0018\u001a\u001c\u0000\u0003\u0002\u0000\u000e\u000e\u0010"+
		"\u0010\u0002\u0000\u000f\u000f\u0011\u0011\u0001\u0000\u0014\u0017\u00ca"+
		"\u0000\u001e\u0001\u0000\u0000\u0000\u0002/\u0001\u0000\u0000\u0000\u0004"+
		"9\u0001\u0000\u0000\u0000\u0006;\u0001\u0000\u0000\u0000\bK\u0001\u0000"+
		"\u0000\u0000\nM\u0001\u0000\u0000\u0000\f[\u0001\u0000\u0000\u0000\u000e"+
		"k\u0001\u0000\u0000\u0000\u0010\u0086\u0001\u0000\u0000\u0000\u0012\u008d"+
		"\u0001\u0000\u0000\u0000\u0014\u0098\u0001\u0000\u0000\u0000\u0016\u00a0"+
		"\u0001\u0000\u0000\u0000\u0018\u00a7\u0001\u0000\u0000\u0000\u001a\u00be"+
		"\u0001\u0000\u0000\u0000\u001c\u00c0\u0001\u0000\u0000\u0000\u001e\u001f"+
		"\u0005\u0001\u0000\u0000\u001f#\u0006\u0000\uffff\uffff\u0000 \"\u0003"+
		"\u0002\u0001\u0000! \u0001\u0000\u0000\u0000\"%\u0001\u0000\u0000\u0000"+
		"#!\u0001\u0000\u0000\u0000#$\u0001\u0000\u0000\u0000$)\u0001\u0000\u0000"+
		"\u0000%#\u0001\u0000\u0000\u0000&(\u0003\b\u0004\u0000\'&\u0001\u0000"+
		"\u0000\u0000(+\u0001\u0000\u0000\u0000)\'\u0001\u0000\u0000\u0000)*\u0001"+
		"\u0000\u0000\u0000*,\u0001\u0000\u0000\u0000+)\u0001\u0000\u0000\u0000"+
		",-\u0005\u0002\u0000\u0000-.\u0006\u0000\uffff\uffff\u0000.\u0001\u0001"+
		"\u0000\u0000\u0000/0\u0003\u0004\u0002\u000001\u0003\u0006\u0003\u0000"+
		"12\u0005\u001b\u0000\u00002\u0003\u0001\u0000\u0000\u000034\u0005\u0003"+
		"\u0000\u00004:\u0006\u0002\uffff\uffff\u000056\u0005\u0004\u0000\u0000"+
		"6:\u0006\u0002\uffff\uffff\u000078\u0005\u0005\u0000\u00008:\u0006\u0002"+
		"\uffff\uffff\u000093\u0001\u0000\u0000\u000095\u0001\u0000\u0000\u0000"+
		"97\u0001\u0000\u0000\u0000:\u0005\u0001\u0000\u0000\u0000;<\u0005\u0019"+
		"\u0000\u0000<B\u0006\u0003\uffff\uffff\u0000=>\u0005\u001a\u0000\u0000"+
		">?\u0005\u0019\u0000\u0000?A\u0006\u0003\uffff\uffff\u0000@=\u0001\u0000"+
		"\u0000\u0000AD\u0001\u0000\u0000\u0000B@\u0001\u0000\u0000\u0000BC\u0001"+
		"\u0000\u0000\u0000C\u0007\u0001\u0000\u0000\u0000DB\u0001\u0000\u0000"+
		"\u0000EL\u0003\u0014\n\u0000FL\u0003\u0010\b\u0000GL\u0003\u0012\t\u0000"+
		"HL\u0003\u000e\u0007\u0000IL\u0003\f\u0006\u0000JL\u0003\n\u0005\u0000"+
		"KE\u0001\u0000\u0000\u0000KF\u0001\u0000\u0000\u0000KG\u0001\u0000\u0000"+
		"\u0000KH\u0001\u0000\u0000\u0000KI\u0001\u0000\u0000\u0000KJ\u0001\u0000"+
		"\u0000\u0000L\t\u0001\u0000\u0000\u0000MN\u0005\u0006\u0000\u0000NP\u0006"+
		"\u0005\uffff\uffff\u0000OQ\u0003\b\u0004\u0000PO\u0001\u0000\u0000\u0000"+
		"QR\u0001\u0000\u0000\u0000RP\u0001\u0000\u0000\u0000RS\u0001\u0000\u0000"+
		"\u0000ST\u0001\u0000\u0000\u0000TU\u0005\u0007\u0000\u0000UV\u0005\u001c"+
		"\u0000\u0000VW\u0003\u0018\f\u0000WX\u0005\u001d\u0000\u0000XY\u0006\u0005"+
		"\uffff\uffff\u0000YZ\u0005\u001b\u0000\u0000Z\u000b\u0001\u0000\u0000"+
		"\u0000[\\\u0005\u0007\u0000\u0000\\]\u0005\u001c\u0000\u0000]^\u0003\u0018"+
		"\f\u0000^_\u0005\u001d\u0000\u0000_`\u0005\u0006\u0000\u0000`b\u0006\u0006"+
		"\uffff\uffff\u0000ac\u0003\b\u0004\u0000ba\u0001\u0000\u0000\u0000cd\u0001"+
		"\u0000\u0000\u0000db\u0001\u0000\u0000\u0000de\u0001\u0000\u0000\u0000"+
		"ef\u0001\u0000\u0000\u0000fg\u0006\u0006\uffff\uffff\u0000gh\u0005\b\u0000"+
		"\u0000hi\u0005\u001b\u0000\u0000ij\u0006\u0006\uffff\uffff\u0000j\r\u0001"+
		"\u0000\u0000\u0000kl\u0005\t\u0000\u0000lm\u0005\u001c\u0000\u0000mn\u0003"+
		"\u0018\f\u0000no\u0005\u001d\u0000\u0000op\u0005\u0006\u0000\u0000pr\u0006"+
		"\u0007\uffff\uffff\u0000qs\u0003\b\u0004\u0000rq\u0001\u0000\u0000\u0000"+
		"st\u0001\u0000\u0000\u0000tr\u0001\u0000\u0000\u0000tu\u0001\u0000\u0000"+
		"\u0000uv\u0001\u0000\u0000\u0000v\u0080\u0006\u0007\uffff\uffff\u0000"+
		"wx\u0005\n\u0000\u0000xz\u0006\u0007\uffff\uffff\u0000y{\u0003\b\u0004"+
		"\u0000zy\u0001\u0000\u0000\u0000{|\u0001\u0000\u0000\u0000|z\u0001\u0000"+
		"\u0000\u0000|}\u0001\u0000\u0000\u0000}~\u0001\u0000\u0000\u0000~\u007f"+
		"\u0006\u0007\uffff\uffff\u0000\u007f\u0081\u0001\u0000\u0000\u0000\u0080"+
		"w\u0001\u0000\u0000\u0000\u0080\u0081\u0001\u0000\u0000\u0000\u0081\u0082"+
		"\u0001\u0000\u0000\u0000\u0082\u0083\u0005\u000b\u0000\u0000\u0083\u0084"+
		"\u0005\u001b\u0000\u0000\u0084\u0085\u0006\u0007\uffff\uffff\u0000\u0085"+
		"\u000f\u0001\u0000\u0000\u0000\u0086\u0087\u0005\f\u0000\u0000\u0087\u0088"+
		"\u0005\u001c\u0000\u0000\u0088\u0089\u0005\u0019\u0000\u0000\u0089\u008a"+
		"\u0006\b\uffff\uffff\u0000\u008a\u008b\u0005\u001d\u0000\u0000\u008b\u008c"+
		"\u0005\u001b\u0000\u0000\u008c\u0011\u0001\u0000\u0000\u0000\u008d\u008e"+
		"\u0005\r\u0000\u0000\u008e\u0093\u0005\u001c\u0000\u0000\u008f\u0090\u0005"+
		"\u0019\u0000\u0000\u0090\u0094\u0006\t\uffff\uffff\u0000\u0091\u0092\u0005"+
		"\u0012\u0000\u0000\u0092\u0094\u0006\t\uffff\uffff\u0000\u0093\u008f\u0001"+
		"\u0000\u0000\u0000\u0093\u0091\u0001\u0000\u0000\u0000\u0094\u0095\u0001"+
		"\u0000\u0000\u0000\u0095\u0096\u0005\u001d\u0000\u0000\u0096\u0097\u0005"+
		"\u001b\u0000\u0000\u0097\u0013\u0001\u0000\u0000\u0000\u0098\u0099\u0005"+
		"\u0019\u0000\u0000\u0099\u009a\u0006\n\uffff\uffff\u0000\u009a\u009b\u0005"+
		"\u0013\u0000\u0000\u009b\u009c\u0006\n\uffff\uffff\u0000\u009c\u009d\u0003"+
		"\u0016\u000b\u0000\u009d\u009e\u0005\u001b\u0000\u0000\u009e\u009f\u0006"+
		"\n\uffff\uffff\u0000\u009f\u0015\u0001\u0000\u0000\u0000\u00a0\u00a4\u0003"+
		"\u001a\r\u0000\u00a1\u00a3\u0003\u001c\u000e\u0000\u00a2\u00a1\u0001\u0000"+
		"\u0000\u0000\u00a3\u00a6\u0001\u0000\u0000\u0000\u00a4\u00a2\u0001\u0000"+
		"\u0000\u0000\u00a4\u00a5\u0001\u0000\u0000\u0000\u00a5\u0017\u0001\u0000"+
		"\u0000\u0000\u00a6\u00a4\u0001\u0000\u0000\u0000\u00a7\u00a8\u0006\f\uffff"+
		"\uffff\u0000\u00a8\u00a9\u0003\u0016\u000b\u0000\u00a9\u00aa\u0006\f\uffff"+
		"\uffff\u0000\u00aa\u00ab\u0005\u0018\u0000\u0000\u00ab\u00ac\u0006\f\uffff"+
		"\uffff\u0000\u00ac\u00ad\u0003\u0016\u000b\u0000\u00ad\u00ae\u0006\f\uffff"+
		"\uffff\u0000\u00ae\u0019\u0001\u0000\u0000\u0000\u00af\u00b0\u0007\u0000"+
		"\u0000\u0000\u00b0\u00bf\u0006\r\uffff\uffff\u0000\u00b1\u00b2\u0005\u0012"+
		"\u0000\u0000\u00b2\u00bf\u0006\r\uffff\uffff\u0000\u00b3\u00b4\u0005\u0019"+
		"\u0000\u0000\u00b4\u00bf\u0006\r\uffff\uffff\u0000\u00b5\u00b6\u0005\u001c"+
		"\u0000\u0000\u00b6\u00ba\u0006\r\uffff\uffff\u0000\u00b7\u00bb\u0003\u0016"+
		"\u000b\u0000\u00b8\u00b9\u0007\u0001\u0000\u0000\u00b9\u00bb\u0006\r\uffff"+
		"\uffff\u0000\u00ba\u00b7\u0001\u0000\u0000\u0000\u00ba\u00b8\u0001\u0000"+
		"\u0000\u0000\u00bb\u00bc\u0001\u0000\u0000\u0000\u00bc\u00bd\u0005\u001d"+
		"\u0000\u0000\u00bd\u00bf\u0006\r\uffff\uffff\u0000\u00be\u00af\u0001\u0000"+
		"\u0000\u0000\u00be\u00b1\u0001\u0000\u0000\u0000\u00be\u00b3\u0001\u0000"+
		"\u0000\u0000\u00be\u00b5\u0001\u0000\u0000\u0000\u00bf\u001b\u0001\u0000"+
		"\u0000\u0000\u00c0\u00c1\u0007\u0002\u0000\u0000\u00c1\u00c2\u0006\u000e"+
		"\uffff\uffff\u0000\u00c2\u00c3\u0003\u001a\r\u0000\u00c3\u001d\u0001\u0000"+
		"\u0000\u0000\u000e#)9BKRdt|\u0080\u0093\u00a4\u00ba\u00be";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}