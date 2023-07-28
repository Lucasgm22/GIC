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

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class IsiLanguageLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, NUMBER=14, NEG_NUMBER=15, NUMBERDEC=16, 
		NEG_NUMBER_DEC=17, TEXT=18, ATTR=19, SUM=20, SUB=21, MUL=22, DIV=23, OPREL=24, 
		ID=25, VIRG=26, PF=27, AP=28, FP=29, BLANK=30;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "T__12", "NUMBER", "NEG_NUMBER", "NUMBERDEC", 
			"NEG_NUMBER_DEC", "TEXT", "ATTR", "SUM", "SUB", "MUL", "DIV", "OPREL", 
			"ID", "VIRG", "PF", "AP", "FP", "BLANK"
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


	public IsiLanguageLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "IsiLanguage.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\u001e\u00f0\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
		"\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"+
		"\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"+
		"\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002"+
		"\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e"+
		"\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011"+
		"\u0002\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014"+
		"\u0002\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017"+
		"\u0002\u0018\u0007\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a"+
		"\u0002\u001b\u0007\u001b\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\r\u0004\r\u009d\b\r\u000b\r\f\r\u009e"+
		"\u0001\u000e\u0001\u000e\u0004\u000e\u00a3\b\u000e\u000b\u000e\f\u000e"+
		"\u00a4\u0001\u000f\u0004\u000f\u00a8\b\u000f\u000b\u000f\f\u000f\u00a9"+
		"\u0001\u000f\u0001\u000f\u0004\u000f\u00ae\b\u000f\u000b\u000f\f\u000f"+
		"\u00af\u0001\u0010\u0001\u0010\u0004\u0010\u00b4\b\u0010\u000b\u0010\f"+
		"\u0010\u00b5\u0001\u0010\u0001\u0010\u0004\u0010\u00ba\b\u0010\u000b\u0010"+
		"\f\u0010\u00bb\u0001\u0011\u0001\u0011\u0005\u0011\u00c0\b\u0011\n\u0011"+
		"\f\u0011\u00c3\t\u0011\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012"+
		"\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0001\u0015"+
		"\u0001\u0015\u0001\u0016\u0001\u0016\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0003\u0017\u00dc\b\u0017\u0001\u0018\u0001\u0018\u0005\u0018"+
		"\u00e0\b\u0018\n\u0018\f\u0018\u00e3\t\u0018\u0001\u0019\u0001\u0019\u0001"+
		"\u001a\u0001\u001a\u0001\u001b\u0001\u001b\u0001\u001c\u0001\u001c\u0001"+
		"\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0000\u0000\u001e\u0001\u0001"+
		"\u0003\u0002\u0005\u0003\u0007\u0004\t\u0005\u000b\u0006\r\u0007\u000f"+
		"\b\u0011\t\u0013\n\u0015\u000b\u0017\f\u0019\r\u001b\u000e\u001d\u000f"+
		"\u001f\u0010!\u0011#\u0012%\u0013\'\u0014)\u0015+\u0016-\u0017/\u0018"+
		"1\u00193\u001a5\u001b7\u001c9\u001d;\u001e\u0001\u0000\u0005\u0001\u0000"+
		"09\u0007\u0000\t\t !-.09<>AZaz\u0001\u0000az\u0003\u000009AZaz\u0003\u0000"+
		"\t\n\r\r  \u00fc\u0000\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001"+
		"\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001"+
		"\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000"+
		"\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000\u0000"+
		"\u0000\u0000\u0011\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000\u0000"+
		"\u0000\u0000\u0015\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000\u0000"+
		"\u0000\u0000\u0019\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000\u0000"+
		"\u0000\u0000\u001d\u0001\u0000\u0000\u0000\u0000\u001f\u0001\u0000\u0000"+
		"\u0000\u0000!\u0001\u0000\u0000\u0000\u0000#\u0001\u0000\u0000\u0000\u0000"+
		"%\u0001\u0000\u0000\u0000\u0000\'\u0001\u0000\u0000\u0000\u0000)\u0001"+
		"\u0000\u0000\u0000\u0000+\u0001\u0000\u0000\u0000\u0000-\u0001\u0000\u0000"+
		"\u0000\u0000/\u0001\u0000\u0000\u0000\u00001\u0001\u0000\u0000\u0000\u0000"+
		"3\u0001\u0000\u0000\u0000\u00005\u0001\u0000\u0000\u0000\u00007\u0001"+
		"\u0000\u0000\u0000\u00009\u0001\u0000\u0000\u0000\u0000;\u0001\u0000\u0000"+
		"\u0000\u0001=\u0001\u0000\u0000\u0000\u0003F\u0001\u0000\u0000\u0000\u0005"+
		"O\u0001\u0000\u0000\u0000\u0007W\u0001\u0000\u0000\u0000\t\\\u0001\u0000"+
		"\u0000\u0000\u000bb\u0001\u0000\u0000\u0000\rj\u0001\u0000\u0000\u0000"+
		"\u000fs\u0001\u0000\u0000\u0000\u0011\u007f\u0001\u0000\u0000\u0000\u0013"+
		"\u0082\u0001\u0000\u0000\u0000\u0015\u0088\u0001\u0000\u0000\u0000\u0017"+
		"\u008e\u0001\u0000\u0000\u0000\u0019\u0093\u0001\u0000\u0000\u0000\u001b"+
		"\u009c\u0001\u0000\u0000\u0000\u001d\u00a0\u0001\u0000\u0000\u0000\u001f"+
		"\u00a7\u0001\u0000\u0000\u0000!\u00b1\u0001\u0000\u0000\u0000#\u00bd\u0001"+
		"\u0000\u0000\u0000%\u00c6\u0001\u0000\u0000\u0000\'\u00c9\u0001\u0000"+
		"\u0000\u0000)\u00cb\u0001\u0000\u0000\u0000+\u00cd\u0001\u0000\u0000\u0000"+
		"-\u00cf\u0001\u0000\u0000\u0000/\u00db\u0001\u0000\u0000\u00001\u00dd"+
		"\u0001\u0000\u0000\u00003\u00e4\u0001\u0000\u0000\u00005\u00e6\u0001\u0000"+
		"\u0000\u00007\u00e8\u0001\u0000\u0000\u00009\u00ea\u0001\u0000\u0000\u0000"+
		";\u00ec\u0001\u0000\u0000\u0000=>\u0005p\u0000\u0000>?\u0005r\u0000\u0000"+
		"?@\u0005o\u0000\u0000@A\u0005g\u0000\u0000AB\u0005r\u0000\u0000BC\u0005"+
		"a\u0000\u0000CD\u0005m\u0000\u0000DE\u0005a\u0000\u0000E\u0002\u0001\u0000"+
		"\u0000\u0000FG\u0005f\u0000\u0000GH\u0005i\u0000\u0000HI\u0005m\u0000"+
		"\u0000IJ\u0005p\u0000\u0000JK\u0005r\u0000\u0000KL\u0005o\u0000\u0000"+
		"LM\u0005g\u0000\u0000MN\u0005.\u0000\u0000N\u0004\u0001\u0000\u0000\u0000"+
		"OP\u0005I\u0000\u0000PQ\u0005N\u0000\u0000QR\u0005T\u0000\u0000RS\u0005"+
		"E\u0000\u0000ST\u0005I\u0000\u0000TU\u0005R\u0000\u0000UV\u0005O\u0000"+
		"\u0000V\u0006\u0001\u0000\u0000\u0000WX\u0005R\u0000\u0000XY\u0005E\u0000"+
		"\u0000YZ\u0005A\u0000\u0000Z[\u0005L\u0000\u0000[\b\u0001\u0000\u0000"+
		"\u0000\\]\u0005T\u0000\u0000]^\u0005E\u0000\u0000^_\u0005X\u0000\u0000"+
		"_`\u0005T\u0000\u0000`a\u0005O\u0000\u0000a\n\u0001\u0000\u0000\u0000"+
		"bc\u0005e\u0000\u0000cd\u0005x\u0000\u0000de\u0005e\u0000\u0000ef\u0005"+
		"c\u0000\u0000fg\u0005u\u0000\u0000gh\u0005t\u0000\u0000hi\u0005e\u0000"+
		"\u0000i\f\u0001\u0000\u0000\u0000jk\u0005e\u0000\u0000kl\u0005n\u0000"+
		"\u0000lm\u0005q\u0000\u0000mn\u0005u\u0000\u0000no\u0005a\u0000\u0000"+
		"op\u0005n\u0000\u0000pq\u0005t\u0000\u0000qr\u0005o\u0000\u0000r\u000e"+
		"\u0001\u0000\u0000\u0000st\u0005f\u0000\u0000tu\u0005i\u0000\u0000uv\u0005"+
		"m\u0000\u0000vw\u0005e\u0000\u0000wx\u0005n\u0000\u0000xy\u0005q\u0000"+
		"\u0000yz\u0005u\u0000\u0000z{\u0005a\u0000\u0000{|\u0005n\u0000\u0000"+
		"|}\u0005t\u0000\u0000}~\u0005o\u0000\u0000~\u0010\u0001\u0000\u0000\u0000"+
		"\u007f\u0080\u0005s\u0000\u0000\u0080\u0081\u0005e\u0000\u0000\u0081\u0012"+
		"\u0001\u0000\u0000\u0000\u0082\u0083\u0005s\u0000\u0000\u0083\u0084\u0005"+
		"e\u0000\u0000\u0084\u0085\u0005n\u0000\u0000\u0085\u0086\u0005a\u0000"+
		"\u0000\u0086\u0087\u0005o\u0000\u0000\u0087\u0014\u0001\u0000\u0000\u0000"+
		"\u0088\u0089\u0005f\u0000\u0000\u0089\u008a\u0005i\u0000\u0000\u008a\u008b"+
		"\u0005m\u0000\u0000\u008b\u008c\u0005s\u0000\u0000\u008c\u008d\u0005e"+
		"\u0000\u0000\u008d\u0016\u0001\u0000\u0000\u0000\u008e\u008f\u0005l\u0000"+
		"\u0000\u008f\u0090\u0005e\u0000\u0000\u0090\u0091\u0005i\u0000\u0000\u0091"+
		"\u0092\u0005a\u0000\u0000\u0092\u0018\u0001\u0000\u0000\u0000\u0093\u0094"+
		"\u0005e\u0000\u0000\u0094\u0095\u0005s\u0000\u0000\u0095\u0096\u0005c"+
		"\u0000\u0000\u0096\u0097\u0005r\u0000\u0000\u0097\u0098\u0005e\u0000\u0000"+
		"\u0098\u0099\u0005v\u0000\u0000\u0099\u009a\u0005a\u0000\u0000\u009a\u001a"+
		"\u0001\u0000\u0000\u0000\u009b\u009d\u0007\u0000\u0000\u0000\u009c\u009b"+
		"\u0001\u0000\u0000\u0000\u009d\u009e\u0001\u0000\u0000\u0000\u009e\u009c"+
		"\u0001\u0000\u0000\u0000\u009e\u009f\u0001\u0000\u0000\u0000\u009f\u001c"+
		"\u0001\u0000\u0000\u0000\u00a0\u00a2\u0005-\u0000\u0000\u00a1\u00a3\u0007"+
		"\u0000\u0000\u0000\u00a2\u00a1\u0001\u0000\u0000\u0000\u00a3\u00a4\u0001"+
		"\u0000\u0000\u0000\u00a4\u00a2\u0001\u0000\u0000\u0000\u00a4\u00a5\u0001"+
		"\u0000\u0000\u0000\u00a5\u001e\u0001\u0000\u0000\u0000\u00a6\u00a8\u0007"+
		"\u0000\u0000\u0000\u00a7\u00a6\u0001\u0000\u0000\u0000\u00a8\u00a9\u0001"+
		"\u0000\u0000\u0000\u00a9\u00a7\u0001\u0000\u0000\u0000\u00a9\u00aa\u0001"+
		"\u0000\u0000\u0000\u00aa\u00ab\u0001\u0000\u0000\u0000\u00ab\u00ad\u0005"+
		".\u0000\u0000\u00ac\u00ae\u0007\u0000\u0000\u0000\u00ad\u00ac\u0001\u0000"+
		"\u0000\u0000\u00ae\u00af\u0001\u0000\u0000\u0000\u00af\u00ad\u0001\u0000"+
		"\u0000\u0000\u00af\u00b0\u0001\u0000\u0000\u0000\u00b0 \u0001\u0000\u0000"+
		"\u0000\u00b1\u00b3\u0005-\u0000\u0000\u00b2\u00b4\u0007\u0000\u0000\u0000"+
		"\u00b3\u00b2\u0001\u0000\u0000\u0000\u00b4\u00b5\u0001\u0000\u0000\u0000"+
		"\u00b5\u00b3\u0001\u0000\u0000\u0000\u00b5\u00b6\u0001\u0000\u0000\u0000"+
		"\u00b6\u00b7\u0001\u0000\u0000\u0000\u00b7\u00b9\u0005.\u0000\u0000\u00b8"+
		"\u00ba\u0007\u0000\u0000\u0000\u00b9\u00b8\u0001\u0000\u0000\u0000\u00ba"+
		"\u00bb\u0001\u0000\u0000\u0000\u00bb\u00b9\u0001\u0000\u0000\u0000\u00bb"+
		"\u00bc\u0001\u0000\u0000\u0000\u00bc\"\u0001\u0000\u0000\u0000\u00bd\u00c1"+
		"\u0005\"\u0000\u0000\u00be\u00c0\u0007\u0001\u0000\u0000\u00bf\u00be\u0001"+
		"\u0000\u0000\u0000\u00c0\u00c3\u0001\u0000\u0000\u0000\u00c1\u00bf\u0001"+
		"\u0000\u0000\u0000\u00c1\u00c2\u0001\u0000\u0000\u0000\u00c2\u00c4\u0001"+
		"\u0000\u0000\u0000\u00c3\u00c1\u0001\u0000\u0000\u0000\u00c4\u00c5\u0005"+
		"\"\u0000\u0000\u00c5$\u0001\u0000\u0000\u0000\u00c6\u00c7\u0005:\u0000"+
		"\u0000\u00c7\u00c8\u0005=\u0000\u0000\u00c8&\u0001\u0000\u0000\u0000\u00c9"+
		"\u00ca\u0005+\u0000\u0000\u00ca(\u0001\u0000\u0000\u0000\u00cb\u00cc\u0005"+
		"-\u0000\u0000\u00cc*\u0001\u0000\u0000\u0000\u00cd\u00ce\u0005*\u0000"+
		"\u0000\u00ce,\u0001\u0000\u0000\u0000\u00cf\u00d0\u0005/\u0000\u0000\u00d0"+
		".\u0001\u0000\u0000\u0000\u00d1\u00dc\u0005>\u0000\u0000\u00d2\u00d3\u0005"+
		">\u0000\u0000\u00d3\u00dc\u0005=\u0000\u0000\u00d4\u00dc\u0005<\u0000"+
		"\u0000\u00d5\u00d6\u0005<\u0000\u0000\u00d6\u00dc\u0005=\u0000\u0000\u00d7"+
		"\u00d8\u0005=\u0000\u0000\u00d8\u00dc\u0005=\u0000\u0000\u00d9\u00da\u0005"+
		"!\u0000\u0000\u00da\u00dc\u0005=\u0000\u0000\u00db\u00d1\u0001\u0000\u0000"+
		"\u0000\u00db\u00d2\u0001\u0000\u0000\u0000\u00db\u00d4\u0001\u0000\u0000"+
		"\u0000\u00db\u00d5\u0001\u0000\u0000\u0000\u00db\u00d7\u0001\u0000\u0000"+
		"\u0000\u00db\u00d9\u0001\u0000\u0000\u0000\u00dc0\u0001\u0000\u0000\u0000"+
		"\u00dd\u00e1\u0007\u0002\u0000\u0000\u00de\u00e0\u0007\u0003\u0000\u0000"+
		"\u00df\u00de\u0001\u0000\u0000\u0000\u00e0\u00e3\u0001\u0000\u0000\u0000"+
		"\u00e1\u00df\u0001\u0000\u0000\u0000\u00e1\u00e2\u0001\u0000\u0000\u0000"+
		"\u00e22\u0001\u0000\u0000\u0000\u00e3\u00e1\u0001\u0000\u0000\u0000\u00e4"+
		"\u00e5\u0005,\u0000\u0000\u00e54\u0001\u0000\u0000\u0000\u00e6\u00e7\u0005"+
		".\u0000\u0000\u00e76\u0001\u0000\u0000\u0000\u00e8\u00e9\u0005(\u0000"+
		"\u0000\u00e98\u0001\u0000\u0000\u0000\u00ea\u00eb\u0005)\u0000\u0000\u00eb"+
		":\u0001\u0000\u0000\u0000\u00ec\u00ed\u0007\u0004\u0000\u0000\u00ed\u00ee"+
		"\u0001\u0000\u0000\u0000\u00ee\u00ef\u0006\u001d\u0000\u0000\u00ef<\u0001"+
		"\u0000\u0000\u0000\f\u0000\u009e\u00a4\u00a9\u00af\u00b5\u00bb\u00bf\u00c1"+
		"\u00db\u00df\u00e1\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}