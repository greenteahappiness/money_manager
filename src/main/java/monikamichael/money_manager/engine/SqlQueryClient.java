package monikamichael.money_manager.engine;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface SqlQueryClient {
    void onStatementReady(PreparedStatement statement) throws SQLException;
    void onResult(ResultSet resultSet) throws SQLException;
}
