package monikamichael.money_manager.engine;

import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.mockito.Mockito.mock;

public class WishlistHandlerTest {

    WishlistHandler wishlistHandler = new WishlistHandler();
    Database db = mock(Database.class);

    @Test(expected = NullPointerException.class)
    public void throwsExceptionIfDatabaseIsNull() {
        Database db = null;
        wishlistHandler.insertWishToDatabase(db, new Wish());
    }

    @Test(expected = NullPointerException.class)
    public void throwsExceptionIfGoalIsNull() {
        Wish wish = null;
        WishlistHandler wishlistHandler = new WishlistHandler();
        wishlistHandler.insertWishToDatabase(db, wish);
    }
    @Test(expected = NullPointerException.class)
    public void throwsExceptionIfGoalAttributeIsNull() {
        WishlistHandler wishlistHandler = new WishlistHandler();
        Wish wish = mock(Wish.class);
        wishlistHandler.insertWishToDatabase(db, wish);
    }

    @Test
    public void testCreateSqlInsertQuery() throws ParseException {
        Wish wish = new Wish();
        wish.setName("Wish nr 1");
        wish.setPrice(4590);
        DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
        wish.setDueDate(new java.sql.Date(df.parse("28-10-2018").getTime()));

        //Mockito.verify(db, Mockito.times(1)).executeSqlInsert();
    }


}
