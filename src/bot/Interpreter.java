package bot;

import java.util.LinkedList;

import bot.Tokenizer.Token;
import bot.commands.*;
import bot.commands.timer.TimerStartCommand;
import bot.commands.timer.TimerStopCommand;
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
                determineTimerFunction(tokens);
                break;
            case "!help":
                outputCommand = new HelpCommand();
                break;
            case "!weather":
                outputCommand = new WeatherCommand(tokens);
        }
    }

    private void determineTimerFunction(LinkedList<Token> tokens) {

        LinkedList<Token> tokensCopy = (LinkedList<Token>) tokens.clone();
        tokensCopy.pop();
        String function = tokensCopy.getFirst().getSequence();

        switch(function) {
            case "start" :
                outputCommand = new TimerStartCommand(tokens);
                break;
            case "stop" :
                outputCommand = new TimerStopCommand();
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
