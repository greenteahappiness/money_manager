package monikamichael.money_manager.engine;

import java.util.List;

// All currency values in gr (grosze)
public class MonthData {
    int walletBegin;
    int walletEnd;
    int accountBegin;
    int accountEnd;
    int payPalBegin;
    int payPalEnd;
    int afterPreviousMonth;
    int salary;

    List<Entry> ownExpenses;
    List<Entry> periodicExpenses;
    List<Entry> otherExpenses;
    List<Entry> outOfBudgetExpenses;
    List<Entry> debts;
    List<Entry> transfersFromSavings;

    public int balance() {
        int result = 0;
        result += walletEnd - walletBegin;
        result += accountEnd - accountBegin;
        result += payPalEnd - payPalBegin;
        result -= Entry.sum(transfersFromSavings);
        result += Entry.sum(outOfBudgetExpenses);
        result += afterPreviousMonth;
        result += Entry.sum(debts);
        return result;
    }

    public int foodExpenses() {
        int result = salary;
        result -= Entry.sum(ownExpenses);
        result -= Entry.sum(periodicExpenses);
        result -= Entry.sum(otherExpenses);
        result -= balance();
        return result;
    }
}
