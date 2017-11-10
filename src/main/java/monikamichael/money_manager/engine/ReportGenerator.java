package monikamichael.money_manager.engine;

import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportGenerator {
    public static void forAllMonths(final Database db, final PrintWriter writer) {
        writer.println("<html>");
        writer.println("  <head>");
        writer.println("    <title>Raport finansowy</title>");
        writer.println("  </head>");
        writer.println("  <body>");
        writer.println("    <h1>Raport finansowy</h1>");
        writer.println("    <p>");
        writer.println("      Raport wygenerowany przez program Money Manager dla wszystkich");
        writer.println("      miesięcy w bazie danych.");
        writer.println("    </p>");
        db.executeSqlQuery("SELECT YEAR, MONTH FROM MONTHS", new SqlQueryClient() {
            @Override
            public void onStatementReady(PreparedStatement statement) throws SQLException {
            }

            @Override
            public void onResult(ResultSet resultSet) throws SQLException {
                while (resultSet.next()) {
                    int year = resultSet.getInt("YEAR");
                    int month = resultSet.getInt("MONTH");
                    MonthData data = MonthData.retrieveForMonth(db, year, month);
                    writer.println("<p>");
                    writer.println(Month.fromInt(month));
                    writer.println(year);
                    writer.println("<pre>");
                    writer.println(data.toString());
                    writer.println("</pre>");
                    writer.println("</p>");
                }
            }
        });
        writer.println("  </body>");
        writer.println("</html>");
    }
}
