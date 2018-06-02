package monikamichael.money_manager.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MonthHandler {
    protected static Logger logger = LoggerFactory.getLogger(MonthHandler.class);


    public static List<String> retrieveListOfEnteredMonths(Database db, final int year) {
        final List<String> enteredMonths = new ArrayList<>();
        db.executeSqlQuery("SELECT * FROM MONTHS WHERE YEAR = ?", new SqlQueryClient() {
            @Override
            public void onStatementReady(PreparedStatement statement) throws SQLException {
                statement.setInt(1, year);
            }

            @Override
            public void onResult(ResultSet resultSet) throws SQLException {
                while (resultSet.next()) {
                    enteredMonths.add(Month.fromInt(resultSet.getInt("MONTH")));
                }
            }
        });
        return enteredMonths;
    }
    public static List<Entry> retrieveListOfEntries(Database db,
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
                    Entry entry = EntryBuilder.entry()
                            .withDescription(resultSet.getString("DESCRIPTION"))
                            .withCategory(resultSet.getString("CATEGORY"))
                            .withValue(resultSet.getInt("VALUE"))
                            .build();

                    result.add(entry);
                }
            }
        });
        return result;
    }

    public static List<Entry> retrieveListOfEntriesFromAllExpenses(Database db,
                                                    final int year, final int month) {
        final List<Entry> result = new LinkedList<Entry>();
        result.addAll(MonthHandler.retrieveListOfEntries(db, year, month, "OWN_EXPENSES"));
        result.addAll(MonthHandler.retrieveListOfEntries(db, year, month, "PERIODIC_EXPENSES"));
        result.addAll(MonthHandler.retrieveListOfEntries(db, year, month, "OOB_EXPENSES"));
        result.addAll(MonthHandler.retrieveListOfEntries(db, year, month, "OTHER_EXPENSES"));

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
                    result.budgetIncome = resultSet.getInt("BUDGET_INCOME");
                    result.isClosed = resultSet.getBoolean("CLOSED");
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
        result.outOfBudgetEvents = retrieveListOfEntries(db, year, month, "OOB_EVENTS");
        result.debts = retrieveListOfEntries(db, year, month, "DEBTS");
        result.transfersFromSavings = retrieveListOfEntries(db, year, month, "TRANSFERS");

        return result;
    }

    public static void insertToDatabase(Database db, final MonthData md, final int year, final int month) {
        db.executeSqlInsert("INSERT INTO MONTHS " +
                "(YEAR, MONTH, WALLET_END, ACCOUNT_END, PAYPAL_END, AFTER_PREV, SALARY, BUDGET_INCOME, CLOSED) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)", new SqlQueryClient() {
            @Override
            public void onStatementReady(PreparedStatement statement) throws SQLException {
                statement.setInt(1, year);
                statement.setInt(2, month);
                statement.setInt(3, md.walletEnd);
                statement.setInt(4, md.accountEnd);
                statement.setInt(5, md.payPalEnd);
                statement.setInt(6, md.afterPreviousMonth);
                statement.setInt(7, md.salary);
                statement.setInt(8, md.budgetIncome);
                statement.setBoolean(9, md.isClosed);
            }

            @Override
            public void onResult(ResultSet resultSet) throws SQLException {
            }
        });
    }

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

    public static void reopenMonth(Database db, final int year, final int month) {
        db.executeSqlInsert(
                "UPDATE MONTHS SET CLOSED = ? WHERE YEAR = ? AND MONTH = ?",
                new SqlQueryClient() {
                    @Override
                    public void onStatementReady(PreparedStatement statement) throws SQLException {
                        statement.setBoolean(1, false);
                        statement.setInt(2, year);
                        statement.setInt(3, month);
                    }

                    @Override
                    public void onResult(ResultSet resultSet) throws SQLException {
                    }
                });
        logger.info(Month.fromInt(month) + " " + year + " reopened successfully");
    }

    public static List<String> retrieveOpenMonthsFromDatabase(Database db, int year) {
        List<String> openMonths = new ArrayList<>();

        for (String month : MonthHandler.retrieveListOfEnteredMonths(db, year)) {
            MonthData monthData = MonthHandler.retrieveForMonth(db, year, Month.toInt(month));
            if (monthData.isClosed() == false) {
                openMonths.add(month);
            }
        }
        return openMonths;
    }

    public static List<String> retrieveClosedMonthsFromDatabase(Database db, int year) {
        List<String> closedMonths = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            MonthData monthData = MonthHandler.retrieveForMonth(db, year, i);
            if (monthData.isClosed() == true) {
                closedMonths.add(Month.fromInt(i));
            }
        }
        return closedMonths;
    }
}
