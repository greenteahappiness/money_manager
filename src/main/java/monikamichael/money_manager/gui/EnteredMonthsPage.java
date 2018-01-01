package monikamichael.money_manager.gui;

import monikamichael.money_manager.engine.Database;
import monikamichael.money_manager.engine.Month;
import monikamichael.money_manager.engine.MonthData;
import monikamichael.money_manager.engine.MonthHandler;
import org.gnome.gtk.Box;
import org.gnome.gtk.Button;
import org.gnome.gtk.ComboBoxText;
import org.gnome.gtk.Window;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class EnteredMonthsPage extends AbstractPage {

    Database db;

    private Button closeMonth;
    private Button reopenMonth;
    private Button quit;

    private ComboBoxText openMonthsComboBox;
    private ComboBoxText closedMonthsComboBox;

    MonthData monthData;

    public EnteredMonthsPage(Database db) {
        this.db = db;
    }

    @Override
    protected void initializeStructures() throws FileNotFoundException, ParseException {
        this.currentWindow = (Window) builder.getObject("entered_months_page");
        closeMonth = (Button) builder.getObject("close_month");
        reopenMonth = (Button) builder.getObject("reopen_month");
        quit = (Button) builder.getObject("quit_button");

        openMonthsComboBox = new ComboBoxText();
        ((Box) builder.getObject("box10")).add(openMonthsComboBox);
        closedMonthsComboBox = new ComboBoxText();
        ((Box) builder.getObject("box11")).add(closedMonthsComboBox);
    }

    @Override
    protected void connectButtons() {
        closeMonth.connect(new Button.Clicked() {
            @Override
            public void onClicked(Button arg0) {
                int month = Month.toInt(openMonthsComboBox.getActiveText());
                MonthHandler.closeMonth(db, 2017, month);
            }
        });

        quit.connect(new Button.Clicked() {
            @Override
            public void onClicked(Button arg0) {
                currentWindow.destroy();
            }
        });
    }

    public void process() {
        fillOpenMonthsComboBox();
        fillClosedMonthsComboBox();
    }
    private void fillOpenMonthsComboBox() {
        //TODO: this method does too much - extract to engine package
        List<String> openMonths = new ArrayList<>();
        int year = 2017;
        for (int i=1; i<=12; i++) {
            monthData = MonthHandler.retrieveForMonth(db, year, i);
            if (monthData != null && monthData.isClosed == false) {
                openMonths.add(Month.fromInt(i));
            }
        }
        for (String m : openMonths)
            openMonthsComboBox.appendText(m);
    }

    private void fillClosedMonthsComboBox() {
        List<String> closedMonths = new ArrayList<>();
        int year = 2017;
        for (int i=1; i<=1; i++) {
            monthData = MonthHandler.retrieveForMonth(db, year, i);
            if (monthData != null && monthData.isClosed == true) {
                closedMonths.add(Month.fromInt(i));
            }
        }
        for (String m : closedMonths)
            closedMonthsComboBox.appendText(m);
    }

}
