package monikamichael.money_manager.gui;

import monikamichael.money_manager.engine.Currency;
import monikamichael.money_manager.engine.Month;
import monikamichael.money_manager.engine.MonthData;
import org.gnome.gtk.*;

import java.io.FileNotFoundException;
import java.text.ParseException;

public class EnterMonthDataPage extends AbstractPage {
    private EnterMonthDataCallback callback;

    private Entry yearEntry;
    private ComboBoxText monthComboBox;

    private Button applyButton;
    private Button cancelButton;

    private Entry walletEndEntry;
    private Entry accountEndEntry;
    private Entry paypalEndEntry;
    private Entry afterPrevEntry;
    private Entry salaryEntry;

    private CheckButton rewritePeriodicCheckbox;

    public EnterMonthDataPage(EnterMonthDataCallback callback) {
        this.callback = callback;
    }

    protected void initializeStructures() throws FileNotFoundException, ParseException {
        this.currentWindow = (Window) builder.getObject("enter_month_data_page");

        yearEntry = (Entry) builder.getObject("year_entry");

        // There is a bug in Java Gtk, which disallows me to just put ComboBoxText widget in Glade and load it with
        // monthComboBox = (ComboBoxText) builder.getObject("month_combobox");
        monthComboBox = new ComboBoxText();
        for (int i = 1; i <= 12; ++i)
            monthComboBox.appendText(Month.fromInt(i));
        ((Box) builder.getObject("box4")).add(monthComboBox);

        applyButton = (Button) builder.getObject("enter_month_ok");
        cancelButton = (Button) builder.getObject("enter_month_cancel");

        walletEndEntry = (Entry) builder.getObject("wallet_end_entry");
        accountEndEntry = (Entry) builder.getObject("account_end_entry");
        paypalEndEntry = (Entry) builder.getObject("paypal_end_entry");
        afterPrevEntry = (Entry) builder.getObject("after_prev_entry");
        salaryEntry = (Entry) builder.getObject("salary_entry");

        rewritePeriodicCheckbox = (CheckButton) builder.getObject("rewrite_periodic_checkbox");
    }

    protected void connectButtons() {
        applyButton.connect(new Button.Clicked() {
            @Override
            public void onClicked(Button arg0) {
                callback.onDataAvailable(getMonthData(),
                        Integer.parseInt(yearEntry.getText()),
                        getMonth(), rewritePeriodicCheckbox.getActive());
                currentWindow.destroy();
            }
        });
        cancelButton.connect(new Button.Clicked() {
            @Override
            public void onClicked(Button arg0) {
                currentWindow.destroy();
            }
        });
    }

    public int getMonth() {
        return 1 + monthComboBox.getActive();
    }

    public MonthData getMonthData() {
        MonthData result = new MonthData();
        result.walletEnd = Currency.parseString(walletEndEntry.getText());
        result.accountEnd = Currency.parseString(accountEndEntry.getText());
        result.payPalEnd = Currency.parseString(paypalEndEntry.getText());
        result.afterPreviousMonth = Currency.parseString(afterPrevEntry.getText());
        result.salary = Currency.parseString(salaryEntry.getText());
        return result;
    }
}
