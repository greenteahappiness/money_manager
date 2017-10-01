package monikamichael.money_manager.gui;

import monikamichael.money_manager.engine.MonthData;

public interface EnterMonthDataCallback {
    void onDataAvailable(MonthData data);
}
