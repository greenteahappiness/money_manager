package monikamichael.money_manager.webapp;

import monikamichael.money_manager.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Calendar;


@Controller
@RequestMapping("/new_expense")
public class NewExpenseController {
    @Autowired
    DatabaseConnector databaseConnector;

    @RequestMapping(method = RequestMethod.GET)
    public String doGet(ModelMap model) {
        model.addAttribute("current_year",
                java.util.Calendar.getInstance().get(Calendar.YEAR));

        return "new_expense";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String doPost(@RequestParam("year") String year,
                         @RequestParam("month") String month,
                         @RequestParam("description") String description,
                         @RequestParam("value") String value,
                         @RequestParam("expense_type") String expense_type,
                         @RequestParam("category") String category,
                         ModelMap model) {

        Database db = databaseConnector.connect();
        try {
            Entry entry = EntryBuilder.entry()
                    .withDescription(description)
                    .withCategory(category)
                    .withEntryType(expense_type)
                    .withValue(Currency.parseString(value))
                    .build();

            EntryHandler.insertToTable(
                    db, entry.entryType.toUpperCase(), Integer.parseInt(year), Integer.parseInt(month), entry);
            model.addAttribute("msg_successful", "Expense successfully added!");
        } catch (Exception e) {
            model.addAttribute("msg", "Incorrect input!");
        }
        return "new_expense";
    }

}
