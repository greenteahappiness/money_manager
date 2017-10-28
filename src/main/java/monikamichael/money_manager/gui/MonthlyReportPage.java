package monikamichael.money_manager.gui;

import org.gnome.gtk.Button;
import org.gnome.gtk.Window;

public class MonthlyReportPage extends AbstractPage {
    private Button previous;
    private Button exit;

    MonthlyReportPage() {
        super();
    }
    protected void connectButtons() {
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
        previous = (Button) builder.getObject("previous_monthly_report");
        exit = (Button) builder.getObject("exit_monthly_report");
    }
}
