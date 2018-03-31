package monikamichael.money_manager.webapp;

import monikamichael.money_manager.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Calendar;

@Controller
@RequestMapping("/monthly_reports")
public class MonthlyReportsController {
    @Autowired
    DatabaseConnector databaseConnector;
    private int year, month;

    @RequestMapping(method = RequestMethod.GET)
    public String doGet(ModelMap model) {
        model.addAttribute("current_year",
                java.util.Calendar.getInstance().get(Calendar.YEAR));

        return "monthly_reports";
    }

    @RequestMapping(params ="one_or_all", method = RequestMethod.POST)
    public String doPost(@RequestParam("year") int year,
                         @RequestParam("month") int month,
                         @RequestParam("one_or_all") String one_or_all,
                         ModelMap model) {

        this.year = year;
        this.month = month;

        String report = getReportCode(year, month, one_or_all);
        model.addAttribute("report_code", report);
        return "report";
    }

    private String getReportCode(int year, int month, String reportType) {

         Database db = databaseConnector.connect();
         StringWriter out = new StringWriter();
         PrintWriter writer = new PrintWriter(out);

         if (reportType.equals("One month report")) {
            ReportGenerator.forOneMonth(db, writer, month, year);
         } else {
            ReportGenerator.forAllMonths(db, writer);
         }

         writer.close();
         String result = out.toString();
         return result;
    }

    @RequestMapping(params="delete", method = RequestMethod.POST)
    public String doDelete(@RequestParam("entryId") String entryId,
                           @RequestParam("expenseType") String expenseType,
                           @RequestParam("one_month_or_all") String one_month_or_all,
                           ModelMap model) {

        Database db = databaseConnector.connect();
        EntryHandler.deleteFromTable(db, TableName.toTableName(expenseType), year, month, entryId);
        String report = getReportCode(year, month, one_month_or_all);
        model.addAttribute("report_code", report);

        return "report";
    }

}
