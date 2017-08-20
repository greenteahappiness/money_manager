package monikamichael.money_manager.gui;

import org.gnome.gtk.Button;
import org.gnome.gtk.Window;

import java.io.FileNotFoundException;
import java.text.ParseException;

public class GoalsPage extends AbstractPage {
    Button addGoalButton;
    Button cancel;
    Button exit;

    protected void initializeStructures() throws FileNotFoundException, ParseException {
        this.currentWindow = (Window) builder.getObject("goals_page");
        addGoalButton = (Button) builder.getObject("add_goal");
        cancel = (Button) builder.getObject("cancel_goals");
        exit = (Button) builder.getObject("exit_goals");
    }
    protected void connectButtons() {
        addGoalButton.connect(new Button.Clicked() {
            @Override
            public void onClicked(Button arg0) {
                AddGoalsPage addGoalsPage = new AddGoalsPage();
                addGoalsPage.show();
            }
        });
        cancel.connect(new Button.Clicked() {
            @Override
            public void onClicked(Button arg0) {
                currentWindow.destroy();
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
