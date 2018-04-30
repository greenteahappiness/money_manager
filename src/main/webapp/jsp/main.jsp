<%@ page contentType = "text/html; charset = UTF-8" %>
<html>
   <head>
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/homepage.css">
      <link id="theme" rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
      <title>Money Manager Home</title>
   </head>

   <body>
   <ul class="nav">
     <li class="nav-item">
       <a class="nav-link active" href="#">Active</a>
     </li>
   </ul>
      <section class="jumbotron text-center">
      <h1 class="jumbotron-heading"><b>${message}</b></h1>
      <h3 class="month-header">App to help you manage your money every day</h3>
      <select class="form-control" onchange="changeTheme(value);">
            <option value="default-dark">Default</option>
            <option value="pastel-pink">Pastel pink</option>
     </select>
     </section>
      <p><table class="container">
            <tr>
              <td><h2><a href="http://localhost:8080/money_manager-1.0-SNAPSHOT/new_data">New Data</a></h2></td>
              <td><h2><a href="http://localhost:8080/money_manager-1.0-SNAPSHOT/new_expense">New expense</a></h2></td>
              <td><h2><a href="http://localhost:8080/money_manager-1.0-SNAPSHOT/upcoming_expenses">Upcoming expenses</h2></td>
            </tr>
            <tr>
              <td><h2><a href="http://localhost:8080/money_manager-1.0-SNAPSHOT/entered_months">Entered months</h2></td>
              <td><h2><a href="http://localhost:8080/money_manager-1.0-SNAPSHOT/monthly_reports">Monthly report</h2></td>
              <td><h2><a href="http://localhost:8080/money_manager-1.0-SNAPSHOT/exit">Exit</h2></td>
            </tr>
      </table>
      </p>
   </body>

   <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
   <script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
   <script src="${pageContext.request.contextPath}/js/main.js"></script>

   <h2>Application by <b>Monika Gruszka & Michał Szymański</b></h2>
   <h2>Theme created with love by <a href=\"http://pablogarciafernandez.com\" target="_blank">Pablo García Fernández</a></h2>

</html>