package monikamichael.money_manager.gui;

import monikamichael.money_manager.engine.*;
import org.gnome.gtk.Button;
import org.gnome.gtk.Window;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.List;

public class HomePage extends AbstractPage {

    private Button monthlyReport;
    private Button wishes;
    private Button newMonthData;
    private Button newExpense;
    private Button enteredMonths;
    private Button exit;

    private Database db;

    protected void initializeStructures() throws FileNotFoundException, ParseException {
        this.currentWindow = (Window) builder.getObject("homepage");
        monthlyReport = (Button) builder.getObject("monthly_report");
        wishes = (Button) builder.getObject("wishes");
        newMonthData = (Button) builder.getObject("new_month_data");
        newExpense = (Button) builder.getObject("new_expense");
        enteredMonths = (Button) builder.getObject("entered_months_button");
        exit = (Button) builder.getObject("exit");

        db = new Database("accounts.sqlite");
        if (db.connect()) {
            db.createTables();
        }
    }

    protected void connectButtons() {
        wishes.connect(new Button.Clicked() {
            @Override
            public void onClicked(Button arg0) {
                WishlistPage wishlistPage = new WishlistPage(db);
                wishlistPage.show();
            }
        });
        newMonthData.connect(new Button.Clicked() {
            @Override
            public void onClicked(Button arg0) {
                EnterMonthDataPage enterMonthDataPage = new EnterMonthDataPage(new EnterMonthDataCallback() {
                    @Override
                    public void onDataAvailable(MonthData data, int year, int month, boolean shouldCopyPeriodic) {
                        MonthHandler.insertToDatabase(db, data, year, month);
                        if (shouldCopyPeriodic) {
                            copyPeriodicExpensesFromLastMonth(year, month);
                        }
                    }
                });
                enterMonthDataPage.show();
            }
        });

        newExpense.connect(new Button.Clicked() {
            @Override
            public void onClicked(Button arg0) {
                AddEntryPage addEntryPage = new AddEntryPage(new AddEntryCallback() {
                    @Override
                    public void onDataAvailable(int year, int month, Entry entry) {
                        EntryHandler.insertToTable(db, entry.entryType.toUpperCase(), year, month, entry);
                    }
                }, "New expense");
                addEntryPage.show();
            }
        });

        monthlyReport.connect(new Button.Clicked() {
            @Override
            public void onClicked(Button arg0) {
                MonthlyReportPage monthlyReportPage = new MonthlyReportPage(db);
                monthlyReportPage.show();
            }
        });

        enteredMonths.connect(new Button.Clicked() {
            @Override
            public void onClicked(Button arg0) {
                EnteredMonthsPage enteredMonthsPage = new EnteredMonthsPage(db);
                enteredMonthsPage.process();
                enteredMonthsPage.show();
            }
        });

        exit.connect(new Button.Clicked() {
            @Override
            public void onClicked(Button arg0) {
                System.exit(0);
            }
        });
    }

    private void copyPeriodicExpensesFromLastMonth(int year, int month) {
        int lastMonth = month - 1;
        List<Entry> periodicExpensesLastMonth = MonthHandler.retrieveListOfEntries(
                db, year, lastMonth, "PERIODIC_EXPENSES");

        for (Entry entry : periodicExpensesLastMonth) {
            EntryHandler.insertToTable(db, "PERIODIC_EXPENSES", year, month, entry);
        }
    }
}
