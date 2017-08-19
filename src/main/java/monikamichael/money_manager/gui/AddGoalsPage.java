package monikamichael.money_manager.gui;

import org.gnome.gtk.Button;
import org.gnome.gtk.Entry;
import org.gnome.gtk.Window;

import java.io.FileNotFoundException;
import java.text.ParseException;

public class AddGoalsPage extends AbstractPage {

    Button add;
    Button cancel;

    Entry nameEntry;
    Entry priceEntry;
    Entry dateEntry;

    String enteredName;
    String enteredPrice;
    String enteredDate;

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
                enteredName = nameEntry.getText();
                enteredPrice = priceEntry.getText();
                enteredDate = dateEntry.getText();
                System.out.println("Name: " + enteredName + "\n Price: " + enteredPrice);
                //TODO: parse somewhere enteredPrice to int and enteredDate to Date
            }
        });
        cancel.connect(new Button.Clicked() {
            @Override
            public void onClicked(Button arg0) {

            }
        });
    }
}
