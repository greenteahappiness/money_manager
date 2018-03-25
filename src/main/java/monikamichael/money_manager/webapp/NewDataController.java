package monikamichael.money_manager.webapp;

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
    public String doPost(@RequestParam("year") int year,
                         @RequestParam("month") int month,
                         @RequestParam("portfel_koniec") int walletEnd,
                         @RequestParam("konto_koniec") int accountEnd,
                         @RequestParam("paypal_koniec") int paypalEnd,
                         @RequestParam("nadwyzka") int afterPreviousMonth,
                         @RequestParam("pensja") int salary) {
        Database db = databaseConnector.connect();
        MonthHandler.insertToDatabase(db,
                new MonthData(walletEnd, accountEnd, paypalEnd, afterPreviousMonth, salary),
                year,
                month);

        return "new_data";
    }


}
