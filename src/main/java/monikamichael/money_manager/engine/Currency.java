package monikamichael.money_manager.engine;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Currency {
    public static int parseString(String value) {
        if (!hasCurrencyZloty(value)) {
            throw new IllegalArgumentException("Currency is other than Zloty");
        }
        if (isNotNumber(value)) {
            throw new IllegalArgumentException("Value is not a number");
        }

        value = removeCurrencyIfExists(value);
        value = value.replaceAll(",", "\\.");
        return (int)(Float.parseFloat(value)*100.0);
    }

    public static String toString(int value) {

        String sign = "";

        if (value < 0) {
            value = -value;
            sign = "-";
        }

        int zl = value / 100;
        int gr = value % 100;
        return String.format("%s%d,%02d zł", sign, zl, gr);
    }

    private static boolean hasCurrencyZloty(String value) {
        if (!contains(value, "[a-zA-Z]+") || contains(value, "zł|zl")) {
            return true;
        }
        return false;
    }

    private static boolean isNotNumber(String value) {
        if (!contains(value, "\\d+")) {
            return true;
        }
        return false;
    }
    private static String removeCurrencyIfExists(String value) {
        String regex = "zł|zl";
        if (contains(value, regex)) {
            value = value.replaceFirst(regex, "");
        }
        return value;
    }

    private static boolean contains(String value, String regex) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(value);
        if (m.find()) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean matches(String value, String regex) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(value);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }
}
