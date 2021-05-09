// Generated from C:/Users/Nedas/IdeaProjects/FacebookBot/src/main/java/bot/antlr\Commands.g4 by ANTLR 4.9.1
package gen;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link CommandsParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface CommandsVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link CommandsParser#command}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCommand(CommandsParser.CommandContext ctx);
	/**
	 * Visit a parse tree produced by {@link CommandsParser#nameCommand}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNameCommand(CommandsParser.NameCommandContext ctx);
	/**
	 * Visit a parse tree produced by {@link CommandsParser#squareCommand}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSquareCommand(CommandsParser.SquareCommandContext ctx);
	/**
	 * Visit a parse tree produced by {@link CommandsParser#timerCommand}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTimerCommand(CommandsParser.TimerCommandContext ctx);
	/**
	 * Visit a parse tree produced by {@link CommandsParser#helpCommand}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHelpCommand(CommandsParser.HelpCommandContext ctx);
	/**
	 * Visit a parse tree produced by {@link CommandsParser#weatherCommand}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWeatherCommand(CommandsParser.WeatherCommandContext ctx);
	/**
	 * Visit a parse tree produced by {@link CommandsParser#moneyCommand}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMoneyCommand(CommandsParser.MoneyCommandContext ctx);
	/**
	 * Visit a parse tree produced by {@link CommandsParser#name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitName(CommandsParser.NameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CommandsParser#int32}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInt32(CommandsParser.Int32Context ctx);
	/**
	 * Visit a parse tree produced by {@link CommandsParser#float_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFloat_value(CommandsParser.Float_valueContext ctx);
}