package monikamichael.money_manager.engine;

import java.sql.*;

public class Database {
    public static final String filename = "accounts.sqlite";
    private Connection connection = null;

    public boolean connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + filename);
        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            connection = null;
            throw new RuntimeException("database: Unable to connect to " + filename);
        }
        System.out.println("Opened database successfully");
        return isConnected();
    }

    public void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connection = null;
    }

    public boolean isConnected() {
        return connection != null;
    }

    private Statement createStatement() {
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("database: Unable to create tables");
        }
        return stmt;
    }

    private void executeSqlUpdate(String sql) {
        Statement stmt = createStatement();
        try {
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("database: Unable to execute SQL statement");
        }
    }

    public void createTables() {
        executeSqlUpdate("CREATE TABLE IF NOT EXISTS MONTHS " +
                "(YEAR        INTEGER NOT NULL," +
                " MONTH       INTEGER NOT NULL," +
                " WALLET_END  INTEGER NOT NULL," +
                " ACCOUNT_END INTEGER NOT NULL," +
                " PAYPAL_END  INTEGER NOT NULL," +
                " AFTER_PREV  INTEGER NOT NULL," +
                " SALARY      INTEGER NOT NULL," +
                " PRIMARY KEY (YEAR, MONTH))");

        executeSqlUpdate("CREATE TABLE IF NOT EXISTS OWN_EXPENSES " +
                "(YEAR        INTEGER NOT NULL," +
                " MONTH       INTEGER NOT NULL," +
                " VALUE       INTEGER NOT NULL," +
                " DESCRIPTION TEXT)");

        executeSqlUpdate("CREATE TABLE IF NOT EXISTS PERIODIC_EXPENSES " +
                "(YEAR        INTEGER NOT NULL," +
                " MONTH       INTEGER NOT NULL," +
                " VALUE       INTEGER NOT NULL," +
                " DESCRIPTION TEXT)");

        executeSqlUpdate("CREATE TABLE IF NOT EXISTS OTHER_EXPENSES " +
                "(YEAR        INTEGER NOT NULL," +
                " MONTH       INTEGER NOT NULL," +
                " VALUE       INTEGER NOT NULL," +
                " DESCRIPTION TEXT)");

        executeSqlUpdate("CREATE TABLE IF NOT EXISTS OOB_EXPENSES " +
                "(YEAR        INTEGER NOT NULL," +
                " MONTH       INTEGER NOT NULL," +
                " VALUE       INTEGER NOT NULL," +
                " DESCRIPTION TEXT)");

        executeSqlUpdate("CREATE TABLE IF NOT EXISTS DEBTS " +
                "(YEAR        INTEGER NOT NULL," +
                " MONTH       INTEGER NOT NULL," +
                " VALUE       INTEGER NOT NULL," +
                " DESCRIPTION TEXT)");

        executeSqlUpdate("CREATE TABLE IF NOT EXISTS TRANSFERS " +
                "(YEAR        INTEGER NOT NULL," +
                " MONTH       INTEGER NOT NULL," +
                " VALUE       INTEGER NOT NULL," +
                " DESCRIPTION TEXT)");
    }

    // Test program
    public static void main(String args[]) {
        Database db = new Database();
        if (db.connect()) {
            db.createTables();
            db.disconnect();
        }
    }
}
