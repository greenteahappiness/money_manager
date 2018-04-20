package monikamichael.money_manager.engine;

public class TableName {
    public static String toTableName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Wrong argument provided");
        }
        if (name.toLowerCase().equals("own expenses")) {
            return "OWN_EXPENSES";
        } else if (name.toLowerCase().equals("periodic expenses")) {
            return "PERIODIC_EXPENSES";
        } else if (name.toLowerCase().equals("other expenses")) {
            return "OTHER_EXPENSES";
        } else if (name.toLowerCase().equals("out-of-budget events")) {
            return "OOB_EVENTS";
        } else if (name.toLowerCase().equals("debts")) {
            return "DEBTS";
        } else if (name.toLowerCase().equals("transfers")) {
            return "TRANSFERS";
        } else {
            throw new IllegalArgumentException("No such column in database");
        }
    }
}
