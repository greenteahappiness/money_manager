package monikamichael.money_manager.gui;

import monikamichael.money_manager.engine.Database;
import monikamichael.money_manager.engine.GoalsHandler;
import org.gnome.gtk.Button;
import org.gnome.gtk.Window;

import java.io.FileNotFoundException;
import java.text.ParseException;

public class GoalsPage extends AbstractPage {
    Button addGoalButton;
    Button showGoals;
    Button cancel;

    GoalsHandler goalsHandler = new GoalsHandler();
    Database db;

    public GoalsPage(Database db) {
        this.db = db;
    }

    protected void initializeStructures() throws FileNotFoundException, ParseException {
        this.currentWindow = (Window) builder.getObject("goals_page");
        addGoalButton = (Button) builder.getObject("add_goal");
        showGoals = (Button) builder.getObject("show_goals");
        cancel = (Button) builder.getObject("cancel_goals");
    }

    protected void connectButtons() {
        addGoalButton.connect(new Button.Clicked() {
            @Override
            public void onClicked(Button arg0) {
                AddGoalsPage addGoalsPage = new AddGoalsPage(db);
                addGoalsPage.show();
            }
        });
        showGoals.connect(new Button.Clicked() {
            @Override
            public void onClicked(Button arg0) {
                ShowGoalsPage showGoalsPage = new ShowGoalsPage(db);
                showGoalsPage.show();
            }
        });
        cancel.connect(new Button.Clicked() {
            @Override
            public void onClicked(Button arg0) {
                currentWindow.destroy();
            }
        });
    }
}
