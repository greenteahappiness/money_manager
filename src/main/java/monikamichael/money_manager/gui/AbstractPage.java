package monikamichael.money_manager.gui;

import org.gnome.gtk.Builder;
import org.gnome.gtk.Window;

import java.io.FileNotFoundException;
import java.text.ParseException;

public abstract class AbstractPage {
    protected Window currentWindow;
    protected Builder builder;

    public AbstractPage() {
        try {
            builder = new Builder();
            builder.addFromFile("src/main/resources/gui.glade");
            initializeStructures();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        connectButtons();
    }
    public void show() {
        currentWindow.showAll();
    }
    public void close() {
        currentWindow.destroy();
    }

    protected abstract void initializeStructures() throws FileNotFoundException, ParseException;
    protected abstract void connectButtons();
}
