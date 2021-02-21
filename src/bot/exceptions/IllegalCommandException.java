package bot.exceptions;

/**
 * Thrown when command is used incorrectly
 */
public class IllegalCommandException extends RuntimeException {

    public IllegalCommandException(String errorMessage) {
        super(errorMessage);
    }
}
