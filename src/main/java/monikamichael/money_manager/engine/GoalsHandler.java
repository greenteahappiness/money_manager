package monikamichael.money_manager.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class GoalsHandler {
    protected Logger logger = LoggerFactory.getLogger(GoalsHandler.class);

    public List<Goal> goals = new ArrayList<Goal>();

    public void retrieveListOfGoals(Database db, String tableName, final Date dueDate) {
        db.executeSqlQuery("SELECT * FROM " + tableName + " WHERE DUE_DATE < ?", new SqlQueryClient() {
            @Override
            public void onStatementReady(PreparedStatement statement) throws SQLException {
                statement.setDate(1, dueDate);
            }

            @Override
            public void onResult(ResultSet resultSet) throws SQLException {
                int counter = 0;
                while (resultSet.next()) {
                    Goal goal = new Goal();

                    goal.setName(resultSet.getString("NAME"));
                    goal.setPrice(resultSet.getInt("PRICE"));
                    goal.setDueDate(resultSet.getDate("DUE_DATE"));

                    goals.add(goal);
                    counter += 1;
                }
                logger.info(counter + " goals retrieved from database");

            }
        });
    }

    public void insertGoalToDatabase(Database db, Goal goal) {
        if (db == null || goal == null) {
            throw new NullPointerException("One of provided arguments is null");
        }
        try {
            final String name = goal.getName();
            final int price = goal.getPrice();
            final Date dueDate = goal.getDueDate();
            final int collectedMoney = goal.getCollectedMoney();

            db.executeSqlInsert("INSERT INTO GOALS " +
                    "VALUES (?, ?, ?, ?)", new SqlQueryClient() {
                @Override
                public void onStatementReady(PreparedStatement statement) throws SQLException {
                    statement.setString(1, name);
                    statement.setInt(2, price);
                    statement.setDate(3, dueDate);
                    statement.setInt(4, collectedMoney);
                }

                @Override
                public void onResult(ResultSet resultSet) throws SQLException {
                    //do nothing
                }
            });
            logger.info("Goal added to database");
        } catch (NullPointerException e) {
            throw new NullPointerException("One or more of goal's attributes is null");
        }

    }
}
