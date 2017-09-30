package monikamichael.money_manager.gui;

import org.gnome.gtk.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    public static void main(String[] args) {

        Logger logger = LoggerFactory.getLogger(Main.class);

        Gtk.init(args);
        logger.info("MoneyManager initialized");

        HomePage homePage = new HomePage();
        homePage.show();

        Gtk.main();
    }
}
