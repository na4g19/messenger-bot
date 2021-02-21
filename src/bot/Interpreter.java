package bot;

import java.util.LinkedList;

import bot.Tokenizer.Token;
import bot.commands.*;
import bot.exceptions.InterpreterException;

/**
 * Creates the appropriate command class depending on the given command
 */
public class Interpreter {

    private Command outputCommand;

    public void interpret(LinkedList<Token> tokens, Message message) {

        outputCommand = null;
        String command = tokens.getFirst().getSequence();

        switch (command) {

            case "!name":
                outputCommand = new NameCommand(message.getSender());
                break;
            case "!square":
                outputCommand = new SquareCommand(tokens.get(1).getSequence());
                break;
            case "!timer":
                outputCommand = new TimerCommand(tokens);
            case "!help":
                outputCommand = new HelpCommand();
        }
    }

    public Command getOutputCommand() throws InterpreterException {

        if (outputCommand != null) {
            return outputCommand;
        } else {
            throw new InterpreterException("Could not interpret command");
        }
    }
}
