package monikamichael.money_manager.gui;

import monikamichael.money_manager.engine.Database;
import monikamichael.money_manager.engine.Goal;
import monikamichael.money_manager.engine.GoalsHandler;
import org.gnome.gtk.Button;
import org.gnome.gtk.Label;
import org.gnome.gtk.Window;

public class ShowGoalsPage extends AbstractPage {

    private Button nextGoal;
    private Button previous;
    private Button previousGoal;

    private Label goalsNumberLabel;
    private Label alreadySavedLabel;
    private Label price;
    private Label name;

    private int numberOfGoals;
    private int currentGoalNum = 0;

    private GoalsHandler goalsHandler = new GoalsHandler();
    private Database db;

    public ShowGoalsPage(Database db) {
        this.db = db;
        goalsHandler.loadListOfGoals(db);
        numberOfGoals = goalsHandler.getNumberOfGoals();
        goalsNumberLabel.setLabel("Goals number: " + numberOfGoals);
    }

    protected void connectButtons() {
        nextGoal.connect(new Button.Clicked() {
            @Override
            public void onClicked(Button arg0) {
                if (isNextGoal()) {
                    currentGoalNum += 1;
                    Goal nextGoal = goalsHandler.getNthGoalFromDatabase(currentGoalNum);
                    setGoalLabels(nextGoal);
                }
            }
        });
        previousGoal.connect(new Button.Clicked() {
            @Override
            public void onClicked(Button arg0) {
                if (isPreviousGoal()) {
                    currentGoalNum -= 1;
                    Goal nextGoal = goalsHandler.getNthGoalFromDatabase(currentGoalNum);
                    setGoalLabels(nextGoal);
                }
            }
        });

        previous.connect(new Button.Clicked() {
            @Override
            public void onClicked(Button arg0) {
                currentWindow.destroy();
            }
        });

    }
    private void setGoalLabels(Goal goal) {
        int price = goal.getPrice();
        float alreadySaved = goal.getAlreadySavedMoney();
        String name = goal.getName();

        this.price.setLabel("Price: " + String.valueOf(price));
        this.alreadySavedLabel.setLabel("Already saved: " + String.valueOf(alreadySaved));
        this.name.setLabel("Name: " + name);
    }

    private boolean isNextGoal() {
        return currentGoalNum + 1 <= numberOfGoals;
    }
    private boolean isPreviousGoal() {
        return currentGoalNum - 1 > 0;
    }

    protected void initializeStructures() {
        this.currentWindow = (Window) builder.getObject("show_goals_page");
        nextGoal = (Button) builder.getObject("next_goal");
        previous = (Button) builder.getObject("show_goals_back");
        previousGoal = (Button) builder.getObject("previous_goal");
        goalsNumberLabel = (Label) builder.getObject("goals_number_label");
        price = (Label) builder.getObject("goal_price");
        alreadySavedLabel= (Label) builder.getObject("goal_already_saved");
        name = (Label) builder.getObject("goal_name");

    }
}