package bot;

import bot.exceptions.ParserException;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Splints commands into words (tokens)
 */
public class Tokenizer {

    // FIXME: 20/02/2021 move to command class
    private String[] commandList = new String[]{"!name", "!square", "!timer", "!help"};

    // Rules on how to split
    private LinkedList<TokenInfo> tokenInfos;

    // Command split into words
    private LinkedList<Token> tokens;

    public Tokenizer() {

        tokenInfos = new LinkedList<>();
        tokens = new LinkedList<>();

        initTokens();
    }

    /**
     * Initialises the rules for tokenizing
     * Rule = regex + short description of regex
     */
    private void initTokens() {

        for (String command : commandList) {
            add(command + " ", "COMMAND");
        }

        add("\\s+", "EMPTY");
        add("[a-zA-Z]+", "WORD");
        add("0|[1-9][0-9]{0,8}", "INTEGER");
    }

    /**
     * Finds the tokens  by applying rules to the beginning of the expression
     *
     * @param expression the command given as input to the bot
     * @throws ParserException if expression defies the rules
     */
    public void tokenize(String expression) throws ParserException {

        // Clear tokens from previous expression
        tokens.clear();
        expression = expression.trim() + " ";

        // Until no more tokens exist
        while (!expression.equals("")) {

            boolean isMatch = false;

            // For each splitting rule
            for (TokenInfo info : tokenInfos) {

                // Check if rule matches the beginning of expression
                Matcher matcher = info.regex.matcher(expression);

                // If match is found
                if (matcher.find()) {

                    isMatch = true;

                    // Add non-empty tokens
                    if (!info.ID.equals("EMPTY")) {

                        // Add it to the list of tokens
                        String token = matcher.group().trim();
                        tokens.add(new Token(token, info.ID));
                    }

                    // Remove it from the beginning of the expression
                    expression = matcher.replaceFirst("");
                    break;
                }
            }

            if (!isMatch) {
                throw new ParserException("Incorrect syntax");
            }
        }
    }

    /**
     * Adds a tokenizing rule to the list
     *
     * @param regex the regex of the rule
     * @param ID    short descriptor of the rule
     */
    private void add(String regex, String ID) {

        // Starts with the regex
        tokenInfos.add(new TokenInfo(Pattern.compile("^(" + regex + ")"), ID));
    }

    /**
     * Getter for tokens
     *
     * @return the tokens
     */
    public LinkedList<Token> getTokens() {
        return tokens;
    }

    /**
     * Describes component of the command
     */
    public static class Token {

        public static final String EMPTY_TOKEN = "";

        // Short descriptor of the token
        private String ID;

        // The value of the token
        private String sequence;

        public Token(String sequence, String ID) {
            this.sequence = sequence;
            this.ID = ID;
        }

        public String getSequence() {
            return sequence;
        }

        public String getID() {
            return ID;
        }
    }

    /**
     * Describes a rule for splitting a command
     */
    private static class TokenInfo {

        // Regex of the rule
        public final Pattern regex;

        // Short descriptor of the rule
        private final String ID;

        public TokenInfo(Pattern regex, String ID) {
            this.regex = regex;
            this.ID = ID;
        }
    }
}
