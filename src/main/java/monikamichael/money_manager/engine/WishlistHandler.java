package monikamichael.money_manager.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class WishlistHandler {

    protected Logger logger = LoggerFactory.getLogger(WishlistHandler.class);
    private List<Wish> wishes = new ArrayList<Wish>();

    public void loadListOfWishes(Database db) {
        db.executeSqlQuery("SELECT * FROM wishes", new SqlQueryClient() {
            @Override
            public void onStatementReady(PreparedStatement statement) throws SQLException {
            }

            @Override
            public void onResult(ResultSet resultSet) throws SQLException {
                int counter = 0;
                while (resultSet.next()) {
                    Wish wish = new Wish();

                    wish.setName(resultSet.getString("NAME"));
                    wish.setPrice(resultSet.getInt("PRICE"));
                    wish.setDueDate(resultSet.getDate("DUE_DATE"));

                    wishes.add(wish);
                    counter += 1;
                }
                logger.info(counter + " wishes retrieved from database");

            }
        });
    }

    public void insertWishToDatabase(Database db, Wish wish) {
        if (db == null || wish == null) {
            throw new NullPointerException("One of provided arguments is null");
        }
        try {
            final String name = wish.getName();
            final int price = wish.getPrice();
            final Date dueDate = wish.getDueDate();
            final int collectedMoney = wish.getCollectedMoney();

            db.executeSqlInsert("INSERT INTO wishes " +
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
            logger.info("Wish added to database");
        } catch (NullPointerException e) {
            throw new NullPointerException("One or more of wish's attributes is null");
        }

    }

    public int getNumberOfWishes() {
        return wishes.size();
    }

    public Wish getNthWishFromDatabase(int i) {
        return wishes.get(i-1);
    }
}
