package monikamichael.money_manager.webapp;

import monikamichael.money_manager.engine.Database;
import monikamichael.money_manager.engine.Month;
import monikamichael.money_manager.engine.MonthHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Calendar;
import java.util.List;

@Controller
@RequestMapping("/entered_months")
public class EnteredMonthsController {

    @Autowired
    private DatabaseConnector databaseConnector;
    private Database db;

    @RequestMapping(method = RequestMethod.GET)
    public String doGet(ModelMap model) {
        db = databaseConnector.connect();

        model.addAttribute("current_year",
                java.util.Calendar.getInstance().get(Calendar.YEAR));

        return "entered_months";
    }

    @RequestMapping(params = "confirm_year", method = RequestMethod.POST)
    public String confirmYear(@RequestParam("year") int year,
                              ModelMap map) {
        List<String> openMonths = MonthHandler.retrieveOpenMonthsFromDatabase(db, year);
        map.addAttribute("open_months_list", openMonths);

        List<String> closedMonth = MonthHandler.retrieveClosedMonthsFromDatabase(db, year);
        map.addAttribute("closed_months_list", closedMonth);
        updateCurrentYear(map);

        return "entered_months";
    }

    @RequestMapping(params = "close_month", method = RequestMethod.POST)
    public String closeMonth(@RequestParam("year") int year,
                             @RequestParam("open_month") String month,
                             ModelMap map) {
        MonthHandler.closeMonth(db, year, Month.toInt(month));
        updateCurrentYear(map);

        return "entered_months";
    }

    @RequestMapping(params = "reopen_month", method = RequestMethod.POST)
    public String reopenMonth(@RequestParam("year") int year,
                              @RequestParam("closed_month") String month,
                              ModelMap map) {
        MonthHandler.reopenMonth(db, year, Month.toInt(month));
        updateCurrentYear(map);

        return "entered_months";
    }

    private void updateCurrentYear(ModelMap map) {
        map.addAttribute("current_year",
                java.util.Calendar.getInstance().get(Calendar.YEAR));
    }

}
