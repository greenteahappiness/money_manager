package monikamichael.money_manager.engine;

import java.util.List;

// All currency values in gr (grosze)
public class MonthData {
    public int walletBegin;
    public int walletEnd;
    public int accountBegin;
    public int accountEnd;
    public int payPalBegin;
    public int payPalEnd;
    public int afterPreviousMonth;
    public int salary;

    public List<Entry> ownExpenses;
    public List<Entry> periodicExpenses;
    public List<Entry> otherExpenses;
    public List<Entry> outOfBudgetExpenses;
    public List<Entry> debts;
    public List<Entry> transfersFromSavings;

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
