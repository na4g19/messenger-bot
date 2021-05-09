// Generated from C:/Users/Nedas/IdeaProjects/FacebookBot/src/main/java/bot/antlr\Commands.g4 by ANTLR 4.9.1
package gen;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CommandsLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		NAME_COMMAND=1, SQUARE_COMMAND=2, TIMER_COMMAND=3, TIMER_START=4, TIMER_STOP=5, 
		HELP_COMMAND=6, WEATHER_COMMAND=7, MONEY_COMMAND=8, EVOKE=9, WORD=10, 
		NUMBER=11, INTEGER=12, FLOAT=13, PT=14, WHITESPACE=15, NEWLINE=16;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"NAME_COMMAND", "SQUARE_COMMAND", "TIMER_COMMAND", "TIMER_START", "TIMER_STOP", 
			"HELP_COMMAND", "WEATHER_COMMAND", "MONEY_COMMAND", "EVOKE", "WORD", 
			"NUMBER", "INTEGER", "FLOAT", "PT", "WHITESPACE", "NEWLINE", "A", "B", 
			"C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", 
			"Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, "'!'", null, null, 
			null, null, "'.'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "NAME_COMMAND", "SQUARE_COMMAND", "TIMER_COMMAND", "TIMER_START", 
			"TIMER_STOP", "HELP_COMMAND", "WEATHER_COMMAND", "MONEY_COMMAND", "EVOKE", 
			"WORD", "NUMBER", "INTEGER", "FLOAT", "PT", "WHITESPACE", "NEWLINE"
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


	public CommandsLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Commands.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\22\u00e3\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\13\6"+
		"\13\u008b\n\13\r\13\16\13\u008c\3\f\3\f\5\f\u0091\n\f\3\r\6\r\u0094\n"+
		"\r\r\r\16\r\u0095\3\16\3\16\3\16\3\16\3\16\5\16\u009d\n\16\3\17\3\17\3"+
		"\20\3\20\3\20\3\20\3\21\5\21\u00a6\n\21\3\21\3\21\6\21\u00aa\n\21\r\21"+
		"\16\21\u00ab\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3"+
		"\26\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3"+
		"\35\3\36\3\36\3\37\3\37\3 \3 \3!\3!\3\"\3\"\3#\3#\3$\3$\3%\3%\3&\3&\3"+
		"\'\3\'\3(\3(\3)\3)\3*\3*\3+\3+\2\2,\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n"+
		"\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\2%\2\'\2)\2+\2-\2/\2\61"+
		"\2\63\2\65\2\67\29\2;\2=\2?\2A\2C\2E\2G\2I\2K\2M\2O\2Q\2S\2U\2\3\2\37"+
		"\5\2C\\c|\u0106\u017f\3\2\62;\4\2\13\13\"\"\4\2CCcc\4\2DDdd\4\2EEee\4"+
		"\2FFff\4\2GGgg\4\2HHhh\4\2IIii\4\2JJjj\4\2KKkk\4\2LLll\4\2MMmm\4\2NNn"+
		"n\4\2OOoo\4\2PPpp\4\2QQqq\4\2RRrr\4\2SSss\4\2TTtt\4\2UUuu\4\2VVvv\4\2"+
		"WWww\4\2XXxx\4\2YYyy\4\2ZZzz\4\2[[{{\4\2\\\\||\2\u00cf\2\3\3\2\2\2\2\5"+
		"\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2"+
		"\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33"+
		"\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\3W\3\2\2\2\5\\\3\2\2\2\7"+
		"c\3\2\2\2\ti\3\2\2\2\13o\3\2\2\2\rt\3\2\2\2\17y\3\2\2\2\21\u0081\3\2\2"+
		"\2\23\u0087\3\2\2\2\25\u008a\3\2\2\2\27\u0090\3\2\2\2\31\u0093\3\2\2\2"+
		"\33\u009c\3\2\2\2\35\u009e\3\2\2\2\37\u00a0\3\2\2\2!\u00a9\3\2\2\2#\u00af"+
		"\3\2\2\2%\u00b1\3\2\2\2\'\u00b3\3\2\2\2)\u00b5\3\2\2\2+\u00b7\3\2\2\2"+
		"-\u00b9\3\2\2\2/\u00bb\3\2\2\2\61\u00bd\3\2\2\2\63\u00bf\3\2\2\2\65\u00c1"+
		"\3\2\2\2\67\u00c3\3\2\2\29\u00c5\3\2\2\2;\u00c7\3\2\2\2=\u00c9\3\2\2\2"+
		"?\u00cb\3\2\2\2A\u00cd\3\2\2\2C\u00cf\3\2\2\2E\u00d1\3\2\2\2G\u00d3\3"+
		"\2\2\2I\u00d5\3\2\2\2K\u00d7\3\2\2\2M\u00d9\3\2\2\2O\u00db\3\2\2\2Q\u00dd"+
		"\3\2\2\2S\u00df\3\2\2\2U\u00e1\3\2\2\2WX\5=\37\2XY\5#\22\2YZ\5;\36\2Z"+
		"[\5+\26\2[\4\3\2\2\2\\]\5G$\2]^\5C\"\2^_\5K&\2_`\5#\22\2`a\5E#\2ab\5+"+
		"\26\2b\6\3\2\2\2cd\5I%\2de\5\63\32\2ef\5;\36\2fg\5+\26\2gh\5E#\2h\b\3"+
		"\2\2\2ij\5G$\2jk\5I%\2kl\5#\22\2lm\5E#\2mn\5I%\2n\n\3\2\2\2op\5G$\2pq"+
		"\5I%\2qr\5? \2rs\5A!\2s\f\3\2\2\2tu\5\61\31\2uv\5+\26\2vw\59\35\2wx\5"+
		"A!\2x\16\3\2\2\2yz\5O(\2z{\5+\26\2{|\5#\22\2|}\5I%\2}~\5\61\31\2~\177"+
		"\5+\26\2\177\u0080\5E#\2\u0080\20\3\2\2\2\u0081\u0082\5;\36\2\u0082\u0083"+
		"\5? \2\u0083\u0084\5=\37\2\u0084\u0085\5+\26\2\u0085\u0086\5S*\2\u0086"+
		"\22\3\2\2\2\u0087\u0088\7#\2\2\u0088\24\3\2\2\2\u0089\u008b\t\2\2\2\u008a"+
		"\u0089\3\2\2\2\u008b\u008c\3\2\2\2\u008c\u008a\3\2\2\2\u008c\u008d\3\2"+
		"\2\2\u008d\26\3\2\2\2\u008e\u0091\5\31\r\2\u008f\u0091\5\33\16\2\u0090"+
		"\u008e\3\2\2\2\u0090\u008f\3\2\2\2\u0091\30\3\2\2\2\u0092\u0094\t\3\2"+
		"\2\u0093\u0092\3\2\2\2\u0094\u0095\3\2\2\2\u0095\u0093\3\2\2\2\u0095\u0096"+
		"\3\2\2\2\u0096\32\3\2\2\2\u0097\u009d\5\31\r\2\u0098\u0099\5\31\r\2\u0099"+
		"\u009a\5\35\17\2\u009a\u009b\5\31\r\2\u009b\u009d\3\2\2\2\u009c\u0097"+
		"\3\2\2\2\u009c\u0098\3\2\2\2\u009d\34\3\2\2\2\u009e\u009f\7\60\2\2\u009f"+
		"\36\3\2\2\2\u00a0\u00a1\t\4\2\2\u00a1\u00a2\3\2\2\2\u00a2\u00a3\b\20\2"+
		"\2\u00a3 \3\2\2\2\u00a4\u00a6\7\17\2\2\u00a5\u00a4\3\2\2\2\u00a5\u00a6"+
		"\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7\u00aa\7\f\2\2\u00a8\u00aa\7\17\2\2"+
		"\u00a9\u00a5\3\2\2\2\u00a9\u00a8\3\2\2\2\u00aa\u00ab\3\2\2\2\u00ab\u00a9"+
		"\3\2\2\2\u00ab\u00ac\3\2\2\2\u00ac\u00ad\3\2\2\2\u00ad\u00ae\b\21\2\2"+
		"\u00ae\"\3\2\2\2\u00af\u00b0\t\5\2\2\u00b0$\3\2\2\2\u00b1\u00b2\t\6\2"+
		"\2\u00b2&\3\2\2\2\u00b3\u00b4\t\7\2\2\u00b4(\3\2\2\2\u00b5\u00b6\t\b\2"+
		"\2\u00b6*\3\2\2\2\u00b7\u00b8\t\t\2\2\u00b8,\3\2\2\2\u00b9\u00ba\t\n\2"+
		"\2\u00ba.\3\2\2\2\u00bb\u00bc\t\13\2\2\u00bc\60\3\2\2\2\u00bd\u00be\t"+
		"\f\2\2\u00be\62\3\2\2\2\u00bf\u00c0\t\r\2\2\u00c0\64\3\2\2\2\u00c1\u00c2"+
		"\t\16\2\2\u00c2\66\3\2\2\2\u00c3\u00c4\t\17\2\2\u00c48\3\2\2\2\u00c5\u00c6"+
		"\t\20\2\2\u00c6:\3\2\2\2\u00c7\u00c8\t\21\2\2\u00c8<\3\2\2\2\u00c9\u00ca"+
		"\t\22\2\2\u00ca>\3\2\2\2\u00cb\u00cc\t\23\2\2\u00cc@\3\2\2\2\u00cd\u00ce"+
		"\t\24\2\2\u00ceB\3\2\2\2\u00cf\u00d0\t\25\2\2\u00d0D\3\2\2\2\u00d1\u00d2"+
		"\t\26\2\2\u00d2F\3\2\2\2\u00d3\u00d4\t\27\2\2\u00d4H\3\2\2\2\u00d5\u00d6"+
		"\t\30\2\2\u00d6J\3\2\2\2\u00d7\u00d8\t\31\2\2\u00d8L\3\2\2\2\u00d9\u00da"+
		"\t\32\2\2\u00daN\3\2\2\2\u00db\u00dc\t\33\2\2\u00dcP\3\2\2\2\u00dd\u00de"+
		"\t\34\2\2\u00deR\3\2\2\2\u00df\u00e0\t\35\2\2\u00e0T\3\2\2\2\u00e1\u00e2"+
		"\t\36\2\2\u00e2V\3\2\2\2\n\2\u008c\u0090\u0095\u009c\u00a5\u00a9\u00ab"+
		"\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}