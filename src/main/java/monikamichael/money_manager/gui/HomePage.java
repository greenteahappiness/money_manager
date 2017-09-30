package monikamichael.money_manager.gui;

import monikamichael.money_manager.engine.Database;
import monikamichael.money_manager.engine.Goal;
import org.gnome.gtk.Builder;
import org.gnome.gtk.Button;
import org.gnome.gtk.Window;

import java.io.FileNotFoundException;
import java.text.ParseException;

public class HomePage extends AbstractPage {

    Button monthlyReport;
    Button goals;
    Button exit;

    Database db;

    protected void initializeStructures() throws FileNotFoundException, ParseException {
        this.currentWindow = (Window) builder.getObject("homepage");
        monthlyReport = (Button) builder.getObject("monthly_report");
        goals = (Button) builder.getObject("goals");
        exit = (Button) builder.getObject("exit");

        db = new Database();
        if (db.connect()) {
            db.createTables();
        }
    }

    protected void connectButtons() {
        goals.connect(new Button.Clicked() {
            @Override
            public void onClicked(Button arg0) {
                GoalsPage goalsPage = new GoalsPage(db);
                goalsPage.show();
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
