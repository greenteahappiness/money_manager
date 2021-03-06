package monikamichael.money_manager.webapp;

import monikamichael.money_manager.engine.*;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/graph_data")
public class GraphJsonController {
    protected Logger logger = LoggerFactory.getLogger(GraphsController.class);

    @Autowired
    DatabaseConnector databaseConnector;

    @RequestMapping(method = RequestMethod.GET)
    public String doGet(ModelMap map,
                        @RequestParam(value="year", required=false) int year,
                        @RequestParam(value="month", required=false) int month,
                        @RequestParam(value="expense_type", required=false) String expense_type) {
        Map<String, String> expenses = getInfoAboutPastExpenses(year, month, expense_type);
        JSONObject jsonExpenses = new JSONObject(expenses);
        map.addAttribute("json", jsonExpenses);

        return "graph_data";
    }

    public Map<String, String> getInfoAboutPastExpenses(int year, int month, String expense_type) {
        if (expense_type == null) {
            expense_type = "OWN_EXPENSES";
        }
        Database db = databaseConnector.connect();
        List<Entry> entryList;
        MonthData md = MonthHandler.retrieveForMonth(db, year, month);
        Map<String, String> expenses = new HashMap<>();

        if (expense_type.contains("all")) {
            entryList = MonthHandler.retrieveListOfEntriesFromAllExpenses(db, year, month);
        } else {
            entryList = MonthHandler.retrieveListOfEntries(db, year, month, ExpenseType.toTableName(expense_type));
        }

        int clothesExpenses = MonthData.balanceCategory(entryList, "clothes");
        int cosmeticsExpenses = MonthData.balanceCategory(entryList, "cosmetics");
        int booksExpenses = MonthData.balanceCategory(entryList, "hobby_books");
        int meetingsExpenses = MonthData.balanceCategory(entryList, "meetings");
        int otherExpenses = MonthData.balanceCategory(entryList, "other");


        expenses.put("clothes", Currency.toStringWithoutCurrency(clothesExpenses));
        expenses.put("cosmetics", Currency.toStringWithoutCurrency(cosmeticsExpenses));
        expenses.put("books", Currency.toStringWithoutCurrency(booksExpenses));
        expenses.put("meetings",  Currency.toStringWithoutCurrency(meetingsExpenses));
        expenses.put("other",  Currency.toStringWithoutCurrency(otherExpenses));

        if (expense_type.equals("all-and-food")) {
            expenses.put("food", Currency.toStringWithoutCurrency((-1)*md.foodExpenses()));
        }

        return expenses;
    }
}