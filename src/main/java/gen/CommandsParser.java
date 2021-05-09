// Generated from C:/Users/Nedas/IdeaProjects/FacebookBot/src/main/java/bot/antlr\Commands.g4 by ANTLR 4.9.1
package gen;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CommandsParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		NAME_COMMAND=1, SQUARE_COMMAND=2, TIMER_COMMAND=3, TIMER_START=4, TIMER_STOP=5, 
		HELP_COMMAND=6, WEATHER_COMMAND=7, MONEY_COMMAND=8, EVOKE=9, WORD=10, 
		NUMBER=11, INTEGER=12, FLOAT=13, PT=14, WHITESPACE=15, NEWLINE=16;
	public static final int
		RULE_command = 0, RULE_nameCommand = 1, RULE_squareCommand = 2, RULE_timerCommand = 3, 
		RULE_helpCommand = 4, RULE_weatherCommand = 5, RULE_moneyCommand = 6, 
		RULE_name = 7, RULE_int32 = 8, RULE_float_value = 9;
	private static String[] makeRuleNames() {
		return new String[] {
			"command", "nameCommand", "squareCommand", "timerCommand", "helpCommand", 
			"weatherCommand", "moneyCommand", "name", "int32", "float_value"
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

	@Override
	public String getGrammarFileName() { return "Commands.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }



	      private boolean isInteger(Token t, int min, int max) {

	        int n = 0;
	        try {
	          n = Integer.parseInt(t.getText());
	        } catch (Exception e) {
	          return false;
	        }
	        return n >= min && n <= max;
	      }

	      private boolean isDouble(Token t) {

	        try {
	          double n = Double.parseDouble(t.getText());
	        } catch (Exception e) {
	          return false;
	        }
	        return true;
	      }

	      private boolean isName(Token t) {

	        // fix this lmao
	        List<String> names = new ArrayList<>();
	        names.add("nedas");
	        names.add("mykolas");
	        String name = t.getText();
	        return names.contains(name.toLowerCase());
	      }
	    
	public CommandsParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class CommandContext extends ParserRuleContext {
		public TerminalNode EVOKE() { return getToken(CommandsParser.EVOKE, 0); }
		public TerminalNode EOF() { return getToken(CommandsParser.EOF, 0); }
		public NameCommandContext nameCommand() {
			return getRuleContext(NameCommandContext.class,0);
		}
		public SquareCommandContext squareCommand() {
			return getRuleContext(SquareCommandContext.class,0);
		}
		public TimerCommandContext timerCommand() {
			return getRuleContext(TimerCommandContext.class,0);
		}
		public HelpCommandContext helpCommand() {
			return getRuleContext(HelpCommandContext.class,0);
		}
		public WeatherCommandContext weatherCommand() {
			return getRuleContext(WeatherCommandContext.class,0);
		}
		public MoneyCommandContext moneyCommand() {
			return getRuleContext(MoneyCommandContext.class,0);
		}
		public CommandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_command; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommandsListener ) ((CommandsListener)listener).enterCommand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommandsListener ) ((CommandsListener)listener).exitCommand(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CommandsVisitor ) return ((CommandsVisitor<? extends T>)visitor).visitCommand(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CommandContext command() throws RecognitionException {
		CommandContext _localctx = new CommandContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_command);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(20);
			match(EVOKE);
			setState(27);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NAME_COMMAND:
				{
				setState(21);
				nameCommand();
				}
				break;
			case SQUARE_COMMAND:
				{
				setState(22);
				squareCommand();
				}
				break;
			case TIMER_COMMAND:
				{
				setState(23);
				timerCommand();
				}
				break;
			case HELP_COMMAND:
				{
				setState(24);
				helpCommand();
				}
				break;
			case WEATHER_COMMAND:
				{
				setState(25);
				weatherCommand();
				}
				break;
			case MONEY_COMMAND:
				{
				setState(26);
				moneyCommand();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(29);
			match(EOF);
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

	public static class NameCommandContext extends ParserRuleContext {
		public TerminalNode NAME_COMMAND() { return getToken(CommandsParser.NAME_COMMAND, 0); }
		public NameCommandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nameCommand; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommandsListener ) ((CommandsListener)listener).enterNameCommand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommandsListener ) ((CommandsListener)listener).exitNameCommand(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CommandsVisitor ) return ((CommandsVisitor<? extends T>)visitor).visitNameCommand(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NameCommandContext nameCommand() throws RecognitionException {
		NameCommandContext _localctx = new NameCommandContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_nameCommand);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(31);
			match(NAME_COMMAND);
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

	public static class SquareCommandContext extends ParserRuleContext {
		public TerminalNode SQUARE_COMMAND() { return getToken(CommandsParser.SQUARE_COMMAND, 0); }
		public Int32Context int32() {
			return getRuleContext(Int32Context.class,0);
		}
		public SquareCommandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_squareCommand; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommandsListener ) ((CommandsListener)listener).enterSquareCommand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommandsListener ) ((CommandsListener)listener).exitSquareCommand(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CommandsVisitor ) return ((CommandsVisitor<? extends T>)visitor).visitSquareCommand(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SquareCommandContext squareCommand() throws RecognitionException {
		SquareCommandContext _localctx = new SquareCommandContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_squareCommand);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(33);
			match(SQUARE_COMMAND);
			setState(34);
			int32();
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

	public static class TimerCommandContext extends ParserRuleContext {
		public TerminalNode TIMER_COMMAND() { return getToken(CommandsParser.TIMER_COMMAND, 0); }
		public TerminalNode TIMER_START() { return getToken(CommandsParser.TIMER_START, 0); }
		public Int32Context int32() {
			return getRuleContext(Int32Context.class,0);
		}
		public TerminalNode TIMER_STOP() { return getToken(CommandsParser.TIMER_STOP, 0); }
		public TimerCommandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_timerCommand; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommandsListener ) ((CommandsListener)listener).enterTimerCommand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommandsListener ) ((CommandsListener)listener).exitTimerCommand(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CommandsVisitor ) return ((CommandsVisitor<? extends T>)visitor).visitTimerCommand(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TimerCommandContext timerCommand() throws RecognitionException {
		TimerCommandContext _localctx = new TimerCommandContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_timerCommand);
		try {
			setState(43);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(36);
				match(TIMER_COMMAND);
				setState(37);
				match(TIMER_START);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(38);
				match(TIMER_COMMAND);
				setState(39);
				match(TIMER_START);
				setState(40);
				int32();
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				{
				setState(41);
				match(TIMER_COMMAND);
				setState(42);
				match(TIMER_STOP);
				}
				}
				break;
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

	public static class HelpCommandContext extends ParserRuleContext {
		public TerminalNode HELP_COMMAND() { return getToken(CommandsParser.HELP_COMMAND, 0); }
		public HelpCommandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_helpCommand; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommandsListener ) ((CommandsListener)listener).enterHelpCommand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommandsListener ) ((CommandsListener)listener).exitHelpCommand(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CommandsVisitor ) return ((CommandsVisitor<? extends T>)visitor).visitHelpCommand(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HelpCommandContext helpCommand() throws RecognitionException {
		HelpCommandContext _localctx = new HelpCommandContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_helpCommand);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(45);
			match(HELP_COMMAND);
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

	public static class WeatherCommandContext extends ParserRuleContext {
		public TerminalNode WEATHER_COMMAND() { return getToken(CommandsParser.WEATHER_COMMAND, 0); }
		public TerminalNode WORD() { return getToken(CommandsParser.WORD, 0); }
		public WeatherCommandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_weatherCommand; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommandsListener ) ((CommandsListener)listener).enterWeatherCommand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommandsListener ) ((CommandsListener)listener).exitWeatherCommand(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CommandsVisitor ) return ((CommandsVisitor<? extends T>)visitor).visitWeatherCommand(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WeatherCommandContext weatherCommand() throws RecognitionException {
		WeatherCommandContext _localctx = new WeatherCommandContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_weatherCommand);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(47);
			match(WEATHER_COMMAND);
			setState(48);
			match(WORD);
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

	public static class MoneyCommandContext extends ParserRuleContext {
		public TerminalNode MONEY_COMMAND() { return getToken(CommandsParser.MONEY_COMMAND, 0); }
		public Float_valueContext float_value() {
			return getRuleContext(Float_valueContext.class,0);
		}
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public MoneyCommandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_moneyCommand; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommandsListener ) ((CommandsListener)listener).enterMoneyCommand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommandsListener ) ((CommandsListener)listener).exitMoneyCommand(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CommandsVisitor ) return ((CommandsVisitor<? extends T>)visitor).visitMoneyCommand(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MoneyCommandContext moneyCommand() throws RecognitionException {
		MoneyCommandContext _localctx = new MoneyCommandContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_moneyCommand);
		try {
			setState(57);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(50);
				match(MONEY_COMMAND);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(51);
				match(MONEY_COMMAND);
				setState(52);
				float_value();
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				{
				setState(53);
				match(MONEY_COMMAND);
				setState(54);
				name();
				setState(55);
				float_value();
				}
				}
				break;
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

	public static class NameContext extends ParserRuleContext {
		public Token WORD;
		public TerminalNode WORD() { return getToken(CommandsParser.WORD, 0); }
		public NameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommandsListener ) ((CommandsListener)listener).enterName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommandsListener ) ((CommandsListener)listener).exitName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CommandsVisitor ) return ((CommandsVisitor<? extends T>)visitor).visitName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NameContext name() throws RecognitionException {
		NameContext _localctx = new NameContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(59);
			((NameContext)_localctx).WORD = match(WORD);
			setState(60);
			if (!(isName(((NameContext)_localctx).WORD))) throw new FailedPredicateException(this, "isName($WORD)");
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

	public static class Int32Context extends ParserRuleContext {
		public Token NUMBER;
		public TerminalNode NUMBER() { return getToken(CommandsParser.NUMBER, 0); }
		public Int32Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_int32; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommandsListener ) ((CommandsListener)listener).enterInt32(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommandsListener ) ((CommandsListener)listener).exitInt32(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CommandsVisitor ) return ((CommandsVisitor<? extends T>)visitor).visitInt32(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Int32Context int32() throws RecognitionException {
		Int32Context _localctx = new Int32Context(_ctx, getState());
		enterRule(_localctx, 16, RULE_int32);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(62);
			((Int32Context)_localctx).NUMBER = match(NUMBER);
			setState(63);
			if (!(isInteger(((Int32Context)_localctx).NUMBER, 0, 2147483647))) throw new FailedPredicateException(this, "isInteger($NUMBER, 0, 2147483647)");
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

	public static class Float_valueContext extends ParserRuleContext {
		public Token NUMBER;
		public TerminalNode NUMBER() { return getToken(CommandsParser.NUMBER, 0); }
		public Float_valueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_float_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CommandsListener ) ((CommandsListener)listener).enterFloat_value(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CommandsListener ) ((CommandsListener)listener).exitFloat_value(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CommandsVisitor ) return ((CommandsVisitor<? extends T>)visitor).visitFloat_value(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Float_valueContext float_value() throws RecognitionException {
		Float_valueContext _localctx = new Float_valueContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_float_value);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(65);
			((Float_valueContext)_localctx).NUMBER = match(NUMBER);
			setState(66);
			if (!(isDouble(((Float_valueContext)_localctx).NUMBER))) throw new FailedPredicateException(this, "isDouble($NUMBER)");
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 7:
			return name_sempred((NameContext)_localctx, predIndex);
		case 8:
			return int32_sempred((Int32Context)_localctx, predIndex);
		case 9:
			return float_value_sempred((Float_valueContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean name_sempred(NameContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return isName(((NameContext)_localctx).WORD);
		}
		return true;
	}
	private boolean int32_sempred(Int32Context _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return isInteger(((Int32Context)_localctx).NUMBER, 0, 2147483647);
		}
		return true;
	}
	private boolean float_value_sempred(Float_valueContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return isDouble(((Float_valueContext)_localctx).NUMBER);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\22G\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2\36\n\2\3\2\3\2\3\3\3\3\3\4\3\4\3\4\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\5\5.\n\5\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\5\b<\n\b\3\t\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3\13\3\13\2\2"+
		"\f\2\4\6\b\n\f\16\20\22\24\2\2\2E\2\26\3\2\2\2\4!\3\2\2\2\6#\3\2\2\2\b"+
		"-\3\2\2\2\n/\3\2\2\2\f\61\3\2\2\2\16;\3\2\2\2\20=\3\2\2\2\22@\3\2\2\2"+
		"\24C\3\2\2\2\26\35\7\13\2\2\27\36\5\4\3\2\30\36\5\6\4\2\31\36\5\b\5\2"+
		"\32\36\5\n\6\2\33\36\5\f\7\2\34\36\5\16\b\2\35\27\3\2\2\2\35\30\3\2\2"+
		"\2\35\31\3\2\2\2\35\32\3\2\2\2\35\33\3\2\2\2\35\34\3\2\2\2\36\37\3\2\2"+
		"\2\37 \7\2\2\3 \3\3\2\2\2!\"\7\3\2\2\"\5\3\2\2\2#$\7\4\2\2$%\5\22\n\2"+
		"%\7\3\2\2\2&\'\7\5\2\2\'.\7\6\2\2()\7\5\2\2)*\7\6\2\2*.\5\22\n\2+,\7\5"+
		"\2\2,.\7\7\2\2-&\3\2\2\2-(\3\2\2\2-+\3\2\2\2.\t\3\2\2\2/\60\7\b\2\2\60"+
		"\13\3\2\2\2\61\62\7\t\2\2\62\63\7\f\2\2\63\r\3\2\2\2\64<\7\n\2\2\65\66"+
		"\7\n\2\2\66<\5\24\13\2\678\7\n\2\289\5\20\t\29:\5\24\13\2:<\3\2\2\2;\64"+
		"\3\2\2\2;\65\3\2\2\2;\67\3\2\2\2<\17\3\2\2\2=>\7\f\2\2>?\6\t\2\3?\21\3"+
		"\2\2\2@A\7\r\2\2AB\6\n\3\3B\23\3\2\2\2CD\7\r\2\2DE\6\13\4\3E\25\3\2\2"+
		"\2\5\35-;";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}