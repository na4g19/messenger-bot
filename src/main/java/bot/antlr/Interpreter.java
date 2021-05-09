package bot.antlr;

import gen.*;
import bot.Message;
import bot.command.commands.*;
import bot.command.commands.timer.TimerStartCommand;
import bot.command.commands.timer.TimerStopCommand;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

/**
 * Visits parse tree rules and returns appropriate command object
 *
 * child 0 = !
 * child 1 = command name
 */
public class Interpreter extends CommandsBaseVisitor<Command> {

    private Message message;

    public Interpreter(Message message) {
        this.message = message;
    }

    @Override
    public Command visitCommand(CommandsParser.CommandContext ctx) {

        try {
            return visit(ctx.getChild(1));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Command visitNameCommand(CommandsParser.NameCommandContext ctx) {
        return new NameCommand(message.getSender());
    }

    @Override
    public Command visitSquareCommand(CommandsParser.SquareCommandContext ctx) {
        return new SquareCommand(Integer.parseInt(ctx.getChild(1).getText()));
    }

    @Override
    public Command visitTimerCommand(CommandsParser.TimerCommandContext ctx) {

        ParseTree function = ctx.getChild(1);

        if (function instanceof TerminalNode) {
            Token token = (Token) function.getPayload();
            if (token.getType() == CommandsLexer.TIMER_START) {

                /*
                 * 0 - timer
                 * 1 - start
                 * 2 - [int]
                 */
                return ctx.getChildCount() == 3 ? new TimerStartCommand(Integer.parseInt(ctx.getChild(2).getText()))
                                                : new TimerStartCommand();
            } else if (token.getType() == CommandsLexer.TIMER_STOP) {
                return new TimerStopCommand();
            }
        }
        return null;
    }

    @Override
    public Command visitHelpCommand(CommandsParser.HelpCommandContext ctx) {
        return new HelpCommand();
    }

    @Override
    public Command visitWeatherCommand(CommandsParser.WeatherCommandContext ctx) {
        return new WeatherCommand(ctx.getChild(1).getText());
    }

    @Override
    public Command visitMoneyCommand(CommandsParser.MoneyCommandContext ctx) {

        int childCount = ctx.getChildCount();
        switch (childCount) {
            case 1 :
                return new DebtCommand();
            case 2 :
                return new DebtCommand(message.getSender(), Double.parseDouble(ctx.getChild(1).getText()));
            case 3 :
                return new DebtCommand(message.getSender(), ctx.getChild(1).getText(),
                        Double.parseDouble(ctx.getChild(2).getText()));
        }
        return null;
    }

    @Override
    public Command visitName(CommandsParser.NameContext ctx) {
        return super.visitName(ctx);
    }

    @Override
    public Command visitInt32(CommandsParser.Int32Context ctx) {
        return super.visitInt32(ctx);
    }
}
