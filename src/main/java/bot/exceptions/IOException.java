package bot.exceptions;

/**
 * Any parser related exception
 */
public class IOException extends RuntimeException
{

    public IOException(String errorMessage) {
        super(errorMessage);
    }
}
