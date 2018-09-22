package monikamichael.money_manager.webapp;

import monikamichael.money_manager.engine.Card;
import monikamichael.money_manager.engine.Database;
import monikamichael.money_manager.engine.Entry;
import monikamichael.money_manager.engine.UpcomingExpensesHandler;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/upcoming_expenses")
public class UpcomingExpensesController {
    protected Logger logger = LoggerFactory.getLogger(UpcomingExpensesController.class);

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


    @RequestMapping(method = RequestMethod.POST,
            consumes = "application/json",
            value="/update_cards")
    public @ResponseBody String updateCards(@RequestBody Card card, HttpServletRequest request) {
        Database db = databaseConnector.connect();
        UpcomingExpensesHandler.addCardPositionToDatabase(db, card);
        return "upcoming_expenses";
    }
}