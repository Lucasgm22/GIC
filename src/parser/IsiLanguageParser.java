// Generated from IsiLanguage.g4 by ANTLR 4.13.0
package parser;

	import java.util.ArrayList;
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
			null, "'programa'", "'fimprog.'", "'INTEGER'", "'REAL'", "'TEXT'", "'se'", 
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
		
		public void generateObjectCode(TargetLang target){
			program.generateTarget(target);
		}
		
		public void runInterpreter(){
			program.run();
		}

		private void validateBinaryOperation() {
			if (leftDT != rightDT) {
	    	    throw new RuntimeException("Semantic ERROR - Type Mismatching "+leftDT+ "-"+rightDT);
	        }
	    }

	public IsiLanguageParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramaContext extends ParserRuleContext {
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
			setState(26); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(25);
				cmd();
				}
				}
				setState(28); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 2100344L) != 0) );
			setState(30);
			match(T__1);
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
			setState(32);
			tipo();
			setState(33);
			lista_var();
			setState(34);
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
			setState(42);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
				enterOuterAlt(_localctx, 1);
				{
				setState(36);
				match(T__2);
				 currentType = DataType.INTEGER; 
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 2);
				{
				setState(38);
				match(T__3);
				 currentType = DataType.REAL; 
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 3);
				{
				setState(40);
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
			setState(44);
			match(ID);

			                 Identifier dcId = new Identifier(_input.LT(-1).getText(), currentType);
			                 symbolTable.add(_input.LT(-1).getText(), dcId);
			                 CmdDecl _decl = new CmdDecl(dcId);
			                 program.getComandos().add(_decl);
			                 
			setState(51);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VIRG) {
				{
				{
				setState(46);
				match(VIRG);
				setState(47);
				match(ID);

				           	     Identifier dcId2 = new Identifier(_input.LT(-1).getText(), currentType);
				           	     symbolTable.add(_input.LT(-1).getText(), dcId2);
				           	     CmdDecl _decl2 = new CmdDecl(dcId2);
				           	     program.getComandos().add(_decl2);
				           	     
				}
				}
				setState(53);
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
		public DeclContext decl() {
			return getRuleContext(DeclContext.class,0);
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
			setState(59);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(54);
				cmdAttr();
				}
				break;
			case T__9:
				enterOuterAlt(_localctx, 2);
				{
				setState(55);
				cmdRead();
				}
				break;
			case T__10:
				enterOuterAlt(_localctx, 3);
				{
				setState(56);
				cmdWrite();
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 4);
				{
				setState(57);
				cmdIf();
				}
				break;
			case T__2:
			case T__3:
			case T__4:
				enterOuterAlt(_localctx, 5);
				{
				setState(58);
				decl();
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
			setState(61);
			match(T__5);
			setState(62);
			match(AP);
			setState(63);
			expr();
			setState(64);
			match(OPREL);
			setState(65);
			expr();
			setState(66);
			match(FP);
			setState(67);
			match(T__6);
			setState(69); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(68);
				cmd();
				}
				}
				setState(71); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 2100344L) != 0) );
			setState(79);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__7) {
				{
				setState(73);
				match(T__7);
				setState(75); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(74);
					cmd();
					}
					}
					setState(77); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 2100344L) != 0) );
				}
			}

			setState(81);
			match(T__8);
			setState(82);
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
			setState(84);
			match(T__9);
			setState(85);
			match(AP);
			setState(86);
			match(ID);

							Identifier id = symbolTable.get(_input.LT(-1).getText());
							if (id == null){
								throw new RuntimeException("Undeclared Variable");
							}
							CmdRead _read = new CmdRead(id);
							program.getComandos().add(_read);
						 
			setState(88);
			match(FP);
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
			setState(91);
			match(T__10);
			setState(92);
			match(AP);
			setState(97);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(93);
				match(ID);

					         	Identifier id = symbolTable.get(_input.LT(-1).getText());
					         	if (id == null){
					         		throw new RuntimeException("Undeclared Variable");	         		
					         	}
					         	CmdWrite _write = new CmdWrite(id);
					         	program.getComandos().add(_write);
					         
				}
				break;
			case TEXT:
				{
				setState(95);
				match(TEXT);

					         	CmdWrite _write = new CmdWrite(_input.LT(-1).getText());
					         	program.getComandos().add(_write);
					         	
					         
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(99);
			match(FP);
			setState(100);
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
			setState(102);
			match(ID);

							idAtribuido = _input.LT(-1).getText();
							if (!symbolTable.exists(_input.LT(-1).getText())){
								throw new RuntimeException("Semantic ERROR - Undeclared Identifier");
							}
							leftDT = symbolTable.get(_input.LT(-1).getText()).getType();
							rightDT = null;
						
			setState(104);
			match(ATTR);

			         	    expression = new ExpressionTree();
			         	
			setState(106);
			expr();
			setState(107);
			match(PF);

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
								textContent = null;
								expression = null;					
							
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
			setState(110);
			termo();
			setState(114);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 983040L) != 0)) {
				{
				{
				setState(111);
				exprl();
				}
				}
				setState(116);
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
			setState(129);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NUMBER:
			case NUMBERDEC:
				enterOuterAlt(_localctx, 1);
				{
				setState(117);
				_la = _input.LA(1);
				if ( !(_la==NUMBER || _la==NUMBERDEC) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}

								rightDT = _input.LT(-1).getType() == 13 ? DataType.REAL : DataType.INTEGER;
								validateBinaryOperation();
								expression.addOperand(new NumberExpression(Double.parseDouble(_input.LT(-1).getText()), rightDT));
							
				}
				break;
			case TEXT:
				enterOuterAlt(_localctx, 2);
				{
				setState(119);
				match(TEXT);

						       rightDT = DataType.TEXT;
						       validateBinaryOperation();

						       textContent = _input.LT(-1).getText();
						    
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(121);
				match(ID);

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
				break;
			case AP:
				enterOuterAlt(_localctx, 4);
				{
				setState(123);
				match(AP);

				               		        expression.addOperator(new OperatorExpression('('));
				            
				setState(125);
				expr();
				setState(126);
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
			setState(131);
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
						
			setState(133);
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
		"\u0004\u0001\u001a\u0088\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001"+
		"\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004"+
		"\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007"+
		"\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b"+
		"\u0001\u0000\u0001\u0000\u0004\u0000\u001b\b\u0000\u000b\u0000\f\u0000"+
		"\u001c\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0003\u0002+\b\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0005\u00032\b\u0003\n\u0003\f\u00035\t\u0003\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0003\u0004<\b"+
		"\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0004\u0005F\b\u0005\u000b\u0005\f\u0005"+
		"G\u0001\u0005\u0001\u0005\u0004\u0005L\b\u0005\u000b\u0005\f\u0005M\u0003"+
		"\u0005P\b\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0003"+
		"\u0007b\b\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0005\tq\b"+
		"\t\n\t\f\tt\t\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0003\n\u0082\b\n\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0000\u0000\f\u0000\u0002"+
		"\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0000\u0002\u0001\u0000"+
		"\f\r\u0001\u0000\u0010\u0013\u008b\u0000\u0018\u0001\u0000\u0000\u0000"+
		"\u0002 \u0001\u0000\u0000\u0000\u0004*\u0001\u0000\u0000\u0000\u0006,"+
		"\u0001\u0000\u0000\u0000\b;\u0001\u0000\u0000\u0000\n=\u0001\u0000\u0000"+
		"\u0000\fT\u0001\u0000\u0000\u0000\u000e[\u0001\u0000\u0000\u0000\u0010"+
		"f\u0001\u0000\u0000\u0000\u0012n\u0001\u0000\u0000\u0000\u0014\u0081\u0001"+
		"\u0000\u0000\u0000\u0016\u0083\u0001\u0000\u0000\u0000\u0018\u001a\u0005"+
		"\u0001\u0000\u0000\u0019\u001b\u0003\b\u0004\u0000\u001a\u0019\u0001\u0000"+
		"\u0000\u0000\u001b\u001c\u0001\u0000\u0000\u0000\u001c\u001a\u0001\u0000"+
		"\u0000\u0000\u001c\u001d\u0001\u0000\u0000\u0000\u001d\u001e\u0001\u0000"+
		"\u0000\u0000\u001e\u001f\u0005\u0002\u0000\u0000\u001f\u0001\u0001\u0000"+
		"\u0000\u0000 !\u0003\u0004\u0002\u0000!\"\u0003\u0006\u0003\u0000\"#\u0005"+
		"\u0017\u0000\u0000#\u0003\u0001\u0000\u0000\u0000$%\u0005\u0003\u0000"+
		"\u0000%+\u0006\u0002\uffff\uffff\u0000&\'\u0005\u0004\u0000\u0000\'+\u0006"+
		"\u0002\uffff\uffff\u0000()\u0005\u0005\u0000\u0000)+\u0006\u0002\uffff"+
		"\uffff\u0000*$\u0001\u0000\u0000\u0000*&\u0001\u0000\u0000\u0000*(\u0001"+
		"\u0000\u0000\u0000+\u0005\u0001\u0000\u0000\u0000,-\u0005\u0015\u0000"+
		"\u0000-3\u0006\u0003\uffff\uffff\u0000./\u0005\u0016\u0000\u0000/0\u0005"+
		"\u0015\u0000\u000002\u0006\u0003\uffff\uffff\u00001.\u0001\u0000\u0000"+
		"\u000025\u0001\u0000\u0000\u000031\u0001\u0000\u0000\u000034\u0001\u0000"+
		"\u0000\u00004\u0007\u0001\u0000\u0000\u000053\u0001\u0000\u0000\u0000"+
		"6<\u0003\u0010\b\u00007<\u0003\f\u0006\u00008<\u0003\u000e\u0007\u0000"+
		"9<\u0003\n\u0005\u0000:<\u0003\u0002\u0001\u0000;6\u0001\u0000\u0000\u0000"+
		";7\u0001\u0000\u0000\u0000;8\u0001\u0000\u0000\u0000;9\u0001\u0000\u0000"+
		"\u0000;:\u0001\u0000\u0000\u0000<\t\u0001\u0000\u0000\u0000=>\u0005\u0006"+
		"\u0000\u0000>?\u0005\u0018\u0000\u0000?@\u0003\u0012\t\u0000@A\u0005\u0014"+
		"\u0000\u0000AB\u0003\u0012\t\u0000BC\u0005\u0019\u0000\u0000CE\u0005\u0007"+
		"\u0000\u0000DF\u0003\b\u0004\u0000ED\u0001\u0000\u0000\u0000FG\u0001\u0000"+
		"\u0000\u0000GE\u0001\u0000\u0000\u0000GH\u0001\u0000\u0000\u0000HO\u0001"+
		"\u0000\u0000\u0000IK\u0005\b\u0000\u0000JL\u0003\b\u0004\u0000KJ\u0001"+
		"\u0000\u0000\u0000LM\u0001\u0000\u0000\u0000MK\u0001\u0000\u0000\u0000"+
		"MN\u0001\u0000\u0000\u0000NP\u0001\u0000\u0000\u0000OI\u0001\u0000\u0000"+
		"\u0000OP\u0001\u0000\u0000\u0000PQ\u0001\u0000\u0000\u0000QR\u0005\t\u0000"+
		"\u0000RS\u0005\u0017\u0000\u0000S\u000b\u0001\u0000\u0000\u0000TU\u0005"+
		"\n\u0000\u0000UV\u0005\u0018\u0000\u0000VW\u0005\u0015\u0000\u0000WX\u0006"+
		"\u0006\uffff\uffff\u0000XY\u0005\u0019\u0000\u0000YZ\u0005\u0017\u0000"+
		"\u0000Z\r\u0001\u0000\u0000\u0000[\\\u0005\u000b\u0000\u0000\\a\u0005"+
		"\u0018\u0000\u0000]^\u0005\u0015\u0000\u0000^b\u0006\u0007\uffff\uffff"+
		"\u0000_`\u0005\u000e\u0000\u0000`b\u0006\u0007\uffff\uffff\u0000a]\u0001"+
		"\u0000\u0000\u0000a_\u0001\u0000\u0000\u0000bc\u0001\u0000\u0000\u0000"+
		"cd\u0005\u0019\u0000\u0000de\u0005\u0017\u0000\u0000e\u000f\u0001\u0000"+
		"\u0000\u0000fg\u0005\u0015\u0000\u0000gh\u0006\b\uffff\uffff\u0000hi\u0005"+
		"\u000f\u0000\u0000ij\u0006\b\uffff\uffff\u0000jk\u0003\u0012\t\u0000k"+
		"l\u0005\u0017\u0000\u0000lm\u0006\b\uffff\uffff\u0000m\u0011\u0001\u0000"+
		"\u0000\u0000nr\u0003\u0014\n\u0000oq\u0003\u0016\u000b\u0000po\u0001\u0000"+
		"\u0000\u0000qt\u0001\u0000\u0000\u0000rp\u0001\u0000\u0000\u0000rs\u0001"+
		"\u0000\u0000\u0000s\u0013\u0001\u0000\u0000\u0000tr\u0001\u0000\u0000"+
		"\u0000uv\u0007\u0000\u0000\u0000v\u0082\u0006\n\uffff\uffff\u0000wx\u0005"+
		"\u000e\u0000\u0000x\u0082\u0006\n\uffff\uffff\u0000yz\u0005\u0015\u0000"+
		"\u0000z\u0082\u0006\n\uffff\uffff\u0000{|\u0005\u0018\u0000\u0000|}\u0006"+
		"\n\uffff\uffff\u0000}~\u0003\u0012\t\u0000~\u007f\u0005\u0019\u0000\u0000"+
		"\u007f\u0080\u0006\n\uffff\uffff\u0000\u0080\u0082\u0001\u0000\u0000\u0000"+
		"\u0081u\u0001\u0000\u0000\u0000\u0081w\u0001\u0000\u0000\u0000\u0081y"+
		"\u0001\u0000\u0000\u0000\u0081{\u0001\u0000\u0000\u0000\u0082\u0015\u0001"+
		"\u0000\u0000\u0000\u0083\u0084\u0007\u0001\u0000\u0000\u0084\u0085\u0006"+
		"\u000b\uffff\uffff\u0000\u0085\u0086\u0003\u0014\n\u0000\u0086\u0017\u0001"+
		"\u0000\u0000\u0000\n\u001c*3;GMOar\u0081";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}