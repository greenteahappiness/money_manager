package monikamichael.money_manager.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class Database {

    protected Logger logger = LoggerFactory.getLogger(Database.class);
    public String filename;
    private Connection connection = null;

    public Database(String filename) {
        this.filename = filename;
    }

    public boolean connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + filename);
        } catch (Exception e) {
            logger.error(e.getClass().getName() + ": " + e.getMessage());
            connection = null;
            throw new RuntimeException("database: Unable to connect to " + filename);
        }
        logger.info("Opened database successfully");
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

    private void executeSqlUpdate(String sql) {
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("database: Unable to execute SQL statement");
        }
    }

    public void executeSqlQuery(String sql, SqlQueryClient client) {
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            client.onStatementReady(statement);
            ResultSet resultSet = statement.executeQuery();
            client.onResult(resultSet);
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("database: Unable to execute SQL statement");
        }
    }
    public void executeSqlInsert(String sql, SqlQueryClient client) {
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            client.onStatementReady(statement);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("database: Unable to execute SQL statement");
        }

    }

    public void executeSqlDelete(String sql, SqlQueryClient client) {
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            client.onStatementReady(statement);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("database: Unable to execute SQL statement");
        }

    }


    public void createTables() {
        executeSqlUpdate("CREATE TABLE IF NOT EXISTS MONTHS " +
                "(YEAR          INTEGER NOT NULL," +
                " MONTH         INTEGER NOT NULL," +
                " WALLET_END    INTEGER NOT NULL," +
                " ACCOUNT_END   INTEGER NOT NULL," +
                " PAYPAL_END    INTEGER NOT NULL," +
                " AFTER_PREV    INTEGER NOT NULL," +
                " SALARY        INTEGER NOT NULL," +
                " BUDGET_INCOME INTEGER NOT NULL," +
                " CLOSED        BOOLEAN NOT NULL DEFAULT FALSE," +
                " PRIMARY KEY (YEAR, MONTH))");

        executeSqlUpdate("CREATE TABLE IF NOT EXISTS OWN_EXPENSES " +
                "(YEAR        INTEGER NOT NULL," +
                " MONTH       INTEGER NOT NULL," +
                " VALUE       INTEGER NOT NULL," +
                " DESCRIPTION TEXT," +
                " CATEGORY    TEXT)");

        executeSqlUpdate("CREATE TABLE IF NOT EXISTS PERIODIC_EXPENSES " +
                "(YEAR        INTEGER NOT NULL," +
                " MONTH       INTEGER NOT NULL," +
                " VALUE       INTEGER NOT NULL," +
                " DESCRIPTION TEXT," +
                " CATEGORY    TEXT)");

        executeSqlUpdate("CREATE TABLE IF NOT EXISTS OTHER_EXPENSES " +
                "(YEAR        INTEGER NOT NULL," +
                " MONTH       INTEGER NOT NULL," +
                " VALUE       INTEGER NOT NULL," +
                " DESCRIPTION TEXT," +
                " CATEGORY    TEXT)");

        executeSqlUpdate("CREATE TABLE IF NOT EXISTS OOB_EVENTS " +
                "(YEAR        INTEGER NOT NULL," +
                " MONTH       INTEGER NOT NULL," +
                " VALUE       INTEGER NOT NULL," +
                " DESCRIPTION TEXT," +
                " CATEGORY    TEXT)");

        executeSqlUpdate("CREATE TABLE IF NOT EXISTS DEBTS " +
                "(YEAR        INTEGER NOT NULL," +
                " MONTH       INTEGER NOT NULL," +
                " VALUE       INTEGER NOT NULL," +
                " DESCRIPTION TEXT," +
                " CATEGORY    TEXT)");

        executeSqlUpdate("CREATE TABLE IF NOT EXISTS TRANSFERS " +
                "(YEAR        INTEGER NOT NULL," +
                " MONTH       INTEGER NOT NULL," +
                " VALUE       INTEGER NOT NULL," +
                " DESCRIPTION TEXT," +
                " CATEGORY    TEXT)");


        executeSqlUpdate("CREATE TABLE IF NOT EXISTS WISHES " +
                "(NAME              TEXT," +
                " PRICE             INTEGER NOT NULL," +
                " DUE_DATE          DATE," +
                " COLLECTED_MONEY   INTEGER NOT NULL," +
                " PRIMARY KEY (NAME))");
        executeSqlUpdate("CREATE TABLE IF NOT EXISTS UPCOMING_EXPENSES " +
                "(NAME              TEXT, " +
                " DESCRIPTION       TEXT," +
                " VALUE             INTEGER NOT NULL," +
                " QUEUE_NAME        TEXT)");
    }

    // Test program
    public static void main(String args[]) {
        Database db = new Database("accounts.sqlite");
        if (db.connect()) {
            db.createTables();
            db.disconnect();
        }
    }
}
