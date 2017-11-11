package monikamichael.money_manager.engine;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
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

    private static List<Entry> retrieveListOfEntries(Database db,
                                                     final int year, final int month,
                                                     final String tableName) {
        final List<Entry> result = new LinkedList<Entry>();
        // TODO Prevent SQL injection with tableName
        db.executeSqlQuery("SELECT * FROM " + tableName + " WHERE YEAR = ? AND MONTH = ?", new SqlQueryClient() {
            @Override
            public void onStatementReady(PreparedStatement statement) throws SQLException {
                statement.setInt(1, year);
                statement.setInt(2, month);
            }

            @Override
            public void onResult(ResultSet resultSet) throws SQLException {
                while (resultSet.next()) {
                    Entry entry = new Entry();
                    entry.description = resultSet.getString("DESCRIPTION");
                    entry.value = resultSet.getInt("VALUE");
                    result.add(entry);
                }
            }
        });
        return result;
    }

    public static MonthData retrieveForMonth(Database db, final int year, final int month) {
        final MonthData result = new MonthData();
        db.executeSqlQuery("SELECT * FROM MONTHS WHERE YEAR = ? AND MONTH = ?", new SqlQueryClient() {
            @Override
            public void onStatementReady(PreparedStatement statement) throws SQLException {
                statement.setInt(1, year);
                statement.setInt(2, month);
            }

            @Override
            public void onResult(ResultSet resultSet) throws SQLException {
                while (resultSet.next()) {
                    result.walletEnd = resultSet.getInt("WALLET_END");
                    result.accountEnd = resultSet.getInt("ACCOUNT_END");
                    result.payPalEnd = resultSet.getInt("PAYPAL_END");
                    result.afterPreviousMonth = resultSet.getInt("AFTER_PREV");
                    result.salary = resultSet.getInt("SALARY");
                }
            }
        });
        db.executeSqlQuery("SELECT * FROM MONTHS WHERE YEAR = ? AND MONTH = ?", new SqlQueryClient() {
            @Override
            public void onStatementReady(PreparedStatement statement) throws SQLException {
                int prevMonthYear = year;
                int prevMonthMonth = month - 1;
                if (prevMonthMonth < 1) {
                    prevMonthMonth = 12;
                    --prevMonthYear;
                }
                statement.setInt(1, prevMonthYear);
                statement.setInt(2, prevMonthMonth);
            }

            @Override
            public void onResult(ResultSet resultSet) throws SQLException {
                while (resultSet.next()) {
                    result.walletBegin = resultSet.getInt("WALLET_END");
                    result.accountBegin = resultSet.getInt("ACCOUNT_END");
                    result.payPalBegin = resultSet.getInt("PAYPAL_END");
                }
            }
        });

        result.ownExpenses = retrieveListOfEntries(db, year, month, "OWN_EXPENSES");
        result.periodicExpenses = retrieveListOfEntries(db, year, month, "PERIODIC_EXPENSES");
        result.otherExpenses = retrieveListOfEntries(db, year, month, "OTHER_EXPENSES");
        result.outOfBudgetExpenses = retrieveListOfEntries(db, year, month, "OOB_EXPENSES");
        result.debts = retrieveListOfEntries(db, year, month, "DEBTS");
        result.transfersFromSavings = retrieveListOfEntries(db, year, month, "TRANSFERS");

        return result;
    }

    public void insertToDatabase(Database db, final int year, final int month) {
        db.executeSqlInsert("INSERT INTO MONTHS " +
                            "(YEAR, MONTH, WALLET_END, ACCOUNT_END, PAYPAL_END, AFTER_PREV, SALARY) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?)", new SqlQueryClient() {
            @Override
            public void onStatementReady(PreparedStatement statement) throws SQLException {
                statement.setInt(1, year);
                statement.setInt(2, month);
                statement.setInt(3, walletEnd);
                statement.setInt(4, accountEnd);
                statement.setInt(5, payPalEnd);
                statement.setInt(6, afterPreviousMonth);
                statement.setInt(7, salary);
            }

            @Override
            public void onResult(ResultSet resultSet) throws SQLException {
            }
        });
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
