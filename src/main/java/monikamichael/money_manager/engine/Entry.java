package monikamichael.money_manager.engine;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

// An entry is a financial entry in tables.
// It is a pair (description, value).
public class Entry {
    public String description;
    public int value;

    public Entry() {
    }

    public Entry(String description, int value) {
        this.description = description;
        this.value = value;
    }

    public static int sum(List<Entry> entries) {
        int result = 0;
        for (Entry entry : entries)
            result += entry.value;

        return result;
    }


    public void insertToTable(Database db, String tableName, final int year, final int month) {
        // TODO Prevent SQL injection with tableName
        db.executeSqlInsert("INSERT INTO " + tableName + " " +
                "(YEAR, MONTH, VALUE, DESCRIPTION) " +
                "VALUES (?, ?, ?, ?)", new SqlQueryClient() {
            @Override
            public void onStatementReady(PreparedStatement statement) throws SQLException {
                statement.setInt(1, year);
                statement.setInt(2, month);
                statement.setInt(3, value);
                statement.setString(4, description);
            }

            @Override
            public void onResult(ResultSet resultSet) throws SQLException {
            }
        });
    }

}
