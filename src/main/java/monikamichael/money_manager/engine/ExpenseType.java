package monikamichael.money_manager.engine;

public class ExpenseType {

    //TODO: make it not case-sensitive
    public static String toTableName(String expense) {
        if (expense == null || expense.isEmpty()) {
           throw new IllegalArgumentException("Empty expense name");
        }

        if (expense.contains("own")) {
            return "OWN_EXPENSES";
        } else if (expense.contains("periodic")) {
            return "PERIODIC_EXPENSES";
        } else if (expense.contains("other")) {
            return "OTHER_EXPENSES";
        } else if (expense.contains("out") || expense.contains("oob")) {
            return "OOB_EXPENSES";
        }
        return expense;
    }
}
