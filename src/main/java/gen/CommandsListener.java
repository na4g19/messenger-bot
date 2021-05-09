// Generated from C:/Users/Nedas/IdeaProjects/FacebookBot/src/main/java/bot/antlr\Commands.g4 by ANTLR 4.9.1
package gen;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CommandsParser}.
 */
public interface CommandsListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CommandsParser#command}.
	 * @param ctx the parse tree
	 */
	void enterCommand(CommandsParser.CommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommandsParser#command}.
	 * @param ctx the parse tree
	 */
	void exitCommand(CommandsParser.CommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommandsParser#nameCommand}.
	 * @param ctx the parse tree
	 */
	void enterNameCommand(CommandsParser.NameCommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommandsParser#nameCommand}.
	 * @param ctx the parse tree
	 */
	void exitNameCommand(CommandsParser.NameCommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommandsParser#squareCommand}.
	 * @param ctx the parse tree
	 */
	void enterSquareCommand(CommandsParser.SquareCommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommandsParser#squareCommand}.
	 * @param ctx the parse tree
	 */
	void exitSquareCommand(CommandsParser.SquareCommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommandsParser#timerCommand}.
	 * @param ctx the parse tree
	 */
	void enterTimerCommand(CommandsParser.TimerCommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommandsParser#timerCommand}.
	 * @param ctx the parse tree
	 */
	void exitTimerCommand(CommandsParser.TimerCommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommandsParser#helpCommand}.
	 * @param ctx the parse tree
	 */
	void enterHelpCommand(CommandsParser.HelpCommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommandsParser#helpCommand}.
	 * @param ctx the parse tree
	 */
	void exitHelpCommand(CommandsParser.HelpCommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommandsParser#weatherCommand}.
	 * @param ctx the parse tree
	 */
	void enterWeatherCommand(CommandsParser.WeatherCommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommandsParser#weatherCommand}.
	 * @param ctx the parse tree
	 */
	void exitWeatherCommand(CommandsParser.WeatherCommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommandsParser#moneyCommand}.
	 * @param ctx the parse tree
	 */
	void enterMoneyCommand(CommandsParser.MoneyCommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommandsParser#moneyCommand}.
	 * @param ctx the parse tree
	 */
	void exitMoneyCommand(CommandsParser.MoneyCommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommandsParser#name}.
	 * @param ctx the parse tree
	 */
	void enterName(CommandsParser.NameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommandsParser#name}.
	 * @param ctx the parse tree
	 */
	void exitName(CommandsParser.NameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommandsParser#int32}.
	 * @param ctx the parse tree
	 */
	void enterInt32(CommandsParser.Int32Context ctx);
	/**
	 * Exit a parse tree produced by {@link CommandsParser#int32}.
	 * @param ctx the parse tree
	 */
	void exitInt32(CommandsParser.Int32Context ctx);
	/**
	 * Enter a parse tree produced by {@link CommandsParser#float_value}.
	 * @param ctx the parse tree
	 */
	void enterFloat_value(CommandsParser.Float_valueContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommandsParser#float_value}.
	 * @param ctx the parse tree
	 */
	void exitFloat_value(CommandsParser.Float_valueContext ctx);
}