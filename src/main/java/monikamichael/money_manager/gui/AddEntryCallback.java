package monikamichael.money_manager.gui;

import monikamichael.money_manager.engine.Entry;

public interface AddEntryCallback {
    void onDataAvailable(int year, int month, Entry entry);
}
