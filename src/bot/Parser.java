package bot;

import java.util.LinkedList;

import bot.exceptions.ParserException;
import bot.Tokenizer.Token;

/**
 * Checks if the syntax of a command is correct
 * <p>
 * BETA VERSION 0.2131521325521313
 * HOW IT WORKS
 * 1. TOKENIZER HAS RULES ON HOW TO SPLIT COMMAND INTO TOKENS
 * 2. ONCE YOU HAVE THE TOKENS, RECURSIVELY CREATE A CALL STACK BY PARSING EACH INDIVIDUAL TOKEN
 * E.G. !TIMER START 5 = parseTimer -> parseTimerFunc -> parseInteger
 * 3. nextToken FUNCTION IS USED TO MOVE TO NEXT TOKEN
 * 4. COMMAND PARSES IF NO INPUT IS LEFT AFTER PARSING EACH TOKEN
 */
public class Parser {

    private final Tokenizer tokenizer = new Tokenizer();

    // The command split into words
    private LinkedList<Token> tokens;

    // The token to be checked
    private Token nextToken;


    /**
     * Parses the command. If parsing fails, throws an exception
     *
     * @param command the command to be parsed
     * @throws ParserException if syntax is incorrect
     */
    @SuppressWarnings("unchecked")
    public void parse(String command) throws ParserException {

        tokenizer.tokenize(command);
        tokens = (LinkedList<Token>) tokenizer.getTokens().clone();
        nextToken = tokens.getFirst();

        parseCommand();

        // All of the tokens should be used up if the command has a correct syntax
        if (!nextToken.getID().equals(Token.EMPTY_TOKEN)) {
            throw new ParserException("Incorrect syntax");
        }
    }

    /**
     * Removes the next token from the expression
     */
    private void nextToken() {

        tokens.pop();

        // If no more tokens exist, the current token is the EMPTY_TOKEN
        if (tokens.isEmpty()) {
            nextToken = new Token("", Token.EMPTY_TOKEN);
        } else {
            nextToken = tokens.getFirst();
        }
    }

    /**
     * Parses a command
     *
     * @throws ParserException if the command doesn't exist
     */
    private void parseCommand() throws ParserException {

        switch (nextToken.getSequence()) {

            case "!name":
                parseName();
                break;
            case "!square":
                parseSquare();
                break;
            case "!timer":
                parseTimer();
                break;
            case "!help":
                parseHelp();
                break;
            default:
                throw new ParserException(nextToken.getSequence() + " is not a defined command");
        }
    }

    /**
     * Parses the !name command
     */
    private void parseName() {
        nextToken();
    }

    /**
     * Parses the !square command
     */
    private void parseSquare() {
        nextToken();
        parseInteger();
    }

    /**
     * Parses the !timer command
     */
    private void parseTimer() {
        nextToken();
        parseTimerFunc();
    }

    /**
     * Parses the !help command
     */
    private void parseHelp() {
        nextToken();
    }

    /**
     * Parses the main argument of !timer
     */
    private void parseTimerFunc() {

        if (nextToken.getSequence().equals("start")) {
            nextToken();

            if (!nextToken.getID().equals(Token.EMPTY_TOKEN)) {
                parseInteger();
            }
        } else if (nextToken.getSequence().equals("stop")) {
            nextToken();
        } else {
            throw new ParserException("Undefined timer function");
        }
    }

    /**
     * Parses an integer
     */
    private void parseInteger() {

        if (nextToken.getID().equals("INTEGER")) {
            nextToken();
        } else {
            throw new ParserException("Integer Expected");
        }
    }

    /**
     * Getter for tokenizer
     *
     * @return the tokenizer
     */
    public Tokenizer getTokenizer() {
        return tokenizer;
    }
}
