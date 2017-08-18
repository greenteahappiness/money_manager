package monikamichael.money_manager.gui;

import org.gnome.gtk.*;

import java.io.FileNotFoundException;
import java.text.ParseException;

public class Main {

    Builder builder;
    Window homepage;
    Button exit;

    public static void main(String[] args) {
        Main main = new Main();
        Gtk.init(args);
        try {
            main.initializeStructures();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        main.connectButtons();
        main.homepage.showAll();
        Gtk.main();
    }
    private void initializeStructures() throws FileNotFoundException, ParseException {
        builder = new Builder();
        builder.addFromFile("src/main/resources/gui.glade");

        homepage = (Window) builder.getObject("window1");
        exit = (Button) builder.getObject("exit");
    }
    private void connectButtons() {

        exit.connect(new Button.Clicked() {

            @Override
            public void onClicked(Button arg0) {
                System.exit(0);
            }
        });
    }
}
