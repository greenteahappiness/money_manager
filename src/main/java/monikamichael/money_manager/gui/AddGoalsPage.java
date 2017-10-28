package monikamichael.money_manager.gui;

import monikamichael.money_manager.engine.Database;
import monikamichael.money_manager.engine.Goal;
import monikamichael.money_manager.engine.GoalsHandler;
import org.gnome.gtk.*;

import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddGoalsPage extends AbstractPage {

    private Button add;
    private Button preview;
    private Button cancel;

    private Entry nameEntry;
    private Entry priceEntry;
    private Entry dateEntry;

    private String enteredName;
    private String enteredPrice;
    private String enteredDate;

    private FileChooserButton chooseImagePanel;
    private Image goalImage;

    private int price;
    private Date date;

    private GoalsHandler goalsHandler = new GoalsHandler();
    private Database db;

    public AddGoalsPage(Database db) {
        this.db = db;
    }

    protected void initializeStructures() throws FileNotFoundException, ParseException {
        this.currentWindow = (Window) builder.getObject("add_goal_page");

        add = (Button) builder.getObject("add_goal_button");
        preview = (Button) builder.getObject("preview_image");
        cancel = (Button) builder.getObject("cancel_button");

        nameEntry = (Entry) builder.getObject("name_entry");
        priceEntry = (Entry) builder.getObject("price_entry");
        dateEntry = (Entry) builder.getObject("due_date");
        goalImage = (Image) builder.getObject("goal_image");
        chooseImagePanel = (FileChooserButton) builder.getObject("image_chooser");

    }
    protected void connectButtons() {
        add.connect(new Button.Clicked() {
            @Override
            public void onClicked(Button arg0) {
                retrieveEntryParameters();
                convertParameters();
                Goal goal  = createGoal();
                goalsHandler.insertGoalToDatabase(db, goal);
            }
        });
        preview.connect(new Button.Clicked() {
            @Override
            public void onClicked(Button arg0) {
               String filename = chooseImagePanel.getFilename();
               goalImage.setImage(filename);
            }
        });

        cancel.connect(new Button.Clicked() {
            @Override
            public void onClicked(Button arg0) {
                currentWindow.destroy();
            }
        });
    }
    private Goal createGoal() {
        Goal goal = new Goal();

        goal.setName(enteredName);
        goal.setPrice(price);
        goal.setDueDate(new java.sql.Date(date.getTime()));

        return goal;
    }
    private void retrieveEntryParameters() {
        enteredName = nameEntry.getText();
        enteredPrice = priceEntry.getText();
        enteredDate = dateEntry.getText();
    }
    private void convertParameters() {
        try {
            price = Integer.parseInt(enteredPrice);
            DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
            date = format.parse(enteredDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
