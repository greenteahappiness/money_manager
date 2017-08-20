package monikamichael.money_manager.gui;

import monikamichael.money_manager.engine.Goal;
import org.gnome.gtk.Button;
import org.gnome.gtk.Entry;
import org.gnome.gtk.Window;

import java.io.FileNotFoundException;
import java.io.InterruptedIOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddGoalsPage extends AbstractPage {

    Button add;
    Button cancel;

    Entry nameEntry;
    Entry priceEntry;
    Entry dateEntry;

    String enteredName;
    String enteredPrice;
    String enteredDate;

    int price;
    Date date;

    protected void initializeStructures() throws FileNotFoundException, ParseException {
        this.currentWindow = (Window) builder.getObject("add_goal_page");

        add = (Button) builder.getObject("add_goal_button");
        cancel = (Button) builder.getObject("cancel_button");

        nameEntry = (Entry) builder.getObject("name_entry");
        priceEntry = (Entry) builder.getObject("price_entry");
        dateEntry = (Entry) builder.getObject("due_date");

    }
    protected void connectButtons() {
        add.connect(new Button.Clicked() {
            @Override
            public void onClicked(Button arg0) {
                retrieveEntryParameters();
                convertParameters();
                Goal goal = new Goal(enteredName, date, price);
            }
        });
        cancel.connect(new Button.Clicked() {
            @Override
            public void onClicked(Button arg0) {
                currentWindow.destroy();
            }
        });
    }
    private void retrieveEntryParameters() {
        enteredName = nameEntry.getText();
        enteredPrice = priceEntry.getText();
        enteredDate = dateEntry.getText();
    }
    private void convertParameters() {
        try {
            price = Integer.parseInt(enteredPrice);
            DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
            date = format.parse(enteredDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
