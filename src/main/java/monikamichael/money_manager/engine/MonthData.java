package monikamichael.money_manager.engine;

import java.util.ArrayList;
import java.util.List;

// All currency values in gr (grosze)
public class MonthData {

    public int walletBegin;
    public int walletEnd;
    public int accountBegin;
    public int accountEnd;
    public int payPalBegin;
    public int payPalEnd;
    public int afterPreviousMonth; // currently has no meaning in calculations
    public int salary;
    public int budgetIncome;
    public boolean isClosed;

    public List<Entry> ownExpenses;
    public List<Entry> periodicExpenses;
    public List<Entry> otherExpenses;
    public List<Entry> outOfBudgetExpenses;
    public List<Entry> debts;
    public List<Entry> transfersFromSavings;

    public List<Entry> getAllEntries() {
        List<Entry> result = new ArrayList<>();
        result.addAll(ownExpenses);
        result.addAll(periodicExpenses);
        result.addAll(otherExpenses);
        result.addAll(outOfBudgetExpenses);
        result.addAll(debts);
        result.addAll(transfersFromSavings);
        return result;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public int mainBegin() {
        return accountBegin + walletBegin + payPalBegin;
    }

    public int mainEnd() {
        return accountEnd + walletEnd + payPalEnd;
    }

    public int mainBalance() {
        return mainEnd() - mainBegin();
    }

    public int salaryToSavings() {
        return salary - budgetIncome;
    }

    public int savingsBalance() {
        return salaryToSavings() - Entry.sum(outOfBudgetExpenses) - Entry.sum(transfersFromSavings);
    }

    public int budgetExpenses() {
        int result = 0;
        result += Entry.sum(ownExpenses);
        result += Entry.sum(periodicExpenses);
        result += Entry.sum(otherExpenses);
        result += foodExpenses();
        return result;
    }

    public int budgetBalance() {
        return budgetIncome - budgetExpenses();
    }

    public int foodExpenses() {
        int balanceWithoutFood = 0;
        balanceWithoutFood += budgetIncome;
        balanceWithoutFood += Entry.sum(transfersFromSavings);
        balanceWithoutFood -= Entry.sum(debts);
        balanceWithoutFood -= Entry.sum(ownExpenses);
        balanceWithoutFood -= Entry.sum(periodicExpenses);
        balanceWithoutFood -= Entry.sum(otherExpenses);

        return balanceWithoutFood - mainBalance();
    }

    public static int balanceEntries(List<Entry> entries) {
        int result = 0;
        for (Entry entry : entries) {
            result += entry.value;
        }
        return result;
    }

    public static int balanceCategory(List<Entry> entries, String category) {
        int result = 0;
        for (Entry entry : entries) {
            if (entry.category.equals(category)) {
                result += entry.value;
            }
        }
        return result;
    }

    private static void printListOfEntries(StringBuilder builder, List<Entry> entries) {
        if (entries == null) {
            builder.append("(empty)\n");
            return;
        }

        for (Entry entry : entries)
            builder.append(entry.description + ": " + Currency.toString(entry.value) + "\n");
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("\nwallet at the beginning: " + Currency.toString(walletBegin));
        builder.append("\nwallet at end: " + Currency.toString(walletEnd));
        builder.append("\naccount at the beginning: " + Currency.toString(accountBegin));
        builder.append("\naccount at end: " + Currency.toString(accountEnd));
        builder.append("\nPayPal at the beginning: " + Currency.toString(payPalBegin));
        builder.append("\nPayPal at end: " + Currency.toString(payPalEnd));
        builder.append("\nafter previous month: " + Currency.toString(afterPreviousMonth));
        builder.append("\nsalary: " + Currency.toString(salary));
        builder.append("\n");

        builder.append("Own expenses:\n");
        printListOfEntries(builder, ownExpenses);

        builder.append("Periodic expenses:\n");
        printListOfEntries(builder, periodicExpenses);

        builder.append("Other expenses:\n");
        printListOfEntries(builder, otherExpenses);

        builder.append("Out of budget expenses:\n");
        printListOfEntries(builder, outOfBudgetExpenses);

        builder.append("Debts:\n");
        printListOfEntries(builder, debts);

        builder.append("Transfers from savings:\n");
        printListOfEntries(builder, transfersFromSavings);

        return builder.toString();
    }
}
