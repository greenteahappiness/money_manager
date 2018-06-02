package monikamichael.money_manager.webapp;

import monikamichael.money_manager.engine.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/graphs")
public class GraphsController {
    protected Logger logger = LoggerFactory.getLogger(GraphsController.class);

    @Autowired
    DatabaseConnector databaseConnector;

    @RequestMapping(method = RequestMethod.GET)
    public String doGet(ModelMap map) {
        String[] data = getInfoAboutPastExpenses();
        map.addAttribute("graph_title", "\'Money spent on given categories\'");
        map.addAttribute("graph_data", Arrays.toString(data));
        map.addAttribute("graph_labels",
                "[\"Clothes\", \"Cosmetics\", \"Hobby and books\", \"Other\"]");

        return "graphs";
    }

    public String[] getInfoAboutPastExpenses() {
        Database db = databaseConnector.connect();
        List<Entry> entryList = MonthHandler.retrieveListOfEntries(db, 2018, 5, "OWN_EXPENSES");
        int clothesExpenses = MonthData.balanceCategory(entryList, "clothes");
        int cosmeticsExpenses = MonthData.balanceCategory(entryList, "cosmetics");
        int booksExpenses = MonthData.balanceCategory(entryList, "hobby_books");
        int otherExpenses = MonthData.balanceCategory(entryList, "other");

        String[] graphData = {Currency.toStringWithoutCurrency(clothesExpenses),
                Currency.toStringWithoutCurrency(cosmeticsExpenses),
                Currency.toStringWithoutCurrency(booksExpenses),
                Currency.toStringWithoutCurrency(otherExpenses)
        };

        return graphData;
    }
}