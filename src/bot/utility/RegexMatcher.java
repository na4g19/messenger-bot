package bot.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexMatcher {

    private static Pattern pattern;
    private static Matcher matcher;

    /**
     * Checks if text contains pattern
     * @param textPattern
     * @param text
     * @return true if contains pattern, false otherwise
     */
    public static boolean contains(String text, String textPattern) {

        pattern = Pattern.compile(textPattern);
        matcher = pattern.matcher(text);
        return matcher.find();
    }

    /**
     * Checks if text is exact match of pattern
     * @param textPattern
     * @param text
     * @return true if text is exact match, false otherwise
     */
    public static boolean matches(String text, String textPattern) {

        pattern = Pattern.compile(textPattern);
        return pattern.matcher(text).matches();
    }
}
