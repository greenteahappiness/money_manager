package monikamichael.money_manager.engine;

import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.mockito.Mockito.mock;

public class GoalsHandlerTest {

    GoalsHandler goalsHandler = new GoalsHandler();
    Database db = mock(Database.class);

    @Test(expected = NullPointerException.class)
    public void throwsExceptionIfDatabaseIsNull() {
        Database db = null;
        goalsHandler.insertGoalToDatabase(db, new Goal());
    }

    @Test(expected = NullPointerException.class)
    public void throwsExceptionIfGoalIsNull() {
        Goal goal = null;
        GoalsHandler goalsHandler = new GoalsHandler();
        goalsHandler.insertGoalToDatabase(db, goal);
    }
    @Test(expected = NullPointerException.class)
    public void throwsExceptionIfGoalAttributeIsNull() {
        GoalsHandler goalsHandler = new GoalsHandler();
        Goal goal = mock(Goal.class);
        goalsHandler.insertGoalToDatabase(db, goal);
    }

    @Test
    public void testCreateSqlInsertQuery() throws ParseException {
        Goal goal = new Goal();
        goal.setName("Goal nr 1");
        goal.setPrice(4590);
        DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
        goal.setDueDate(new java.sql.Date(df.parse("28-10-2018").getTime()));

        //Mockito.verify(db, Mockito.times(1)).executeSqlInsert();
    }


}
