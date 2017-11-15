package monikamichael.money_manager.engine;

import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportGenerator {
    public static void forAllMonths(final Database db, final PrintWriter writer) {
        writer.println("<html>");
        writer.println("  <head>");
        writer.println("    <title>Financial report</title>");
        writer.println("    <link rel=\"stylesheet\" type=\"text/css\" href=\"src/main/resources/main.css\">");
        writer.println("  </head>");
        writer.println("  <body>");
        writer.println("    <h1>Financial report</h1>");
        writer.println("    <h4>");
        writer.println("      Report generated by Money Manager for every month");
        writer.println("      included in the database.");
        writer.println("    <h4>");
        db.executeSqlQuery("SELECT YEAR, MONTH FROM MONTHS ORDER BY YEAR DESC, MONTH DESC", new SqlQueryClient() {
            @Override
            public void onStatementReady(PreparedStatement statement) throws SQLException {
            }

            @Override
            public void onResult(ResultSet resultSet) throws SQLException {
                while (resultSet.next()) {
                    int year = resultSet.getInt("YEAR");
                    int month = resultSet.getInt("MONTH");
                    MonthData data = MonthData.retrieveForMonth(db, year, month);

                    writer.println("<h3 class=\"month-header\">" + Month.fromInt(month) + " " + year + "</h3>");

                    writer.println("<p><table class=\"container\">");
                    //writeTableHeader(writer, new String[]{"account", " beginning", "end", "balance"});
                    writer.println("<thead>");
                    writer.println("  <tr>");
                    writer.println("    <th>account</th>");
                    writer.println("    <th>beginning</th>");
                    writer.println("    <th>end</th>");
                    writer.println("    <th>balance</th>");
                    writer.println("  </tr>");
                    writer.println("<thead>");

                    writer.println("  <tr>");
                    writer.println("    <td>main</th>");
                    writer.println("    <td>" + Currency.toString(data.accountBegin) + "</td>");
                    writer.println("    <td>" + Currency.toString(data.accountEnd) + "</td>");
                    writer.println("    <td>" + Currency.toString(data.accountEnd - data.accountBegin) + "</td>");
                    writer.println("  </tr>");

                    writer.println("  <tr>");
                    writer.println("    <td>wallet</th>");
                    writer.println("    <td>" + Currency.toString(data.walletBegin) + "</td>");
                    writer.println("    <td>" + Currency.toString(data.walletEnd) + "</td>");
                    writer.println("    <td>" + Currency.toString(data.walletEnd - data.walletBegin) + "</td>");
                    writer.println("  </tr>");

                    writer.println("  <tr>");
                    writer.println("    <td>PayPal</th>");
                    writer.println("    <td>" + Currency.toString(data.payPalBegin) + "</td>");
                    writer.println("    <td>" + Currency.toString(data.payPalEnd) + "</td>");
                    writer.println("    <td>" + Currency.toString(data.payPalEnd - data.payPalBegin) + "</td>");
                    writer.println("  </tr>");

                    writer.println("  <tr>");
                    writer.println("    <td>TOTAL</th>");
                    writer.println("    <td>" + Currency.toString(data.totalBegin()) + "</td>");
                    writer.println("    <td>" + Currency.toString(data.totalEnd()) + "</td>");
                    writer.println("    <td>" + Currency.toString(data.totalEnd() - data.totalBegin()) + "</td>");
                    writer.println("  </tr>");

                    writer.println("</table></p>");

                    writer.println("<p><table class=\"container\">");
                    writer.println("<tr>");
                    writer.println("<p><td><b>After previous month</b></td><td>" + Currency.toString(data.afterPreviousMonth) + "</td><br>");
                    writer.println("</td></tr>");
                    writer.println("<tr>");
                    writer.println("<td><b>Salary</b></td><td>" + Currency.toString(data.salary) + "</td></p>");
                    writer.println("<tr>");
                    writer.println("<p><td><b>Own expenses</b></td><ul class=\"entries-list\">");
                    for (Entry entry : data.ownExpenses)
                        writer.println("<td><li>" + Currency.toString(entry.value) + ": " + entry.description + "</li></td>");
                    writer.println("</ul></p>");
                    writer.println("</tr>");

                    writer.println("<tr>");
                    writer.println("<p><td><b>Periodic expenses</b></td><ul class=\"entries-list\">");
                    for (Entry entry : data.periodicExpenses)
                        writer.println("<td><li>" + Currency.toString(entry.value) + ": " + entry.description + "</td></li>");
                    writer.println("</ul></p>");
                    writer.println("</tr>");

                    writer.println("<tr>");
                    writer.println("<p><td><b>Other expenses</b></td</b><ul class=\"entries-list\">");
                    for (Entry entry : data.otherExpenses)
                        writer.println("<td><li>" + Currency.toString(entry.value) + ": " + entry.description + "</td></li>");
                    writer.println("</ul></p>");
                    writer.println("</tr>");

                    writer.println("<tr>");
                    writer.println("<p><td><b>Out-of-budget expenses</b></td><ul class=\"entries-list\">");
                    for (Entry entry : data.outOfBudgetExpenses)
                        writer.println("<td><li>" + Currency.toString(entry.value) + ": " + entry.description + "</td></li>");
                    writer.println("</ul></p>");
                    writer.println("</tr>");

                    writer.println("<tr>");
                    writer.println("<p><td><b>Debts</b></td><ul class=\"entries-list\">");
                    for (Entry entry : data.debts)
                        writer.println("<td><li>" + Currency.toString(entry.value) + ": " + entry.description + "</td></li>");
                    writer.println("</ul></p>");
                    writer.println("</tr>");

                    writer.println("<tr>");
                    writer.println("<p><td><b>Transfers from savings</b></td><ul class=\"entries-list\">");
                    for (Entry entry : data.transfersFromSavings)
                        writer.println("<td><li>" + Currency.toString(entry.value) + ": " + entry.description + "</td></li>");
                    writer.println("</ul></p>");
                    writer.println("</tr>");
                    writer.println("</table></p>");
                }
            }
        });
        writer.println("  </body>");
        writer.println("<h2>Created with love by <a href=\"http://pablogarciafernandez.com\" target=\"_blank\">Pablo García Fernández</a></h2>");
        writer.println("</html>");
    }

    private static void writeTableHeader(PrintWriter writer, String[] heading) {
        writer.println("<thead>");
        writer.println("  <tr>");
        for (String column : heading) {
            writer.println("<th>" + column + "</th>");
        }
        writer.println("  </tr>");
        writer.println("<thead>");
    }


}
