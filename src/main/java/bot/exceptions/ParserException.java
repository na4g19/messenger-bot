package bot.exceptions;

/**
 * Any parser related exception
 */
public class ParserException extends RuntimeException {

    public ParserException(String errorMessage) {
        super(errorMessage);
    }
}
