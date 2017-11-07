package monikamichael.money_manager.gui;

import monikamichael.money_manager.engine.Database;
import monikamichael.money_manager.engine.MonthData;
import org.gnome.gtk.Button;
import org.gnome.gtk.Window;

import java.io.FileNotFoundException;
import java.text.ParseException;

public class HomePage extends AbstractPage {

    private Button monthlyReport;
    private Button wishes;
    private Button newData;
    private Button exit;

    private Database db;

    protected void initializeStructures() throws FileNotFoundException, ParseException {
        this.currentWindow = (Window) builder.getObject("homepage");
        monthlyReport = (Button) builder.getObject("monthly_report");
        wishes = (Button) builder.getObject("wishes");
        newData = (Button) builder.getObject("new_data");
        exit = (Button) builder.getObject("exit");

        db = new Database();
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
        newData.connect(new Button.Clicked() {
            @Override
            public void onClicked(Button arg0) {
                EnterMonthDataPage enterMonthDataPage = new EnterMonthDataPage(new EnterMonthDataCallback() {
                    @Override
                    public void onDataAvailable(MonthData data) {
                        System.out.println("Data returned:" + data.toString());
                    }
                });
                enterMonthDataPage.show();
            }
        });
        monthlyReport.connect(new Button.Clicked() {
            @Override
            public void onClicked(Button arg0) {
                MonthlyReportPage monthlyReportPage = new MonthlyReportPage();
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
