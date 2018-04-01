package monikamichael.money_manager.webapp;

import monikamichael.money_manager.engine.Database;
import monikamichael.money_manager.engine.Entry;
import monikamichael.money_manager.engine.UpcomingExpensesHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/upcoming_expenses")
public class UpcomingExpensesController {
    @Autowired
    DatabaseConnector databaseConnector;

    @RequestMapping(method = RequestMethod.GET)
    public String doGet(ModelMap model) {
        Database db = databaseConnector.connect();
        List<Entry> backlogEntries = UpcomingExpensesHandler.retrieveListOfEntries(
                db, "Backlog");
        List<Entry> marchEntries = UpcomingExpensesHandler.retrieveListOfEntries(
                db, "March");

        model.addAttribute("backlog_entries", backlogEntries);
        model.addAttribute("march_entries", marchEntries);

        return "upcoming_expenses";
    }
}
