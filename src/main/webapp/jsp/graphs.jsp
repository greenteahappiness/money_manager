<%@ page contentType = "text/html; charset = UTF-8" %>
<html>
   <head>
      <link href="https://fonts.googleapis.com/css?family=Dosis" rel="stylesheet">
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
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
      <h1 class="jumbotron-heading"><b>Graphs</b></h1>
      <h2><b><a href="http://localhost:8080/money_manager-1.0-SNAPSHOT/">Back to Money Manager</a></b></h2>
      <h3 class="month-header">App to help you manage your money every day</h3>
     </section>
      <p>
      <form class="data-form col-md-offset-5" method="post">
        <div class="form-row">
            <div class="form-group col-md-5">
            <label for="year">Year</label>
            <input type="text" name="year" class="form-control" id="year" value=${current_year}>
            </div>
            <div class="form-group col-md-5">
                  <label for="month">Month</label>
                  <select name="month" id="month" class="form-control" onchange="onMonthChange(this.value)">
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
      <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.js"></script>
      <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.bundle.js"></script>
      <div id="graph-container">
      <canvas id="myChart" width="200" height="200"></canvas>
      </div>
      </div>
      </div>
      </div>
      </form>
      </p>
   </body>

   <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
   <script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
   <script src="${pageContext.request.contextPath}/js/main.js"></script>

   <h2>Application by <a>Monika Gruszka & Michał Szymański</a></h2>
</html>