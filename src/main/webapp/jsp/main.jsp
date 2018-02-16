<%@ page contentType = "text/html; charset = UTF-8" %>
<html>
   <head>
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
      <title>Hello World</title>
   </head>

   <body>
      <h1><b>${message}</b></h1>
      
      <h3 class="month-header">App to help you manage your money every day</h3>
      <p><table class="container">
      <thead>
          <!--   <tr>
          for (String column : heading) {
              <th>Button</th>
              <th>Button</th>
              <th>Button</th>
              <th>Button</th>
          }
          </tr>-->
      </thead>
                <tr>
                  <td><h2><a href="http://localhost:8080/money_manager-1.0-SNAPSHOT/new_data">New Data</a></h2></td>
                  <td><h2><a href="http://localhost:8080/money_manager-1.0-SNAPSHOT/new_expense">New expense</a></h2></td>
                  <td><h2><a href="http://localhost:8080/money_manager-1.0-SNAPSHOT/wishes">Wishes</h2></td>
                </tr>
                <tr>
                  <td><h2><a href="http://localhost:8080/money_manager-1.0-SNAPSHOT/entered_months">Entered months</h2></td>
                  <td><h2><a href="http://localhost:8080/money_manager-1.0-SNAPSHOT/monthly_reports">Monthly report</h2></td>
                  <td><h2><a href="http://localhost:8080/money_manager-1.0-SNAPSHOT/exit">Exit</h2></td>
                </tr>
      </table>
      </p>
   </body>
   <h2>Application by <b>Monika Gruszka & Michał Szymański</b></h2>
   <h2>Theme created with love by <a href=\"http://pablogarciafernandez.com\" target="_blank">Pablo García Fernández</a></h2>

</html>