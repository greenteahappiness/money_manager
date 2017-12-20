package monikamichael.money_manager.engine;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

// An entry is a financial entry in tables.
// It is a pair (description, value, category).
public class Entry {
    public String entryType;
    public String description;
    public String category;
    public int value;

    public Entry() {
    }

    public Entry(String description, int value, String category, String entryType) {
        this.description = description;
        this.value = value;
        this.category = category;
        this.entryType = entryType;
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
                "(YEAR, MONTH, VALUE, DESCRIPTION, CATEGORY) " +
                "VALUES (?, ?, ?, ?, ?)", new SqlQueryClient() {
            @Override
            public void onStatementReady(PreparedStatement statement) throws SQLException {
                statement.setInt(1, year);
                statement.setInt(2, month);
                statement.setInt(3, value);
                statement.setString(4, description);
                statement.setString(5, category);
            }

            @Override
            public void onResult(ResultSet resultSet) throws SQLException {
            }
        });
    }

}
