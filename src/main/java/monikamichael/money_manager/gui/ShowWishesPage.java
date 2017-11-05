package monikamichael.money_manager.gui;

import monikamichael.money_manager.engine.Database;
import monikamichael.money_manager.engine.Wish;
import monikamichael.money_manager.engine.WishlistHandler;
import org.gnome.gtk.Button;
import org.gnome.gtk.Label;
import org.gnome.gtk.Window;

public class ShowWishesPage extends AbstractPage {

    private Button nextWish;
    private Button previous;
    private Button previousWish;

    private Label wishesNumberLabel;
    private Label alreadySavedLabel;
    private Label price;
    private Label name;

    private int numberOfWishes;
    private int currentWishNum = 0;

    private WishlistHandler wishlistHandler = new WishlistHandler();
    private Database db;

    public ShowWishesPage(Database db) {
        this.db = db;
        wishlistHandler.loadListOfWishes(db);
        numberOfWishes = wishlistHandler.getNumberOfWishes();
        wishesNumberLabel.setLabel("Goals number: " + numberOfWishes);
    }

    protected void connectButtons() {
        nextWish.connect(new Button.Clicked() {
            @Override
            public void onClicked(Button arg0) {
                if (isnextWish()) {
                    currentWishNum += 1;
                    Wish nextWish = wishlistHandler.getNthWishFromDatabase(currentWishNum);
                    setWishLabels(nextWish);
                }
            }
        });
        previousWish.connect(new Button.Clicked() {
            @Override
            public void onClicked(Button arg0) {
                if (isPreviousWish()) {
                    currentWishNum -= 1;
                    Wish nextWish = wishlistHandler.getNthWishFromDatabase(currentWishNum);
                    setWishLabels(nextWish);
                }
            }
        });

        previous.connect(new Button.Clicked() {
            @Override
            public void onClicked(Button arg0) {
                currentWindow.destroy();
            }
        });

    }
    private void setWishLabels(Wish wish) {
        int price = wish.getPrice();
        float alreadySaved = wish.getAlreadySavedMoney();
        String name = wish.getName();

        this.price.setLabel("Price: " + String.valueOf(price));
        this.alreadySavedLabel.setLabel("Already saved: " + String.valueOf(alreadySaved));
        this.name.setLabel("Name: " + name);
    }

    private boolean isnextWish() {
        return currentWishNum + 1 <= numberOfWishes;
    }
    private boolean isPreviousWish() {
        return currentWishNum - 1 > 0;
    }

    protected void initializeStructures() {
        this.currentWindow = (Window) builder.getObject("show_goals_page");
        nextWish = (Button) builder.getObject("next_goal");
        previous = (Button) builder.getObject("show_goals_back");
        previousWish = (Button) builder.getObject("previous_goal");
        wishesNumberLabel = (Label) builder.getObject("goals_number_label");
        price = (Label) builder.getObject("goal_price");
        alreadySavedLabel= (Label) builder.getObject("goal_already_saved");
        name = (Label) builder.getObject("goal_name");

    }
}