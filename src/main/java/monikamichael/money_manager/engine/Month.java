package monikamichael.money_manager.engine;

public class Month {
    public static String fromInt(int i) {
        switch (i) {
            case 1:
                return "styczeń";
            case 2:
                return "luty";
            case 3:
                return "marzec";
            case 4:
                return "kwiecień";
            case 5:
                return "maj";
            case 6:
                return "czerwiec";
            case 7:
                return "lipiec";
            case 8:
                return "sierpień";
            case 9:
                return "wrzesień";
            case 10:
                return "październik";
            case 11:
                return "listopad";
            case 12:
                return "grudzień";
            default:
                return "(nieznany)";
        }
    }
}
