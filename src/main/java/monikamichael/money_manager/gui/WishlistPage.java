package monikamichael.money_manager.gui;

import monikamichael.money_manager.engine.Database;
import org.gnome.gtk.Button;
import org.gnome.gtk.Window;

import java.io.FileNotFoundException;
import java.text.ParseException;

public class WishlistPage extends AbstractPage {

    private Button addWishButton;
    private Button showWishes;
    private Button cancel;

    private Database db;

    public WishlistPage(Database db) {
        this.db = db;
    }

    protected void initializeStructures() throws FileNotFoundException, ParseException {
        this.currentWindow = (Window) builder.getObject("goals_page");
        addWishButton = (Button) builder.getObject("add_goal");
        showWishes = (Button) builder.getObject("show_goals");
        cancel = (Button) builder.getObject("cancel_goals");
    }

    protected void connectButtons() {
        addWishButton.connect(new Button.Clicked() {
            @Override
            public void onClicked(Button arg0) {
                AddWishesPage addWishesPage = new AddWishesPage(db);
                addWishesPage.show();
            }
        });
        showWishes.connect(new Button.Clicked() {
            @Override
            public void onClicked(Button arg0) {
                ShowWishesPage showWishesPage = new ShowWishesPage(db);
                showWishesPage.show();
            }
        });
        cancel.connect(new Button.Clicked() {
            @Override
            public void onClicked(Button arg0) {
                currentWindow.destroy();
            }
        });
    }
}
