package monikamichael.money_manager.gui;

import monikamichael.money_manager.engine.Database;
import monikamichael.money_manager.engine.Month;
import monikamichael.money_manager.engine.MonthData;
import monikamichael.money_manager.engine.MonthHandler;
import org.gnome.gtk.*;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class EnteredMonthsPage extends AbstractPage {

    Database db;

    private Button closeMonth;
    private Button reopenMonth;
    private Button confirmYear;
    private Button quit;

    private ComboBoxText openMonthsComboBox;
    private ComboBoxText closedMonthsComboBox;

    private Entry yearEntry;

    MonthData monthData;
    int defaultYear = 2017;

    public EnteredMonthsPage(Database db) {
        this.db = db;
    }

    @Override
    protected void initializeStructures() throws FileNotFoundException, ParseException {
        this.currentWindow = (Window) builder.getObject("entered_months_page");
        closeMonth = (Button) builder.getObject("close_month");
        reopenMonth = (Button) builder.getObject("reopen_month");
        confirmYear = (Button) builder.getObject("confirm_year");
        quit = (Button) builder.getObject("quit_button");
        yearEntry = (Entry) builder.getObject("year_entry_close");

        openMonthsComboBox = new ComboBoxText();
        ((Box) builder.getObject("box10")).add(openMonthsComboBox);
        closedMonthsComboBox = new ComboBoxText();
        ((Box) builder.getObject("box11")).add(closedMonthsComboBox);
    }

    public void process() {
        fillMonthsComboBoxes(defaultYear);
    }

    @Override
    protected void connectButtons() {
        reopenMonth.connect(new Button.Clicked() {
            @Override
            public void onClicked(Button arg0) {
                int month = Month.toInt(closedMonthsComboBox.getActiveText());
                int year = Integer.parseInt(yearEntry.getText());
                MonthHandler.reopenMonth(db, year, month);
            }
        });
        closeMonth.connect(new Button.Clicked() {
            @Override
            public void onClicked(Button arg0) {
                int month = Month.toInt(openMonthsComboBox.getActiveText());
                int year = Integer.parseInt(yearEntry.getText());
                MonthHandler.closeMonth(db, year, month);
            }
        });
        confirmYear.connect(new Button.Clicked() {
            @Override
            public void onClicked(Button arg0) {
                int year = Integer.parseInt(yearEntry.getText());
                clearMonthsComboBoxes();
                fillMonthsComboBoxes(year);
            }
        });

        quit.connect(new Button.Clicked() {
            @Override
            public void onClicked(Button arg0) {
                currentWindow.destroy();
            }
        });
    }

    private void fillMonthsComboBoxes(int year) {
        fillOpenMonthsComboBox(year);
        fillClosedMonthsComboBox(year);
    }

    private void fillOpenMonthsComboBox(int year) {
        List<String> openMonths = MonthHandler.retrieveOpenMonthsFromDatabase(db, year);
        for (String m : openMonths) {
            openMonthsComboBox.appendText(m);
        }
    }

    private void fillClosedMonthsComboBox(int year) {
        List<String> closedMonths = MonthHandler.retrieveClosedMonthsFromDatabase(db, year);
        for (String m : closedMonths)
            closedMonthsComboBox.appendText(m);
    }
    private void clearMonthsComboBoxes() {
        for (int i=0; i <=12; i++) {
            openMonthsComboBox.removeText(0);
        }

        for (int i=0; i <=12; i++) {
            closedMonthsComboBox.removeText(0);
        }
    }
}
