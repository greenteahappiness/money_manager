package monikamichael.money_manager.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EntryHandler {
    protected static Logger logger = LoggerFactory.getLogger(EntryHandler.class);

    public static void insertToTable(Database db, String tableName, final int year, final int month,
                                     final Entry entry) {
        // TODO Prevent SQL injection with tableName
        db.executeSqlInsert("INSERT INTO " + tableName + " " +
                "(YEAR, MONTH, VALUE, DESCRIPTION, CATEGORY) " +
                "VALUES (?, ?, ?, ?, ?)", new SqlQueryClient() {
            @Override
            public void onStatementReady(PreparedStatement statement) throws SQLException {
                statement.setInt(1, year);
                statement.setInt(2, month);
                statement.setInt(3, entry.value);
                statement.setString(4, entry.description);
                statement.setString(5, entry.category);
            }

            @Override
            public void onResult(ResultSet resultSet) throws SQLException {
            }
        });
    }

    public static void deleteFromTable(
            Database db, String tableName, final int year, final int month, final String entryId) {

        logger.info(entryId);
        db.executeSqlDelete("DELETE FROM " + tableName + " " +
                "WHERE (YEAR = ? AND MONTH = ? AND DESCRIPTION = ?)", new SqlQueryClient() {
            @Override
            public void onStatementReady(PreparedStatement statement) throws SQLException {
                statement.setInt(1, year);
                statement.setInt(2, month);
                statement.setString(3, entryId);
            }

            @Override
            public void onResult(ResultSet resultSet) throws SQLException {
            }
        });
    }
}
