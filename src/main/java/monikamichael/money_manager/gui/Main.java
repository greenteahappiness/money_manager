package monikamichael.money_manager.gui;

import org.gnome.gtk.*;

import java.io.FileNotFoundException;
import java.text.ParseException;

public class Main {

    public static void main(String[] args) {
        Gtk.init(args);
        HomePage homePage = new HomePage();
        homePage.show();
        Gtk.main();
    }
}
