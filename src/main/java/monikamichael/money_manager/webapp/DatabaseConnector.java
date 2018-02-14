package monikamichael.money_manager.webapp;

import monikamichael.money_manager.engine.Database;
import org.springframework.stereotype.Component;

@Component
public class DatabaseConnector {

    public Database connect() {
        Database db = new Database("sqlite/accounts.sqlite");
        if (db.connect()) {
            db.createTables();
        }
        return db;
    }
}
