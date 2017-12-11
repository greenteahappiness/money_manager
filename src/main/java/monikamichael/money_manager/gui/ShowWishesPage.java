package monikamichael.money_manager.gui;

import monikamichael.money_manager.engine.Database;
import monikamichael.money_manager.engine.Wish;
import monikamichael.money_manager.engine.WishlistHandler;
import org.gnome.gtk.Button;
import org.gnome.gtk.Entry;
import org.gnome.gtk.Label;
import org.gnome.gtk.Window;

public class ShowWishesPage extends AbstractPage {

    private Button deleteWish;
    private Button nextWish;
    private Button previous;
    private Button previousWish;
    private Button transferMoney;

    private Label wishesNumberLabel;
    private Label alreadySavedLabel;
    private Label price;
    private Label name;

    private Entry moneyToTransferEntry;

    private int numberOfWishes;
    private int currentWishNum = 0;

    private WishlistHandler wishlistHandler = new WishlistHandler();
    private Database db;

    public ShowWishesPage(Database db) {
        this.db = db;
        refreshData();
    }

    protected void connectButtons() {
        transferMoney.connect(new Button.Clicked() {
            @Override
            public void onClicked(Button arg0) {
                int money = Integer.parseInt(moneyToTransferEntry.getText());
                Wish currentWish = wishlistHandler.getNthWishFromDatabase(currentWishNum);
                currentWish.transferMoneyForWish(money);
                alreadySavedLabel.setLabel("Already saved: " + String.valueOf(currentWish.getAlreadySavedMoney()));
                wishlistHandler.updateWishDataInDatabase(db, currentWish);
            }
        });
        deleteWish.connect(new Button.Clicked() {
            @Override
            public void onClicked(Button arg0) {
                Wish currentWish = wishlistHandler.getNthWishFromDatabase(currentWishNum);
                wishlistHandler.deleteWishFromDatabase(db, currentWish);
                refreshData();
            }
        });
        nextWish.connect(new Button.Clicked() {
            @Override
            public void onClicked(Button arg0) {
                if (isNextWish()) {
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

    private boolean isNextWish() {
        return currentWishNum + 1 <= numberOfWishes;
    }
    private boolean isPreviousWish() {
        return currentWishNum - 1 > 0;
    }

    private void refreshData() {
        wishlistHandler.loadListOfWishes(db);
        numberOfWishes = wishlistHandler.getNumberOfWishes();
        wishesNumberLabel.setLabel("Wishes number: " + numberOfWishes);
    }
    protected void initializeStructures() {
        this.currentWindow = (Window) builder.getObject("show_wishes_page");
        transferMoney = (Button) builder.getObject("transfer_button");
        deleteWish = (Button) builder.getObject("delete_wish_button");
        nextWish = (Button) builder.getObject("next_wish");
        previous = (Button) builder.getObject("show_wish_back");
        previousWish = (Button) builder.getObject("previous_wish");
        wishesNumberLabel = (Label) builder.getObject("wishes_number_label");
        price = (Label) builder.getObject("wish_price");
        alreadySavedLabel= (Label) builder.getObject("wish_already_saved");
        name = (Label) builder.getObject("wish_name");
        moneyToTransferEntry = (Entry) builder.getObject("wish_money_entry");

    }
}