package monikamichael.money_manager.gui;

import monikamichael.money_manager.engine.Database;
import monikamichael.money_manager.engine.Month;
import monikamichael.money_manager.engine.ReportGenerator;
import org.gnome.gtk.*;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;

public class MonthlyReportPage extends AbstractPage {
    private Button allMonthsReport;
    private Button oneMonthReport;
    private Button previous;
    private Button exit;

    private ComboBoxText monthChooser;
    private Entry yearEntry;
    private int defaultYear;

    private Database db;

    MonthlyReportPage(Database db) {
        super();
        this.db = db;
    }

    protected void connectButtons() {
        oneMonthReport.connect(new Button.Clicked() {
           @Override
           public void onClicked(Button button) {

               PrintWriter writer = null;
               try {
                   writer = new PrintWriter("one_month_report.html", "UTF-8");
               } catch (FileNotFoundException e) {
                   e.printStackTrace();
               } catch (UnsupportedEncodingException e) {
                   e.printStackTrace();
               }
               ReportGenerator.forOneMonth(db, writer, Month.toInt(monthChooser.getActiveText()), Integer.parseInt(yearEntry.getText()));
               writer.close();
           }
       });
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
        oneMonthReport = (Button) builder.getObject("one_month_report");
        allMonthsReport = (Button) builder.getObject("all_months_report");
        previous = (Button) builder.getObject("previous_monthly_report");
        exit = (Button) builder.getObject("exit_monthly_report");

        defaultYear = java.util.Calendar.getInstance().get(Calendar.YEAR);
        yearEntry = (Entry) builder.getObject("year_entry_report");
        yearEntry.setText(Integer.toString(defaultYear));

        monthChooser = new ComboBoxText();
        for (int i = 1; i <= 12; ++i)
            monthChooser.appendText(Month.fromInt(i));
        ((Grid) builder.getObject("grid2")).add(monthChooser);
    }
}
