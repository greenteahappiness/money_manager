package monikamichael.money_manager.webapp;

import monikamichael.money_manager.engine.Currency;
import monikamichael.money_manager.engine.Database;
import monikamichael.money_manager.engine.MonthData;
import monikamichael.money_manager.engine.MonthHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


import java.util.Calendar;
import java.util.Map;

@Controller
@RequestMapping("/new_data")
public class NewDataController {

    private Map<String, String> allRequestParams;
    //year=2018&portfel_koniec=0&konto_koniec=0&paypal_koniec=0&nadwyzka=0&pensja=100
    @Autowired
    DatabaseConnector databaseConnector;

    @RequestMapping(method = RequestMethod.GET)
    public String doGet(ModelMap model) {
        model.addAttribute("current_year",
                java.util.Calendar.getInstance().get(Calendar.YEAR));
        return "new_data";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String doPost(@RequestParam("year") String year,
                         @RequestParam("month") String month,
                         @RequestParam("portfel_koniec") String walletEnd,
                         @RequestParam("konto_koniec") String accountEnd,
                         @RequestParam("paypal_koniec") String paypalEnd,
                         @RequestParam("nadwyzka") String afterPreviousMonth,
                         @RequestParam("pensja") String salary,
                         ModelMap model) {
        Database db = databaseConnector.connect();
        try {
            MonthHandler.insertToDatabase(db,
                    new MonthData(
                            Currency.parseString(walletEnd),
                            Currency.parseString(accountEnd),
                            Currency.parseString(paypalEnd),
                            Currency.parseString(afterPreviousMonth),
                            Currency.parseString(salary)),
                    Integer.parseInt(year),
                    Integer.parseInt(month));
            model.addAttribute("msg_successful", "Month data successfully added!");
        } catch (Exception e) {
            model.addAttribute("msg", "Incorrect input!");
        }
        return "new_data";
    }
}
