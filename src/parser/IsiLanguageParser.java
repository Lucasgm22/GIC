// Generated from IsiLanguage.g4 by ANTLR 4.13.0
package parser;

	import java.util.ArrayList;
	import java.util.Stack;
	import symbols.DataType;
	import symbols.Identifier;
	import symbols.SymbolTable;
	import expressions.*;
	import ast.*;
	

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
		T__9=10, T__10=11, NUMBER=12, NUMBERDEC=13, TEXT=14, ATTR=15, SUM=16, 
		SUB=17, MUL=18, DIV=19, OPREL=20, ID=21, VIRG=22, PF=23, AP=24, FP=25, 
		BLANK=26;
	public static final int
		RULE_programa = 0, RULE_decl = 1, RULE_tipo = 2, RULE_lista_var = 3, RULE_cmd = 4, 
		RULE_cmdIf = 5, RULE_cmdRead = 6, RULE_cmdWrite = 7, RULE_cmdAttr = 8, 
		RULE_expr = 9, RULE_termo = 10, RULE_exprl = 11;
	private static String[] makeRuleNames() {
		return new String[] {
			"programa", "decl", "tipo", "lista_var", "cmd", "cmdIf", "cmdRead", "cmdWrite", 
			"cmdAttr", "expr", "termo", "exprl"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'programa'", "'fimprog.'", "'INTEIRO'", "'REAL'", "'TEXTO'", "'se'", 
			"'entao'", "'senao'", "'fimse'", "'leia'", "'escreva'", null, null, null, 
			"':='", "'+'", "'-'", "'*'", "'/'", null, null, "','", "'.'", "'('", 
			"')'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			"NUMBER", "NUMBERDEC", "TEXT", "ATTR", "SUM", "SUB", "MUL", "DIV", "OPREL", 
			"ID", "VIRG", "PF", "AP", "FP", "BLANK"
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
		private int indentationLvl = 0;
		private Stack<ArrayList<AbstractCommand>> stack = new Stack<>();
		private Stack<CmdIf> stackIfCmds = new Stack<>();
		private ArrayList<AbstractCommand> curThread;
		
		public void init(){
			program.setSymbolTable(symbolTable);
		}
			
		public void exibirID(){
			symbolTable.getSymbols().values().stream().forEach((id)->System.out.println(id));
		}
		
		public void generateObjectCode(String filename, TargetLang target){
			program.generateTarget(filename, target);
		}
		
		public void runInterpreter(){
			program.run();
		}

		private void validateBinaryOperation() {
			if (leftDT != null && leftDT != rightDT) {
	    	    throw new RuntimeException("Semantic ERROR - Type Mismatching "+leftDT+ "-"+rightDT);
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
			setState(24);
			match(T__0);

			             curThread = new ArrayList<AbstractCommand>();
			             stack.push(curThread);
			           
			setState(27); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(26);
				decl();
				}
				}
				setState(29); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 56L) != 0) );
			setState(34);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2100288L) != 0)) {
				{
				{
				setState(31);
				cmd();
				}
				}
				setState(36);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(37);
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
			setState(40);
			tipo();
			setState(41);
			lista_var();
			setState(42);
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
			setState(50);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
				enterOuterAlt(_localctx, 1);
				{
				setState(44);
				match(T__2);
				 currentType = DataType.INTEGER; 
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 2);
				{
				setState(46);
				match(T__3);
				 currentType = DataType.REAL; 
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 3);
				{
				setState(48);
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
			setState(52);
			match(ID);

			                 Identifier dcId = new Identifier(_input.LT(-1).getText(), currentType);
			                 symbolTable.add(_input.LT(-1).getText(), dcId);
			                 CmdDecl _decl = new CmdDecl(dcId, indentationLvl);
			                 stack.peek().add(_decl);
			                 
			setState(59);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VIRG) {
				{
				{
				setState(54);
				match(VIRG);
				setState(55);
				match(ID);

				           	     Identifier dcId2 = new Identifier(_input.LT(-1).getText(), currentType);
				           	     symbolTable.add(_input.LT(-1).getText(), dcId2);
				           	     CmdDecl _decl2 = new CmdDecl(dcId2, indentationLvl);
				           	     stack.peek().add(_decl2);
				           	     
				}
				}
				setState(61);
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
			setState(66);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(62);
				cmdAttr();
				}
				break;
			case T__9:
				enterOuterAlt(_localctx, 2);
				{
				setState(63);
				cmdRead();
				}
				break;
			case T__10:
				enterOuterAlt(_localctx, 3);
				{
				setState(64);
				cmdWrite();
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 4);
				{
				setState(65);
				cmdIf();
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
	public static class CmdIfContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLanguageParser.AP, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode OPREL() { return getToken(IsiLanguageParser.OPREL, 0); }
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
		enterRule(_localctx, 10, RULE_cmdIf);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(68);
			match(T__5);
			setState(69);
			match(AP);

			                expression = new ExpressionTree();
			            
			setState(71);
			expr();

			                _leftExpression = expression;
			                expression = null;
			            
			setState(73);
			match(OPREL);

			                _relOp = _input.LT(-1).getText();
			                expression = new ExpressionTree();
			            
			setState(75);
			expr();

			                _rightExpression = expression;
			                expression = null;
			                CmdIf _cmdIf = new CmdIf(indentationLvl, _leftExpression, _rightExpression, _relOp);
			                stackIfCmds.push(_cmdIf);
			                _cmdIf = null;
			                _leftExpression = null;
			                _rightExpression = null;
			                _relOp = null;
			                indentationLvl += 1;
			            
			setState(77);
			match(FP);
			setState(78);
			match(T__6);

			                curThread = new ArrayList<AbstractCommand>();
			                stack.push(curThread);
			            
			setState(81); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(80);
				cmd();
				}
				}
				setState(83); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 2100288L) != 0) );

			               stackIfCmds.peek().setListTrue(stack.pop());
			            
			setState(95);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__7) {
				{
				setState(86);
				match(T__7);

				               curThread = new ArrayList<AbstractCommand>();
				               stack.push(curThread);
				             
				setState(89); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(88);
					cmd();
					}
					}
					setState(91); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 2100288L) != 0) );

				               stackIfCmds.peek().setListFalse(stack.pop());
				             
				}
			}

			setState(97);
			match(T__8);
			setState(98);
			match(PF);

			                stack.peek().add(stackIfCmds.pop());
			                indentationLvl -= 1;
			             
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
		enterRule(_localctx, 12, RULE_cmdRead);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(101);
			match(T__9);
			setState(102);
			match(AP);
			setState(103);
			match(ID);

							Identifier id = symbolTable.get(_input.LT(-1).getText());
							if (id == null){
								throw new RuntimeException("Undeclared Variable");
							}
							CmdRead _read = new CmdRead(id, indentationLvl);
							stack.peek().add(_read);
						 
			setState(105);
			match(FP);
			setState(106);
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
		enterRule(_localctx, 14, RULE_cmdWrite);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(108);
			match(T__10);
			setState(109);
			match(AP);
			setState(114);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(110);
				match(ID);

					         	Identifier id = symbolTable.get(_input.LT(-1).getText());
					         	if (id == null){
					         		throw new RuntimeException("Undeclared Variable");	         		
					         	}
					         	CmdWrite _write = new CmdWrite(id, indentationLvl);
					         	stack.peek().add(_write);
					         
				}
				break;
			case TEXT:
				{
				setState(112);
				match(TEXT);

					         	CmdWrite _write = new CmdWrite(_input.LT(-1).getText(), indentationLvl);
					         	stack.peek().add(_write);
					         	
					         
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(116);
			match(FP);
			setState(117);
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
		enterRule(_localctx, 16, RULE_cmdAttr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(119);
			match(ID);

							idAtribuido = _input.LT(-1).getText();
							if (!symbolTable.exists(_input.LT(-1).getText())){
								throw new RuntimeException("Semantic ERROR - Undeclared Identifier");
							}
							leftDT = symbolTable.get(_input.LT(-1).getText()).getType();
							rightDT = null;
						
			setState(121);
			match(ATTR);

			         	    expression = new ExpressionTree();
			         	
			setState(123);
			expr();
			setState(124);
			match(PF);

								// logica para atribuir o valor da expressao no identificador
								AbstractCommand _attr;
			                    Identifier id = symbolTable.get(idAtribuido);
								if (!DataType.TEXT.equals(id.getType())) {

			                        id.setValue(expression.eval());
			                        symbolTable.add(idAtribuido, id);

			                        System.out.println("EVAL ["+expression+"] = "+expression.eval());
								
								    _attr = new CmdAttrib(id, expression, indentationLvl);
								} else {
								    id.setValueText(textContent);
								    _attr = new CmdAttribString(id, textContent, indentationLvl);
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
		enterRule(_localctx, 18, RULE_expr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(127);
			termo();
			setState(131);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 983040L) != 0)) {
				{
				{
				setState(128);
				exprl();
				}
				}
				setState(133);
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
		enterRule(_localctx, 20, RULE_termo);
		int _la;
		try {
			setState(146);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NUMBER:
			case NUMBERDEC:
				enterOuterAlt(_localctx, 1);
				{
				setState(134);
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
								    rightDT = _input.LT(-1).getType() == 13 ? DataType.REAL : DataType.INTEGER;
								    validateBinaryOperation();
								}
								else {
								    leftDT = _input.LT(-1).getType() == 13 ? DataType.REAL : DataType.INTEGER;
								}

								expression.addOperand(new NumberExpression(Double.parseDouble(_input.LT(-1).getText()), rightDT));
							
				}
				break;
			case TEXT:
				enterOuterAlt(_localctx, 2);
				{
				setState(136);
				match(TEXT);

						       rightDT = DataType.TEXT;
						       validateBinaryOperation();

						       textContent = _input.LT(-1).getText();
						    
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(138);
				match(ID);

								if (!symbolTable.exists(_input.LT(-1).getText())){
									throw new RuntimeException("Semantic ERROR - Undeclared Identifier: "+_input.LT(-1).getText());
								}
								rightDT = symbolTable.get(_input.LT(-1).getText()).getType();
								validateBinaryOperation();

								Identifier id = symbolTable.get(_input.LT(-1).getText());
								if (rightDT == DataType.TEXT) {
								    textContent = id.getValueText();
								} else {

				                	if (id.getValue() != null || id.getValueText() != null){
				                		expression.addOperand(new IDExpression(id));
				                	}
				                	else{
				                		throw new RuntimeException("Semantic ERROR - Unassigned variable");
				                	}
								}
							
				}
				break;
			case AP:
				enterOuterAlt(_localctx, 4);
				{
				setState(140);
				match(AP);

				               		        expression.addOperator(new OperatorExpression('('));
				            
				setState(142);
				expr();
				setState(143);
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
		enterRule(_localctx, 22, RULE_exprl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(148);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 983040L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}

							operator = _input.LT(-1).getText().charAt(0);
							if (leftDT == DataType.TEXT) {
							    throw new RuntimeException("Semantic ERROR - Cannot apply operand " + operator + " to TEXT");
							}
							expression.addOperator(new OperatorExpression(operator));
						
			setState(150);
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
		"\u0004\u0001\u001a\u0099\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001"+
		"\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004"+
		"\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007"+
		"\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0004\u0000\u001c\b\u0000\u000b\u0000"+
		"\f\u0000\u001d\u0001\u0000\u0005\u0000!\b\u0000\n\u0000\f\u0000$\t\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0003\u00023\b\u0002\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0005\u0003:\b\u0003\n\u0003\f\u0003=\t\u0003"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0003\u0004C\b\u0004"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0004\u0005R\b\u0005\u000b\u0005\f\u0005S\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0004\u0005Z\b\u0005\u000b\u0005\f\u0005"+
		"[\u0001\u0005\u0001\u0005\u0003\u0005`\b\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007s\b\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\t\u0001\t\u0005\t\u0082\b\t\n\t\f\t\u0085\t\t"+
		"\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0003\n\u0093\b\n\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0000\u0000\f\u0000\u0002\u0004\u0006\b"+
		"\n\f\u000e\u0010\u0012\u0014\u0016\u0000\u0002\u0001\u0000\f\r\u0001\u0000"+
		"\u0010\u0013\u009c\u0000\u0018\u0001\u0000\u0000\u0000\u0002(\u0001\u0000"+
		"\u0000\u0000\u00042\u0001\u0000\u0000\u0000\u00064\u0001\u0000\u0000\u0000"+
		"\bB\u0001\u0000\u0000\u0000\nD\u0001\u0000\u0000\u0000\fe\u0001\u0000"+
		"\u0000\u0000\u000el\u0001\u0000\u0000\u0000\u0010w\u0001\u0000\u0000\u0000"+
		"\u0012\u007f\u0001\u0000\u0000\u0000\u0014\u0092\u0001\u0000\u0000\u0000"+
		"\u0016\u0094\u0001\u0000\u0000\u0000\u0018\u0019\u0005\u0001\u0000\u0000"+
		"\u0019\u001b\u0006\u0000\uffff\uffff\u0000\u001a\u001c\u0003\u0002\u0001"+
		"\u0000\u001b\u001a\u0001\u0000\u0000\u0000\u001c\u001d\u0001\u0000\u0000"+
		"\u0000\u001d\u001b\u0001\u0000\u0000\u0000\u001d\u001e\u0001\u0000\u0000"+
		"\u0000\u001e\"\u0001\u0000\u0000\u0000\u001f!\u0003\b\u0004\u0000 \u001f"+
		"\u0001\u0000\u0000\u0000!$\u0001\u0000\u0000\u0000\" \u0001\u0000\u0000"+
		"\u0000\"#\u0001\u0000\u0000\u0000#%\u0001\u0000\u0000\u0000$\"\u0001\u0000"+
		"\u0000\u0000%&\u0005\u0002\u0000\u0000&\'\u0006\u0000\uffff\uffff\u0000"+
		"\'\u0001\u0001\u0000\u0000\u0000()\u0003\u0004\u0002\u0000)*\u0003\u0006"+
		"\u0003\u0000*+\u0005\u0017\u0000\u0000+\u0003\u0001\u0000\u0000\u0000"+
		",-\u0005\u0003\u0000\u0000-3\u0006\u0002\uffff\uffff\u0000./\u0005\u0004"+
		"\u0000\u0000/3\u0006\u0002\uffff\uffff\u000001\u0005\u0005\u0000\u0000"+
		"13\u0006\u0002\uffff\uffff\u00002,\u0001\u0000\u0000\u00002.\u0001\u0000"+
		"\u0000\u000020\u0001\u0000\u0000\u00003\u0005\u0001\u0000\u0000\u0000"+
		"45\u0005\u0015\u0000\u00005;\u0006\u0003\uffff\uffff\u000067\u0005\u0016"+
		"\u0000\u000078\u0005\u0015\u0000\u00008:\u0006\u0003\uffff\uffff\u0000"+
		"96\u0001\u0000\u0000\u0000:=\u0001\u0000\u0000\u0000;9\u0001\u0000\u0000"+
		"\u0000;<\u0001\u0000\u0000\u0000<\u0007\u0001\u0000\u0000\u0000=;\u0001"+
		"\u0000\u0000\u0000>C\u0003\u0010\b\u0000?C\u0003\f\u0006\u0000@C\u0003"+
		"\u000e\u0007\u0000AC\u0003\n\u0005\u0000B>\u0001\u0000\u0000\u0000B?\u0001"+
		"\u0000\u0000\u0000B@\u0001\u0000\u0000\u0000BA\u0001\u0000\u0000\u0000"+
		"C\t\u0001\u0000\u0000\u0000DE\u0005\u0006\u0000\u0000EF\u0005\u0018\u0000"+
		"\u0000FG\u0006\u0005\uffff\uffff\u0000GH\u0003\u0012\t\u0000HI\u0006\u0005"+
		"\uffff\uffff\u0000IJ\u0005\u0014\u0000\u0000JK\u0006\u0005\uffff\uffff"+
		"\u0000KL\u0003\u0012\t\u0000LM\u0006\u0005\uffff\uffff\u0000MN\u0005\u0019"+
		"\u0000\u0000NO\u0005\u0007\u0000\u0000OQ\u0006\u0005\uffff\uffff\u0000"+
		"PR\u0003\b\u0004\u0000QP\u0001\u0000\u0000\u0000RS\u0001\u0000\u0000\u0000"+
		"SQ\u0001\u0000\u0000\u0000ST\u0001\u0000\u0000\u0000TU\u0001\u0000\u0000"+
		"\u0000U_\u0006\u0005\uffff\uffff\u0000VW\u0005\b\u0000\u0000WY\u0006\u0005"+
		"\uffff\uffff\u0000XZ\u0003\b\u0004\u0000YX\u0001\u0000\u0000\u0000Z[\u0001"+
		"\u0000\u0000\u0000[Y\u0001\u0000\u0000\u0000[\\\u0001\u0000\u0000\u0000"+
		"\\]\u0001\u0000\u0000\u0000]^\u0006\u0005\uffff\uffff\u0000^`\u0001\u0000"+
		"\u0000\u0000_V\u0001\u0000\u0000\u0000_`\u0001\u0000\u0000\u0000`a\u0001"+
		"\u0000\u0000\u0000ab\u0005\t\u0000\u0000bc\u0005\u0017\u0000\u0000cd\u0006"+
		"\u0005\uffff\uffff\u0000d\u000b\u0001\u0000\u0000\u0000ef\u0005\n\u0000"+
		"\u0000fg\u0005\u0018\u0000\u0000gh\u0005\u0015\u0000\u0000hi\u0006\u0006"+
		"\uffff\uffff\u0000ij\u0005\u0019\u0000\u0000jk\u0005\u0017\u0000\u0000"+
		"k\r\u0001\u0000\u0000\u0000lm\u0005\u000b\u0000\u0000mr\u0005\u0018\u0000"+
		"\u0000no\u0005\u0015\u0000\u0000os\u0006\u0007\uffff\uffff\u0000pq\u0005"+
		"\u000e\u0000\u0000qs\u0006\u0007\uffff\uffff\u0000rn\u0001\u0000\u0000"+
		"\u0000rp\u0001\u0000\u0000\u0000st\u0001\u0000\u0000\u0000tu\u0005\u0019"+
		"\u0000\u0000uv\u0005\u0017\u0000\u0000v\u000f\u0001\u0000\u0000\u0000"+
		"wx\u0005\u0015\u0000\u0000xy\u0006\b\uffff\uffff\u0000yz\u0005\u000f\u0000"+
		"\u0000z{\u0006\b\uffff\uffff\u0000{|\u0003\u0012\t\u0000|}\u0005\u0017"+
		"\u0000\u0000}~\u0006\b\uffff\uffff\u0000~\u0011\u0001\u0000\u0000\u0000"+
		"\u007f\u0083\u0003\u0014\n\u0000\u0080\u0082\u0003\u0016\u000b\u0000\u0081"+
		"\u0080\u0001\u0000\u0000\u0000\u0082\u0085\u0001\u0000\u0000\u0000\u0083"+
		"\u0081\u0001\u0000\u0000\u0000\u0083\u0084\u0001\u0000\u0000\u0000\u0084"+
		"\u0013\u0001\u0000\u0000\u0000\u0085\u0083\u0001\u0000\u0000\u0000\u0086"+
		"\u0087\u0007\u0000\u0000\u0000\u0087\u0093\u0006\n\uffff\uffff\u0000\u0088"+
		"\u0089\u0005\u000e\u0000\u0000\u0089\u0093\u0006\n\uffff\uffff\u0000\u008a"+
		"\u008b\u0005\u0015\u0000\u0000\u008b\u0093\u0006\n\uffff\uffff\u0000\u008c"+
		"\u008d\u0005\u0018\u0000\u0000\u008d\u008e\u0006\n\uffff\uffff\u0000\u008e"+
		"\u008f\u0003\u0012\t\u0000\u008f\u0090\u0005\u0019\u0000\u0000\u0090\u0091"+
		"\u0006\n\uffff\uffff\u0000\u0091\u0093\u0001\u0000\u0000\u0000\u0092\u0086"+
		"\u0001\u0000\u0000\u0000\u0092\u0088\u0001\u0000\u0000\u0000\u0092\u008a"+
		"\u0001\u0000\u0000\u0000\u0092\u008c\u0001\u0000\u0000\u0000\u0093\u0015"+
		"\u0001\u0000\u0000\u0000\u0094\u0095\u0007\u0001\u0000\u0000\u0095\u0096"+
		"\u0006\u000b\uffff\uffff\u0000\u0096\u0097\u0003\u0014\n\u0000\u0097\u0017"+
		"\u0001\u0000\u0000\u0000\u000b\u001d\"2;BS[_r\u0083\u0092";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}