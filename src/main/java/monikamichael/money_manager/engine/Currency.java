package monikamichael.money_manager.engine;

public class Currency {
    public static int parseString(String value) {
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
        return String.format("%d,%02d zł", zl, gr);
    }
}
