package monikamichael.money_manager.engine;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.mockito.Mockito.mock;

public class WishlistHandlerTest {

    WishlistHandler wishlistHandler = new WishlistHandler();
    Database db;

    @Before
    public void beforeClassInit() {
        db = new Database("test.sqlite");
        if (db.connect()) {
            db.createTables();
        }
        clearAllWishes();
    }

    @Test(expected = NullPointerException.class)
    public void throwsExceptionIfDatabaseIsNull() {
        Database db = null;
        wishlistHandler.insertWishToDatabase(db, new Wish());
    }

    @Test(expected = NullPointerException.class)
    public void throwsExceptionIfWishIsNull() {
        Wish wish = null;
        WishlistHandler wishlistHandler = new WishlistHandler();
        wishlistHandler.insertWishToDatabase(db, wish);
    }
    
    @Test
    public void deletesWishFromDatabase() {
        Wish wish = createWish("test1");
        wishlistHandler.loadListOfWishes(db);
        wishlistHandler.insertWishToDatabase(db, wish);
        wishlistHandler.loadListOfWishes(db);
        int before = wishlistHandler.getNumberOfWishes();

        wishlistHandler.deleteWishFromDatabase(db, wish);
        wishlistHandler.loadListOfWishes(db);
        int after = wishlistHandler.getNumberOfWishes();

        assert before-after==1;
    }

    @Test
    public void testCreateSqlInsertQuery() throws ParseException {
        Wish wish = createWish("test2");

        wishlistHandler.loadListOfWishes(db);
        int before = wishlistHandler.getNumberOfWishes();

        wishlistHandler.insertWishToDatabase(db, wish);
        wishlistHandler.loadListOfWishes(db);
        int after = wishlistHandler.getNumberOfWishes();

        assert after-before==1;
    }

    private void clearAllWishes() {
        wishlistHandler.deleteAllWishesFromDatabase(db);
    }

    private Wish createWish(String key) {

        Wish wish = new Wish();

        wish.setName(key);
        wish.setPrice(200);
        wish.setDueDate(new Date(2017, 03, 20));

        return wish;
    }
}
