package monikamichael.money_manager.engine;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class UpcomingExpensesHandler {

    public static List<Entry> retrieveListOfEntries(Database db,
                                                    final String queueName) {
        final List<Entry> result = new LinkedList<>();
        // TODO Prevent SQL injection with queueName
        db.executeSqlQuery("SELECT * FROM UPCOMING_EXPENSES WHERE QUEUE_NAME = ?", new SqlQueryClient() {
            @Override
            public void onStatementReady(PreparedStatement statement) throws SQLException {
                statement.setString(1, queueName);
            }

            @Override
            public void onResult(ResultSet resultSet) throws SQLException {
                while (resultSet.next()) {
                    Entry entry = EntryBuilder.entry()
                            .withDescription(resultSet.getString("DESCRIPTION"))
                            .withCategory(resultSet.getString("CATEGORY"))
                            .withValue(resultSet.getInt("VALUE"))
                            .withQueueName(resultSet.getString("QUEUE_NAME"))
                            .build();

                    result.add(entry);
                }
            }
        });
        return result;
    }
}
