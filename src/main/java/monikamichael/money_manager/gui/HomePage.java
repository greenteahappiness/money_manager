package monikamichael.money_manager.gui;

import monikamichael.money_manager.engine.Database;
import monikamichael.money_manager.engine.Entry;
import monikamichael.money_manager.engine.MonthData;
import org.gnome.gtk.Button;
import org.gnome.gtk.Window;

import java.io.FileNotFoundException;
import java.text.ParseException;

public class HomePage extends AbstractPage {

    private Button monthlyReport;
    private Button wishes;
    private Button newMonthData;
    private Button newOwnExpense;
    private Button newOutOfBudgetExpense;
    private Button newOtherExpense;
    private Button newDebt;
    private Button newTransferFromSavings;
    private Button exit;

    private Database db;

    protected void initializeStructures() throws FileNotFoundException, ParseException {
        this.currentWindow = (Window) builder.getObject("homepage");
        monthlyReport = (Button) builder.getObject("monthly_report");
        wishes = (Button) builder.getObject("wishes");
        newMonthData = (Button) builder.getObject("new_month_data");
        newOwnExpense = (Button) builder.getObject("new_own_expense");
        newOutOfBudgetExpense = (Button) builder.getObject("new_out_of_budget_expense");
        newOtherExpense = (Button) builder.getObject("new_other_expense");
        newDebt = (Button) builder.getObject("new_debt");
        newTransferFromSavings = (Button) builder.getObject("new_transfer_from_savings");
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
                    public void onDataAvailable(MonthData data, int year, int month) {
                        data.insertToDatabase(db, year, month);
                    }
                });
                enterMonthDataPage.show();
            }
        });
        newOwnExpense.connect(new Button.Clicked() {
            @Override
            public void onClicked(Button arg0) {
                AddEntryPage addEntryPage = new AddEntryPage(new AddEntryCallback() {
                    @Override
                    public void onDataAvailable(int year, int month, Entry entry) {
                        entry.insertToTable(db, "OWN_EXPENSES", year, month);
                    }
                }, "New own expense");
                addEntryPage.show();
            }
        });
        newOutOfBudgetExpense.connect(new Button.Clicked() {
            @Override
            public void onClicked(Button arg0) {
                AddEntryPage addEntryPage = new AddEntryPage(new AddEntryCallback() {
                    @Override
                    public void onDataAvailable(int year, int month, Entry entry) {
                        entry.insertToTable(db, "OOB_EXPENSES", year, month);
                    }
                }, "New out of budget expense");
                addEntryPage.show();
            }
        });
        newOtherExpense.connect(new Button.Clicked() {
            @Override
            public void onClicked(Button arg0) {
                AddEntryPage addEntryPage = new AddEntryPage(new AddEntryCallback() {
                    @Override
                    public void onDataAvailable(int year, int month, Entry entry) {
                        entry.insertToTable(db, "OTHER_EXPENSES", year, month);
                    }
                }, "New other expense");
                addEntryPage.show();
            }
        });
        newDebt.connect(new Button.Clicked() {
            @Override
            public void onClicked(Button arg0) {
                AddEntryPage addEntryPage = new AddEntryPage(new AddEntryCallback() {
                    @Override
                    public void onDataAvailable(int year, int month, Entry entry) {
                        entry.insertToTable(db, "DEBTS", year, month);
                    }
                }, "New debt");
                addEntryPage.show();
            }
        });
        newTransferFromSavings.connect(new Button.Clicked() {
            @Override
            public void onClicked(Button arg0) {
                AddEntryPage addEntryPage = new AddEntryPage(new AddEntryCallback() {
                    @Override
                    public void onDataAvailable(int year, int month, Entry entry) {
                        entry.insertToTable(db, "TRANSFERS", year, month);
                    }
                }, "New transfer");
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

        exit.connect(new Button.Clicked() {
            @Override
            public void onClicked(Button arg0) {
                System.exit(0);
            }
        });
    }
}
