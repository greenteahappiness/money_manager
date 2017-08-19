package monikamichael.money_manager.engine;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

        // TODO Retrieve other fields, those which are lists

        return result;
    }

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
