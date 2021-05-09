package bot.antlr;

import gen.*;
import bot.Message;
import bot.command.commands.Command;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 * Parses message and returns the command the message represents
 */
public class CommandParser {

    private Message message;

    public CommandParser() {
    }

    public Command parse(Message message) {
        this.message = message;
        CodePointCharStream input = CharStreams.fromString(message.getCommand());
        return compile(input);
    }

    private Command compile(CharStream input) {
        CommandsParser parser = new CommandsParser(new CommonTokenStream(new CommandsLexer(input)));
        parser.addErrorListener(ThrowingErrorListener.INSTANCE);
        ParseTree tree = parser.command();

        Interpreter interpreter = new Interpreter(message);
        return interpreter.visit(tree);
    }
}
