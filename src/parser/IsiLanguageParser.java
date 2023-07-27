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
		T__9=10, T__10=11, T__11=12, T__12=13, NUMBER=14, NUMBERDEC=15, TEXT=16, 
		ATTR=17, SUM=18, SUB=19, MUL=20, DIV=21, OPREL=22, ID=23, VIRG=24, PF=25, 
		AP=26, FP=27, BLANK=28;
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
			"'escreva'", null, null, null, "':='", "'+'", "'-'", "'*'", "'/'", null, 
			null, "','", "'.'", "'('", "')'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, "NUMBER", "NUMBERDEC", "TEXT", "ATTR", "SUM", "SUB", "MUL", 
			"DIV", "OPREL", "ID", "VIRG", "PF", "AP", "FP", "BLANK"
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

		private void validateBinaryOperation(int line, int column) {
			if (leftDT != null && leftDT != rightDT) {
	    	    throw new IsiTypeMismatchException(leftDT, rightDT, line, column);
	        }
	    }

	    private void validateId(String idTxt, Identifier id, boolean validateValue, int line, int column) {
	    	if (id == null){
	    	    throw new IsiUndeclaredVariableException(idTxt, line, column);
	    	}
	    	if (validateValue && (id == null || !id.isAssigned())) {
	            throw new IsiUnassignedVariableException(idTxt, line, column);
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
			           
			setState(33); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(32);
				decl();
				}
				}
				setState(35); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 56L) != 0) );
			setState(40);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 8401600L) != 0)) {
				{
				{
				setState(37);
				cmd();
				}
				}
				setState(42);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(43);
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
			setState(46);
			tipo();
			setState(47);
			lista_var();
			setState(48);
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
			setState(56);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
				enterOuterAlt(_localctx, 1);
				{
				setState(50);
				match(T__2);
				 currentType = DataType.INTEGER; 
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 2);
				{
				setState(52);
				match(T__3);
				 currentType = DataType.REAL; 
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 3);
				{
				setState(54);
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
			setState(58);
			match(ID);

			                 Identifier dcId = new Identifier(_input.LT(-1).getText(), currentType);
			                 symbolTable.add(_input.LT(-1).getText(), dcId);
			                 CmdDecl _decl = new CmdDecl(dcId, blockLvl);
			                 stack.peek().add(_decl);
			                 
			setState(65);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VIRG) {
				{
				{
				setState(60);
				match(VIRG);
				setState(61);
				match(ID);

				           	     Identifier dcId2 = new Identifier(_input.LT(-1).getText(), currentType);
				           	     symbolTable.add(_input.LT(-1).getText(), dcId2);
				           	     CmdDecl _decl2 = new CmdDecl(dcId2, blockLvl);
				           	     stack.peek().add(_decl2);
				           	     
				}
				}
				setState(67);
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
			setState(74);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(68);
				cmdAttr();
				}
				break;
			case T__11:
				enterOuterAlt(_localctx, 2);
				{
				setState(69);
				cmdRead();
				}
				break;
			case T__12:
				enterOuterAlt(_localctx, 3);
				{
				setState(70);
				cmdWrite();
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 4);
				{
				setState(71);
				cmdIf();
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 5);
				{
				setState(72);
				cmdWhile();
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 6);
				{
				setState(73);
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
			setState(76);
			match(T__5);

			                curThread = new ArrayList<AbstractCommand>();
			                stack.push(curThread);
			                blockLvl += 1;
			              
			setState(79); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(78);
					cmd();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(81); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(83);
			match(T__6);
			setState(84);
			match(AP);
			setState(85);
			bRelationalExpr();
			setState(86);
			match(FP);

			                blockLvl -= 1;
			                CmdDoWhile _cmdDoWhile = new CmdDoWhile(blockLvl, _bExpression, stack.pop());
			                stack.peek().add(_cmdDoWhile);
			                _bExpression = null;
			              
			setState(88);
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
			setState(90);
			match(T__6);
			setState(91);
			match(AP);
			setState(92);
			bRelationalExpr();
			setState(93);
			match(FP);
			setState(94);
			match(T__5);

			                CmdWhile _cmdWhile = new CmdWhile(blockLvl, _bExpression);
			                _bExpression = null;
			                stackWhileCmds.push(_cmdWhile);
			                blockLvl += 1;
			                curThread = new ArrayList<AbstractCommand>();
			                stack.push(curThread);
			            
			setState(97); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(96);
				cmd();
				}
				}
				setState(99); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 8401600L) != 0) );

			                stackWhileCmds.peek().setListCommands(stack.pop());
			            
			setState(102);
			match(T__7);
			setState(103);
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
			setState(106);
			match(T__8);
			setState(107);
			match(AP);
			setState(108);
			bRelationalExpr();
			setState(109);
			match(FP);
			setState(110);
			match(T__5);

			                CmdIf _cmdIf = new CmdIf(blockLvl, _bExpression);
			                _bExpression = null;
			                stackIfCmds.push(_cmdIf);
			                blockLvl += 1;
			                curThread = new ArrayList<AbstractCommand>();
			                stack.push(curThread);
			            
			setState(113); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(112);
				cmd();
				}
				}
				setState(115); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 8401600L) != 0) );

			               stackIfCmds.peek().setListTrue(stack.pop());
			            
			setState(127);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__9) {
				{
				setState(118);
				match(T__9);

				               curThread = new ArrayList<AbstractCommand>();
				               stack.push(curThread);
				             
				setState(121); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(120);
					cmd();
					}
					}
					setState(123); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 8401600L) != 0) );

				               stackIfCmds.peek().setListFalse(stack.pop());
				             
				}
			}

			setState(129);
			match(T__10);
			setState(130);
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
			setState(133);
			match(T__11);
			setState(134);
			match(AP);
			setState(135);
			match(ID);

							Identifier id = symbolTable.get(_input.LT(-1).getText());
							validateId(id.getText(), id, true, _input.LT(-1).getLine(), _input.LT(-1).getCharPositionInLine());
							CmdRead _read = new CmdRead(id, blockLvl);
							stack.peek().add(_read);
						 
			setState(137);
			match(FP);
			setState(138);
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
			setState(140);
			match(T__12);
			setState(141);
			match(AP);
			setState(146);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(142);
				match(ID);

					         	Identifier id = symbolTable.get(_input.LT(-1).getText());
								validateId(id.getText(), id, true, _input.LT(-1).getLine(), _input.LT(-1).getCharPositionInLine());
					         	CmdWrite _write = new CmdWrite(id, blockLvl);
					         	stack.peek().add(_write);
					         
				}
				break;
			case TEXT:
				{
				setState(144);
				match(TEXT);

					         	CmdWrite _write = new CmdWrite(_input.LT(-1).getText(), blockLvl);
					         	stack.peek().add(_write);
					         	
					         
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(148);
			match(FP);
			setState(149);
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
			setState(151);
			match(ID);

			                idAtribuido = _input.LT(-1).getText();
							validateId(idAtribuido, symbolTable.get(idAtribuido), false,_input.LT(-1).getLine(), _input.LT(-1).getCharPositionInLine());
							leftDT = symbolTable.get(_input.LT(-1).getText()).getType();
							rightDT = null;
						
			setState(153);
			match(ATTR);

			         	    expression = new ExpressionTree();
			         	
			setState(155);
			expr();
			setState(156);
			match(PF);

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
			setState(159);
			termo();
			setState(163);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 3932160L) != 0)) {
				{
				{
				setState(160);
				exprl();
				}
				}
				setState(165);
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
			                             
			setState(167);
			expr();

			                                 _leftExpression = expression;
			                                 expression = null;
			                             
			setState(169);
			match(OPREL);

			                                 _relOp = _input.LT(-1).getText();
			                                 expression = new ExpressionTree();
			                             
			setState(171);
			expr();

			                                 _rightExpression = expression;
			                                 expression = null;
			                                 _bExpression = new BinaryRelationalExpression(_leftExpression, _rightExpression,_relOp);
			                                 _leftExpression = null;
			                                 _rightExpression = null;
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
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode FP() { return getToken(IsiLanguageParser.FP, 0); }
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
			setState(186);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NUMBER:
			case NUMBERDEC:
				enterOuterAlt(_localctx, 1);
				{
				setState(174);
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
								    rightDT = _input.LT(-1).getType() == 15 ? DataType.REAL : DataType.INTEGER;
								    validateBinaryOperation(_input.LT(-1).getLine(), _input.LT(-1).getCharPositionInLine());
								}
								else {
								    leftDT = _input.LT(-1).getType() == 15 ? DataType.REAL : DataType.INTEGER;
								}

								expression.addOperand(new NumberExpression(Double.parseDouble(_input.LT(-1).getText()), rightDT));
							
				}
				break;
			case TEXT:
				enterOuterAlt(_localctx, 2);
				{
				setState(176);
				match(TEXT);

						       rightDT = DataType.TEXT;
						       validateBinaryOperation(_input.LT(-1).getLine(), _input.LT(-1).getCharPositionInLine());

						       textContent = _input.LT(-1).getText();
						       textContent = textContent.substring(1, textContent.length() -1);
						    
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(178);
				match(ID);

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
				break;
			case AP:
				enterOuterAlt(_localctx, 4);
				{
				setState(180);
				match(AP);

				               		        expression.addOperator(new OperatorExpression('('));
				            
				setState(182);
				expr();
				setState(183);
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
			setState(188);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 3932160L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}

							operator = _input.LT(-1).getText().charAt(0);
							if (leftDT == DataType.TEXT) {
							    throw new IsiIllegalOperationException(operator, leftDT, _input.LT(-1).getLine(), _input.LT(-1).getCharPositionInLine());
							}
							expression.addOperator(new OperatorExpression(operator));
						
			setState(190);
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
		"\u0004\u0001\u001c\u00c1\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001"+
		"\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004"+
		"\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007"+
		"\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b"+
		"\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0001\u0000\u0001"+
		"\u0000\u0001\u0000\u0004\u0000\"\b\u0000\u000b\u0000\f\u0000#\u0001\u0000"+
		"\u0005\u0000\'\b\u0000\n\u0000\f\u0000*\t\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002"+
		"9\b\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0005\u0003@\b\u0003\n\u0003\f\u0003C\t\u0003\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0003\u0004K\b\u0004"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0004\u0005P\b\u0005\u000b\u0005"+
		"\f\u0005Q\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0004\u0006b\b\u0006\u000b\u0006"+
		"\f\u0006c\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0004\u0007r\b\u0007\u000b\u0007\f\u0007s\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0004\u0007z\b\u0007\u000b\u0007\f\u0007"+
		"{\u0001\u0007\u0001\u0007\u0003\u0007\u0080\b\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b"+
		"\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0003"+
		"\t\u0093\b\t\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0005\u000b\u00a2\b"+
		"\u000b\n\u000b\f\u000b\u00a5\t\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0003\r\u00bb\b\r\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0000\u0000\u000f"+
		"\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a"+
		"\u001c\u0000\u0002\u0001\u0000\u000e\u000f\u0001\u0000\u0012\u0015\u00c5"+
		"\u0000\u001e\u0001\u0000\u0000\u0000\u0002.\u0001\u0000\u0000\u0000\u0004"+
		"8\u0001\u0000\u0000\u0000\u0006:\u0001\u0000\u0000\u0000\bJ\u0001\u0000"+
		"\u0000\u0000\nL\u0001\u0000\u0000\u0000\fZ\u0001\u0000\u0000\u0000\u000e"+
		"j\u0001\u0000\u0000\u0000\u0010\u0085\u0001\u0000\u0000\u0000\u0012\u008c"+
		"\u0001\u0000\u0000\u0000\u0014\u0097\u0001\u0000\u0000\u0000\u0016\u009f"+
		"\u0001\u0000\u0000\u0000\u0018\u00a6\u0001\u0000\u0000\u0000\u001a\u00ba"+
		"\u0001\u0000\u0000\u0000\u001c\u00bc\u0001\u0000\u0000\u0000\u001e\u001f"+
		"\u0005\u0001\u0000\u0000\u001f!\u0006\u0000\uffff\uffff\u0000 \"\u0003"+
		"\u0002\u0001\u0000! \u0001\u0000\u0000\u0000\"#\u0001\u0000\u0000\u0000"+
		"#!\u0001\u0000\u0000\u0000#$\u0001\u0000\u0000\u0000$(\u0001\u0000\u0000"+
		"\u0000%\'\u0003\b\u0004\u0000&%\u0001\u0000\u0000\u0000\'*\u0001\u0000"+
		"\u0000\u0000(&\u0001\u0000\u0000\u0000()\u0001\u0000\u0000\u0000)+\u0001"+
		"\u0000\u0000\u0000*(\u0001\u0000\u0000\u0000+,\u0005\u0002\u0000\u0000"+
		",-\u0006\u0000\uffff\uffff\u0000-\u0001\u0001\u0000\u0000\u0000./\u0003"+
		"\u0004\u0002\u0000/0\u0003\u0006\u0003\u000001\u0005\u0019\u0000\u0000"+
		"1\u0003\u0001\u0000\u0000\u000023\u0005\u0003\u0000\u000039\u0006\u0002"+
		"\uffff\uffff\u000045\u0005\u0004\u0000\u000059\u0006\u0002\uffff\uffff"+
		"\u000067\u0005\u0005\u0000\u000079\u0006\u0002\uffff\uffff\u000082\u0001"+
		"\u0000\u0000\u000084\u0001\u0000\u0000\u000086\u0001\u0000\u0000\u0000"+
		"9\u0005\u0001\u0000\u0000\u0000:;\u0005\u0017\u0000\u0000;A\u0006\u0003"+
		"\uffff\uffff\u0000<=\u0005\u0018\u0000\u0000=>\u0005\u0017\u0000\u0000"+
		">@\u0006\u0003\uffff\uffff\u0000?<\u0001\u0000\u0000\u0000@C\u0001\u0000"+
		"\u0000\u0000A?\u0001\u0000\u0000\u0000AB\u0001\u0000\u0000\u0000B\u0007"+
		"\u0001\u0000\u0000\u0000CA\u0001\u0000\u0000\u0000DK\u0003\u0014\n\u0000"+
		"EK\u0003\u0010\b\u0000FK\u0003\u0012\t\u0000GK\u0003\u000e\u0007\u0000"+
		"HK\u0003\f\u0006\u0000IK\u0003\n\u0005\u0000JD\u0001\u0000\u0000\u0000"+
		"JE\u0001\u0000\u0000\u0000JF\u0001\u0000\u0000\u0000JG\u0001\u0000\u0000"+
		"\u0000JH\u0001\u0000\u0000\u0000JI\u0001\u0000\u0000\u0000K\t\u0001\u0000"+
		"\u0000\u0000LM\u0005\u0006\u0000\u0000MO\u0006\u0005\uffff\uffff\u0000"+
		"NP\u0003\b\u0004\u0000ON\u0001\u0000\u0000\u0000PQ\u0001\u0000\u0000\u0000"+
		"QO\u0001\u0000\u0000\u0000QR\u0001\u0000\u0000\u0000RS\u0001\u0000\u0000"+
		"\u0000ST\u0005\u0007\u0000\u0000TU\u0005\u001a\u0000\u0000UV\u0003\u0018"+
		"\f\u0000VW\u0005\u001b\u0000\u0000WX\u0006\u0005\uffff\uffff\u0000XY\u0005"+
		"\u0019\u0000\u0000Y\u000b\u0001\u0000\u0000\u0000Z[\u0005\u0007\u0000"+
		"\u0000[\\\u0005\u001a\u0000\u0000\\]\u0003\u0018\f\u0000]^\u0005\u001b"+
		"\u0000\u0000^_\u0005\u0006\u0000\u0000_a\u0006\u0006\uffff\uffff\u0000"+
		"`b\u0003\b\u0004\u0000a`\u0001\u0000\u0000\u0000bc\u0001\u0000\u0000\u0000"+
		"ca\u0001\u0000\u0000\u0000cd\u0001\u0000\u0000\u0000de\u0001\u0000\u0000"+
		"\u0000ef\u0006\u0006\uffff\uffff\u0000fg\u0005\b\u0000\u0000gh\u0005\u0019"+
		"\u0000\u0000hi\u0006\u0006\uffff\uffff\u0000i\r\u0001\u0000\u0000\u0000"+
		"jk\u0005\t\u0000\u0000kl\u0005\u001a\u0000\u0000lm\u0003\u0018\f\u0000"+
		"mn\u0005\u001b\u0000\u0000no\u0005\u0006\u0000\u0000oq\u0006\u0007\uffff"+
		"\uffff\u0000pr\u0003\b\u0004\u0000qp\u0001\u0000\u0000\u0000rs\u0001\u0000"+
		"\u0000\u0000sq\u0001\u0000\u0000\u0000st\u0001\u0000\u0000\u0000tu\u0001"+
		"\u0000\u0000\u0000u\u007f\u0006\u0007\uffff\uffff\u0000vw\u0005\n\u0000"+
		"\u0000wy\u0006\u0007\uffff\uffff\u0000xz\u0003\b\u0004\u0000yx\u0001\u0000"+
		"\u0000\u0000z{\u0001\u0000\u0000\u0000{y\u0001\u0000\u0000\u0000{|\u0001"+
		"\u0000\u0000\u0000|}\u0001\u0000\u0000\u0000}~\u0006\u0007\uffff\uffff"+
		"\u0000~\u0080\u0001\u0000\u0000\u0000\u007fv\u0001\u0000\u0000\u0000\u007f"+
		"\u0080\u0001\u0000\u0000\u0000\u0080\u0081\u0001\u0000\u0000\u0000\u0081"+
		"\u0082\u0005\u000b\u0000\u0000\u0082\u0083\u0005\u0019\u0000\u0000\u0083"+
		"\u0084\u0006\u0007\uffff\uffff\u0000\u0084\u000f\u0001\u0000\u0000\u0000"+
		"\u0085\u0086\u0005\f\u0000\u0000\u0086\u0087\u0005\u001a\u0000\u0000\u0087"+
		"\u0088\u0005\u0017\u0000\u0000\u0088\u0089\u0006\b\uffff\uffff\u0000\u0089"+
		"\u008a\u0005\u001b\u0000\u0000\u008a\u008b\u0005\u0019\u0000\u0000\u008b"+
		"\u0011\u0001\u0000\u0000\u0000\u008c\u008d\u0005\r\u0000\u0000\u008d\u0092"+
		"\u0005\u001a\u0000\u0000\u008e\u008f\u0005\u0017\u0000\u0000\u008f\u0093"+
		"\u0006\t\uffff\uffff\u0000\u0090\u0091\u0005\u0010\u0000\u0000\u0091\u0093"+
		"\u0006\t\uffff\uffff\u0000\u0092\u008e\u0001\u0000\u0000\u0000\u0092\u0090"+
		"\u0001\u0000\u0000\u0000\u0093\u0094\u0001\u0000\u0000\u0000\u0094\u0095"+
		"\u0005\u001b\u0000\u0000\u0095\u0096\u0005\u0019\u0000\u0000\u0096\u0013"+
		"\u0001\u0000\u0000\u0000\u0097\u0098\u0005\u0017\u0000\u0000\u0098\u0099"+
		"\u0006\n\uffff\uffff\u0000\u0099\u009a\u0005\u0011\u0000\u0000\u009a\u009b"+
		"\u0006\n\uffff\uffff\u0000\u009b\u009c\u0003\u0016\u000b\u0000\u009c\u009d"+
		"\u0005\u0019\u0000\u0000\u009d\u009e\u0006\n\uffff\uffff\u0000\u009e\u0015"+
		"\u0001\u0000\u0000\u0000\u009f\u00a3\u0003\u001a\r\u0000\u00a0\u00a2\u0003"+
		"\u001c\u000e\u0000\u00a1\u00a0\u0001\u0000\u0000\u0000\u00a2\u00a5\u0001"+
		"\u0000\u0000\u0000\u00a3\u00a1\u0001\u0000\u0000\u0000\u00a3\u00a4\u0001"+
		"\u0000\u0000\u0000\u00a4\u0017\u0001\u0000\u0000\u0000\u00a5\u00a3\u0001"+
		"\u0000\u0000\u0000\u00a6\u00a7\u0006\f\uffff\uffff\u0000\u00a7\u00a8\u0003"+
		"\u0016\u000b\u0000\u00a8\u00a9\u0006\f\uffff\uffff\u0000\u00a9\u00aa\u0005"+
		"\u0016\u0000\u0000\u00aa\u00ab\u0006\f\uffff\uffff\u0000\u00ab\u00ac\u0003"+
		"\u0016\u000b\u0000\u00ac\u00ad\u0006\f\uffff\uffff\u0000\u00ad\u0019\u0001"+
		"\u0000\u0000\u0000\u00ae\u00af\u0007\u0000\u0000\u0000\u00af\u00bb\u0006"+
		"\r\uffff\uffff\u0000\u00b0\u00b1\u0005\u0010\u0000\u0000\u00b1\u00bb\u0006"+
		"\r\uffff\uffff\u0000\u00b2\u00b3\u0005\u0017\u0000\u0000\u00b3\u00bb\u0006"+
		"\r\uffff\uffff\u0000\u00b4\u00b5\u0005\u001a\u0000\u0000\u00b5\u00b6\u0006"+
		"\r\uffff\uffff\u0000\u00b6\u00b7\u0003\u0016\u000b\u0000\u00b7\u00b8\u0005"+
		"\u001b\u0000\u0000\u00b8\u00b9\u0006\r\uffff\uffff\u0000\u00b9\u00bb\u0001"+
		"\u0000\u0000\u0000\u00ba\u00ae\u0001\u0000\u0000\u0000\u00ba\u00b0\u0001"+
		"\u0000\u0000\u0000\u00ba\u00b2\u0001\u0000\u0000\u0000\u00ba\u00b4\u0001"+
		"\u0000\u0000\u0000\u00bb\u001b\u0001\u0000\u0000\u0000\u00bc\u00bd\u0007"+
		"\u0001\u0000\u0000\u00bd\u00be\u0006\u000e\uffff\uffff\u0000\u00be\u00bf"+
		"\u0003\u001a\r\u0000\u00bf\u001d\u0001\u0000\u0000\u0000\r#(8AJQcs{\u007f"+
		"\u0092\u00a3\u00ba";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}