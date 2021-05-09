package bot.utility;

import java.text.DecimalFormat;

/**
 * Utility functions for pretty printing
 */
public class Print {

    /**
     * Converts double to String with precision of two decimal places
     */
    public static String moneyPrecision(double money) {
        DecimalFormat twoPlaces = new DecimalFormat("#.##");
        return twoPlaces.format(money);
    }
}
