package monikamichael.money_manager.webapp;

import monikamichael.money_manager.engine.Database;
import monikamichael.money_manager.engine.MonthData;
import monikamichael.money_manager.engine.MonthHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class RecordEnterMonthData {
    protected Logger logger = LoggerFactory.getLogger(RecordEnterMonthData.class);

    private Database db;

    public void connect() {
        db = new Database(//"/money_manager-1.0-SNAPSHOT/" +
                "sqlite/accounts.sqlite");
        if (db.connect()) {
            db.createTables();
        }
    }

    public void save(MonthData monthData, int year, int month) {
        MonthHandler.insertToDatabase(db, monthData, year, month);
        logger.info("Month data for " + year + "saved.");
    }

}
