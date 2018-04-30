<%@ page contentType = "text/html; charset = UTF-8" %>
<html>
   <head>
      <link href="https://fonts.googleapis.com/css?family=Dosis" rel="stylesheet">
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
      <link id="theme" rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
      <title>Monthly reports</title>
   </head>

   <body>
      <h1><b>Monthly reports</b></h1>
      <h2><b><a href="http://localhost:8080/money_manager-1.0-SNAPSHOT/">Back to Money Manager</a></b></h2>

      <h4><b>Specify year and month for your report</b></h4>
  <p3>
    <form class="data-form col-md-offset-3" method="post">
            <div class="form-row">
            <div class="form-group col-md-4">
              <label for="year">Year</label>
              <input type="text" name="year" class="form-control" id="year" value=${current_year}>
            </div>
            <div class="form-group col-md-4">
              <label for="month">Month</label>
              <select name="month" id="month" class="form-control">
                <option selected>Choose month</option>
                 <option value="1">January</option>
                 <option value="2">February</option>
                 <option value="3">March</option>
                 <option value="4">April</option>
                 <option value="5">May</option>
                 <option value="6">June</option>
                 <option value="7">July</option>
                 <option value="8">August</option>
                 <option value="9">September</option>
                 <option value="10">October</option>
                 <option value="11">November</option>
                 <option value="12">December</option>
              </select>
            </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-4">
                  <button type="submit" name="one_or_all" value="One month report" class="btn btn-primary">One month report</button>
                  <button type="submit" name="one_or_all" value="All months report" class="btn btn-primary">All months report</button>
                </div>
            </div>
        </form>
    </p3>
   </body>
   <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
   <script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
   <script src="${pageContext.request.contextPath}/js/main.js"></script>
   <h2>Application by <b>Monika Gruszka & Michał Szymański</b></h2>
   <h2>Theme created with love by <a href=\"http://pablogarciafernandez.com\" target="_blank">Pablo García Fernández</a></h2>

</html>