package bot.exceptions;

/**
 * Any interpreter related exception
 */
public class InterpreterException extends RuntimeException {

    public InterpreterException(String errorMessage) {
        super(errorMessage);
    }
}
