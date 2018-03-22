package monikamichael.money_manager.engine;

import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ReportGenerator {
    private static void writeMonthData(final PrintWriter writer,
                                       final int year, final int month, final MonthData data) {
        writer.println("<h3 class=\"month-header\">" + Month.fromInt(month) + " " + year + "</h3>");
        writer.println("<p><table class=\"container\">");

        writeTableHeader(writer, new String[]{"account", " beginning", "end", "balance"});
        writeTableRow(writer, new String[] {
                "main",
                Currency.toString(data.accountBegin),
                Currency.toString(data.accountEnd),
                Currency.toString(data.accountEnd - data.accountBegin)
        });

        writeTableRow(writer, new String[] {
                "wallet",
                Currency.toString(data.walletBegin),
                Currency.toString(data.walletEnd),
                Currency.toString(data.walletEnd - data.walletBegin)
        });

        writeTableRow(writer, new String[] {
                "PayPal",
                Currency.toString(data.payPalBegin),
                Currency.toString(data.payPalEnd),
                Currency.toString(data.payPalEnd - data.payPalBegin)
        });

        writeTableRow(writer, new String[] {
                "TOTAL",
                Currency.toString(data.mainBegin()),
                Currency.toString(data.mainEnd()),
                Currency.toString(data.mainEnd() - data.mainBegin())
        });

        writer.println("</table></p>");

        writer.println("<p><table class=\"container\">");
        writeTableRow(writer, new String[] {
                "<b>Full salary in this month</b>", Currency.toString(data.salary)
        });
        writeTableRow(writer, new String[] {
                "<b>Budget income</b>", Currency.toString(data.budgetIncome)
        });
        writeTableRow(writer, new String[] {
                "<b>Saved part of salary</b>", Currency.toString(data.salaryToSavings())
        });
        writer.println("</table></p>");

        writer.println("<p><table class=\"container\">");
        writeTableRow(writer, new String[] {
                "<b>Budget income</b>", Currency.toString(data.budgetIncome)
        });
        writeTableRow(writer, new String[] {
                "<b>Budget expenses</b>", Currency.toString(data.budgetExpenses())
        });
        writeTableRow(writer, new String[] {
                "<b>Budget balance</b>", Currency.toString(data.budgetBalance())
        });
        writeExpandingList(writer, month, data.ownExpenses, "Own Expenses");
        writeExpandingList(writer, month, data.periodicExpenses, "Periodic Expenses");
        writeExpandingList(writer, month, data.otherExpenses, "Other Expenses");
        writeTableRow(writer, new String[] {
                "<b>Food expenses</b>", Currency.toString(data.foodExpenses())
        });
        writer.println("</table></p>");

        writer.println("<p><table class=\"container\">");
        writeTableRow(writer, new String[] {
                "<b>Saved salary</b>", Currency.toString(data.salaryToSavings())
        });
        writeExpandingList(writer, month, data.outOfBudgetExpenses, "Out-of-budget expenses");
        writeExpandingList(writer, month, data.transfersFromSavings, "Transfers from savings");
        writeTableRow(writer, new String[] {
                "<b>Savings balance</b>", Currency.toString(data.savingsBalance())
        });
        writer.println("</table></p>");
        writer.println("<p><table class=\"container\">");
        writeExpandingList(writer, month, data.debts, "Money lent + Borrowed money gave back");
        writer.println("</table></p>");
        
        writer.println("<p><table class=\"container\">");
        writeTableHeader(writer, new String[]{"", "Clothes", "Cosmetics",
                "Hobby and books", "Dates and meetings", "Other"});
        writeTableRow(writer, new String[] {
                "TOTAL",
                Currency.toString(MonthData.balanceCategory(data.getAllEntries(), "Clothes")),
                Currency.toString(MonthData.balanceCategory(data.getAllEntries(), "Cosmetics")),
                Currency.toString(MonthData.balanceCategory(data.getAllEntries(), "Hobby and books")),
                Currency.toString(MonthData.balanceCategory(data.getAllEntries(), "Dates and meetings")),
                Currency.toString(MonthData.balanceCategory(data.getAllEntries(), "Other")),

        });
        writer.println("</table></p>");
    }

    public static void forOneMonth(final Database db, final PrintWriter writer,
                                   final int month, final int year) {
        writer.println("<html>");
        writer.println("  <head>");
        writer.println("    <title>Financial report</title>");
        writer.println("    <link rel=\"stylesheet\" type=\"text/css\" href=\"src/main/resources/main.css\">");
        writer.println("  </head>");
        writer.println("  <body>");

        writer.println("    <h1>Financial report</h1>");
        writer.println("    <h4>");
        writer.println("      Report generated by Money Manager for one specified month.");
        writer.println("    </h4>");

        MonthData data = MonthHandler.retrieveForMonth(db, year, month);
        writeMonthData(writer, year, month, data);

        writeHelp(writer);

        writer.println("  </body>");
        writer.println("<h2>Application by <b>Monika Gruszka &amp; Michał Szymański</b></h2>");
        writer.println("<h2>Theme created with love by <a href=\"http://pablogarciafernandez.com\" target=\"_blank\">Pablo García Fernández</a></h2>");
        writer.println("</html>");
    }

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
        writer.println("    </h4>");
        db.executeSqlQuery("SELECT YEAR, MONTH FROM MONTHS ORDER BY YEAR DESC, MONTH DESC", new SqlQueryClient() {
            @Override
            public void onStatementReady(PreparedStatement statement) throws SQLException {
            }

            @Override
            public void onResult(ResultSet resultSet) throws SQLException {
                boolean first = true;
                while (resultSet.next()) {
                    int year = resultSet.getInt("YEAR");
                    int month = resultSet.getInt("MONTH");

                    if (first) {
                        // Also print information about the next month (first not included in the database)
                        int nextMonthYear = year;
                        int nextMonthMonth = month + 1;
                        if (nextMonthMonth > 12) {
                            nextMonthMonth = 1;
                            ++nextMonthYear;
                        }
                        MonthData data = MonthHandler.retrieveForMonth(db, nextMonthYear, nextMonthMonth);
                        writeMonthData(writer, nextMonthYear, nextMonthMonth, data);

                        first = false;
                    }

                    MonthData data = MonthHandler.retrieveForMonth(db, year, month);
                    writeMonthData(writer, year, month, data);
                }
            }
        });

        writeHelp(writer);

        writer.println("  </body>");
        writer.println("<h2>Application by <b>Monika Gruszka &amp; Michał Szymański</b></h2>");
        writer.println("<h2>Theme created with love by <a href=\"http://pablogarciafernandez.com\" target=\"_blank\">Pablo García Fernández</a></h2>");
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

    private static void writeTableRow(PrintWriter writer, String[] cells) {
        writer.println("  <tr>");
        for (String cell : cells) {
            writer.println("<td>" + cell + "</td>");
        }
        writer.println("  </tr>");
    }

    private static  void writeExpandingList(PrintWriter writer, int month, List<Entry> data, String uniqueId) {
        writer.println("<tr><p><td>" +
                "<input class=\"toggle\" id=\"toggle" + uniqueId + Month.fromInt(month)  +  "\" type=\"checkbox\">" +
                "<label for=\"toggle" + uniqueId + Month.fromInt(month) + "\"><b>"+uniqueId +"</b></label>" +
                "<div class=\"expand\">" +
                "    <section>" +
                "<p><table class=\"container\">");
        for (Entry entry : data) {
            writer.println("<form method=\"post\">");
            writer.println(
                    "<tr>" +
                    "<td> <input type=\"text\" name=\"entryId\" value=\"" + entry.description + "\"/></td>" +
                    "<td>" + Currency.toString(entry.value) + "</td>" +
                    "<td>" + entry.category + "</td>" +
                    "<td>" +
                    "<input onclick=\"return confirm('Do you want to delete this expense?');\"" +
                            " name=\"delete\" type=\"submit\" value=\"Delete\" style=\"width:100%\"/>" +
                            "</td>" +
                    "</tr>");
            writer.println("</form>");
        }
        writer.println("</form>");
        writer.println("</table></p> </section></div></td><td>");
        writer.println(Currency.toString(MonthData.balanceEntries(data)));
        writer.println("</td></tr></p>");
    }

    private static void writeHelp(PrintWriter writer) {
        writer.println("<div id=\"help\">");
        writer.println("<h4>Help</h4>" +
                "<img src=\"accounts.png\" style=\"width: 14cm; display: block; margin: 0 auto;\"/>" +
                "<p>Every month, you enter <b>salary</b> you earned that month and amount that " +
                "you wish to be your <b>in-budget income</b>. The rest of your salary is " +
                "<i>out-of-budget</i> income (don't enter it in out-of-budget events yourself).</p>" +
                "<p>You can also have other out-of-budget events, labeled <i>gifts</i> " +
                "and <i>out-of-budget expenses</i>. Put them into <b>out-of-budget events</b> " +
                "table. Gifts go with a positive sign into that table and expenses with negative sign.</p>" +
                "<p>If you transferred some money from savings account into the main account, " +
                "report it in <b>transfers from savings</b>. But also return the money " +
                "as soon as possible, trying to keep transfers zero. Report returned money " +
                "in the same table with negative sign.</p>" +
                "<p>If the money transferred from savings was spent on an out-of-budget expense, " +
                "report <u>only</u> the out-of-budget expense and <u>don't</u> report the transfer.</p>" +
                "<p>If you lend some money, enter it in <b>money lent</b> table. " +
                "The rules for signs are as follows:" +
                "<ul>" +
                "<li>+ when you lend money to someone</li>" +
                "<li>- when that someone gives the money back</li>" +
                "<li>- when you borrow money from someone</li>" +
                "<li>+ when you give the money back to them</li>" +
                "</ul></p>" +
                "<p><b>In-budget expense</b> can be reported in either of the following categories:" +
                "<ol>" +
                "  <li>own expenses</li>" +
                "  <li>periodic expenses</li>" +
                "  <li>other expenses</li>" +
                "</ol>" +
                "You are free to choose whichever category, but periodic expenses have an additional feature: " +
                "they can be copied from the previous month when entering a new month data." +
                "</p>" +
                "<p>You don't enter <i>food expenses</i> manually. Instead, it is calculated automatically " +
                "based on Main Account balance and the rest of the data entered.</p>" +
                "<p>If you wish to pay for some in-budget expense <b>in instalments</b>, here's an example " +
                "of how to do that: suppose you wish to pay for 300 zł expense in three instalments, " +
                "from January to March." +
                "<ol>" +
                "  <li>in January, you actually pay, so you enter 300 zł in <i>expenses</i>. But to have money " +
                "you also take 200 zł from savings and report that in <i>transfers from savings</i></li>" +
                "  <li>in February, you don't report anything in <i>expenses</i>, but transfer the borrowed " +
                "money back to savings account (100 zł) and report that in <i>transfers from savings</i> " +
                "with negative sign</li>" +
                "  <li>March is the same as February</li>" +
                "</ol>" +
                "You need to keep the record of borrows from future months. Sum of transfers from savings " +
                "in the past months should be kept as close to zero as possible.</p>");
        writer.println("</div>");
    }
}
