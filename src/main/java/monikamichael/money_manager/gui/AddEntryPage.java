package monikamichael.money_manager.gui;

import monikamichael.money_manager.engine.Currency;
import monikamichael.money_manager.engine.Entry;
import monikamichael.money_manager.engine.EntryBuilder;
import monikamichael.money_manager.engine.Month;
import org.gnome.gtk.*;

import java.io.FileNotFoundException;
import java.text.ParseException;

public class AddEntryPage extends AbstractPage {
    private AddEntryCallback callback;

    private org.gnome.gtk.Entry yearEntry;
    private ComboBoxText monthComboBox;
    private ComboBoxText categoryComboBox;
    private ComboBoxText expenseTypeComboBox;

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

        createMonthComboBox();
        createCategoryComboBox();
        createEntryTypeComboBox();

        addButton = (Button) builder.getObject("add_entry_button");
        quitButton = (Button) builder.getObject("quit_adding_entries_button");

        descriptionEntry = (org.gnome.gtk.Entry) builder.getObject("description_entry");
        valueEntry = (org.gnome.gtk.Entry) builder.getObject("value_entry");
    }

    protected void connectButtons() {
        addButton.connect(new Button.Clicked() {
            @Override
            public void onClicked(Button arg0) {
                Entry entry = EntryBuilder.entry()
                        .withDescription(descriptionEntry.getText())
                        .withCategory(categoryComboBox.getActiveText())
                        .withEntryType(expenseTypeComboBox.getActiveText())
                        .withValue(Currency.parseString(valueEntry.getText()))
                        .build();

                callback.onDataAvailable(
                        Integer.parseInt(yearEntry.getText()),
                        getMonth(),
                        entry);

                clearEntryBoxes();
            }
        });

        quitButton.connect(new Button.Clicked() {
            @Override
            public void onClicked(Button arg0) {
                currentWindow.destroy();
            }
        });
    }

    private void clearEntryBoxes() {
        descriptionEntry.setText("");
        valueEntry.setText("");
        descriptionEntry.grabFocus();
    }

    public int getMonth() {
        return 1 + monthComboBox.getActive();
    }

    private void createEntryTypeComboBox() {
        String[] entriesTypes = {"Own_Expenses", "Periodic_Expenses", "Other_Expenses",
                "OOB_Expenses", "Debts", "Transfers"};
        expenseTypeComboBox = new ComboBoxText();
        for (String t : entriesTypes)
            expenseTypeComboBox.appendText(t);
        ((Box) builder.getObject("box9")).add(expenseTypeComboBox);
    }

    private void createMonthComboBox() {
        // There is a bug in Java Gtk, which disallows me to just put ComboBoxText widget in Glade and load it with
        // monthComboBox = (ComboBoxText) builder.getObject("month_combobox");
        monthComboBox = new ComboBoxText();
        for (int i = 1; i <= 12; ++i)
            monthComboBox.appendText(Month.fromInt(i));
        ((Grid) builder.getObject("grid9")).add(monthComboBox);
    }

    private void createCategoryComboBox() {
        String[] categories = new String[]{"Clothes", "Cosmetics", "Hobby and books", "Dates and meetings", "Other"};
        categoryComboBox = new ComboBoxText();
        for (String c : categories) {
            categoryComboBox.appendText(c);
        }
        ((Box) builder.getObject("box6")).add(categoryComboBox);
    }
}
