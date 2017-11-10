package monikamichael.money_manager.gui;

import monikamichael.money_manager.engine.Database;
import monikamichael.money_manager.engine.ReportGenerator;
import org.gnome.gtk.Button;
import org.gnome.gtk.Window;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class MonthlyReportPage extends AbstractPage {
    private Button allMonthsReport;

    private Button previous;
    private Button exit;

    private Database db;

    MonthlyReportPage(Database db) {
        super();
        this.db = db;
    }

    protected void connectButtons() {
        allMonthsReport.connect(new Button.Clicked() {
            @Override
            public void onClicked(Button button) {
                PrintWriter writer = null;
                try {
                    writer = new PrintWriter("all_months_report.html", "UTF-8");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                ReportGenerator.forAllMonths(db, writer);
                writer.close();
            }
        });

        previous.connect(new Button.Clicked() {
            @Override
            public void onClicked(Button arg0) {

            }
        });
        exit.connect(new Button.Clicked() {
            @Override
            public void onClicked(Button arg0) {
                System.exit(0);
            }
        });

    }
    protected void initializeStructures() {
        this.currentWindow = (Window) builder.getObject("monthly_report_page");
        allMonthsReport = (Button) builder.getObject("all_months_report");
        previous = (Button) builder.getObject("previous_monthly_report");
        exit = (Button) builder.getObject("exit_monthly_report");
    }
}
