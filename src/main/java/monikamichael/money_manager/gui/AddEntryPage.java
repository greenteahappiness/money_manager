package monikamichael.money_manager.gui;

import monikamichael.money_manager.engine.Currency;
import monikamichael.money_manager.engine.Entry;
import monikamichael.money_manager.engine.Month;
import org.gnome.gtk.*;

import java.io.FileNotFoundException;
import java.text.ParseException;

public class AddEntryPage extends AbstractPage {
    private AddEntryCallback callback;

    private org.gnome.gtk.Entry yearEntry;
    private ComboBoxText monthComboBox;
    private ComboBoxText categoryComboBox;

    private Button addButton;
    private Button quitButton;

    private org.gnome.gtk.Entry descriptionEntry;
    private org.gnome.gtk.Entry valueEntry;

    public AddEntryPage(AddEntryCallback callback, String title) {
        this.callback = callback;
        this.currentWindow.setTitle(title);
    }

    protected void initializeStructures() throws FileNotFoundException, ParseException {
        this.currentWindow = (Window) builder.getObject("add_entry_page");

        yearEntry = (org.gnome.gtk.Entry) builder.getObject("add_entry_year_entry");

        // There is a bug in Java Gtk, which disallows me to just put ComboBoxText widget in Glade and load it with
        // monthComboBox = (ComboBoxText) builder.getObject("month_combobox");
        monthComboBox = new ComboBoxText();
        for (int i = 1; i <= 12; ++i)
            monthComboBox.appendText(Month.fromInt(i));
        ((Grid) builder.getObject("grid9")).add(monthComboBox);

        String[] categories = {"Clothes", "Cosmetics", "Hobby and books", "Dates and meetings"};
        categoryComboBox = new ComboBoxText();
        for (String c : categories)
            categoryComboBox.appendText(c);
        ((Box) builder.getObject("box6")).add(categoryComboBox);

        addButton = (Button) builder.getObject("add_entry_button");
        quitButton = (Button) builder.getObject("quit_adding_entries_button");

        descriptionEntry = (org.gnome.gtk.Entry) builder.getObject("description_entry");
        valueEntry = (org.gnome.gtk.Entry) builder.getObject("value_entry");
    }

    protected void connectButtons() {
        addButton.connect(new Button.Clicked() {
            @Override
            public void onClicked(Button arg0) {
                Entry entry = new Entry(descriptionEntry.getText(), Currency.parseString(valueEntry.getText()), categoryComboBox.getActiveText());
                callback.onDataAvailable(
                        Integer.parseInt(yearEntry.getText()),
                        getMonth(),
                        entry);
                descriptionEntry.setText("");
                valueEntry.setText("");
                descriptionEntry.grabFocus();
            }
        });
        quitButton.connect(new Button.Clicked() {
            @Override
            public void onClicked(Button arg0) {
                currentWindow.destroy();
            }
        });
    }

    public int getMonth() {
        return 1 + monthComboBox.getActive();
    }
}
