package monikamichael.money_manager.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

// All currency values in gr (grosze)
public class MonthData {
    protected static Logger logger = LoggerFactory.getLogger(MonthData.class);

    public int walletBegin;
    public int walletEnd;
    public int accountBegin;
    public int accountEnd;
    public int payPalBegin;
    public int payPalEnd;
    public int afterPreviousMonth;
    public int salary;
    public boolean isClosed;

    public List<Entry> ownExpenses;
    public List<Entry> periodicExpenses;
    public List<Entry> otherExpenses;
    public List<Entry> outOfBudgetExpenses;
    public List<Entry> debts;
    public List<Entry> transfersFromSavings;

    public static void closeMonth(Database db, final int year, final int month) {
        db.executeSqlInsert(
                "UPDATE MONTHS SET CLOSED = ? WHERE YEAR = ? AND MONTH = ?",
                new SqlQueryClient() {
                    @Override
                    public void onStatementReady(PreparedStatement statement) throws SQLException {
                        statement.setBoolean(1, true);
                        statement.setInt(2, year);
                        statement.setInt(3, month);
                    }

                    @Override
                    public void onResult(ResultSet resultSet) throws SQLException {
                    }
                });
        logger.info(Month.fromInt(month) + " " + year + " closed successfully");
    }

    public int totalBegin() {
        return accountBegin + walletBegin + payPalBegin;
    }

    public int totalEnd() {
        return accountEnd + walletEnd + payPalEnd;
    }

    public int balance() {
        int result = 0;
        result += totalEnd() - totalBegin();
        result -= Entry.sum(transfersFromSavings);
        result += Entry.sum(outOfBudgetExpenses);
        result += afterPreviousMonth;
        result += Entry.sum(debts);
        return result;
    }

    public static int balanceEntries(List<Entry> entries) {
        int result = 0;
        for (Entry entry : entries) {
            result += entry.value;
        }
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
