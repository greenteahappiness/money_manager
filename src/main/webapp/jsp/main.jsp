<%@ page contentType = "text/html; charset = UTF-8" %>
<html>
   <head>
      <link href="https://fonts.googleapis.com/css?family=Dosis" rel="stylesheet">
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
      <p>
      <div class="col-lg-12 text-center">
          <div class="row">
              <div class="col-sm-4">
                  <a href="http://localhost:8080/money_manager-1.0-SNAPSHOT/new_data" class="btn btn-primary btn-block" role="button">New Data</a>
              </div>
              <div class="col-sm-4">
                  <a href="http://localhost:8080/money_manager-1.0-SNAPSHOT/new_expense" class="btn btn-primary btn-block" role="button">New Expense</a>
              </div>
              <div class="col-sm-4">
                  <a href="http://localhost:8080/money_manager-1.0-SNAPSHOT/upcoming_expenses" class="btn btn-primary btn-block" role="button">Upcoming expenses</a>
              </div>
          </div>
          <div class="row">
              <div class="col-sm-4">
                  <a href="http://localhost:8080/money_manager-1.0-SNAPSHOT/entered_months" class="btn btn-primary btn-block" role="button">Entered months</a>
              </div>
              <div class="col-sm-4">
                  <a href="http://localhost:8080/money_manager-1.0-SNAPSHOT/monthly_reports" class="btn btn-primary btn-block" role="button">Monthly reports</a>
              </div>
              <div class="col-sm-4">
                  <a href="http://localhost:8080/money_manager-1.0-SNAPSHOT/#" class="btn btn-primary btn-block" role="button">Graphs</a>
              </div>
          </div>
      </div>
      </p>
   </body>

   <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
   <script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
   <script src="${pageContext.request.contextPath}/js/main.js"></script>

   <h2>Application by <a>Monika Gruszka & Michał Szymański</a></h2>
</html>