<%@ page contentType = "text/html; charset = UTF-8" %>
<html>
   <head>
      <link href="https://fonts.googleapis.com/css?family=Dosis" rel="stylesheet">
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
      <link id="theme" rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/pastel_pink/main.css">
      <title>Enter Month Data</title>
   </head>

   <body>
      <h1><b>Enter month data</b></h1>
      <h2><b><a href="http://localhost:8080/money_manager-1.0-SNAPSHOT/">Back to Money Manager</a></b></h2>

      <h4><b>Wybierz miesiąc i wprowadź dane dla tego miesiąca.
       Możesz wprowadzić dane tylko jednego miesiąca
      naraz. <br> Dane zostaną zapisane dopiero po kliknięciu przycisku akceptacji.</b></h4>
   <p3>
   <!--<form method="post">
   <ul>
     <li>Year:
     <input type="text" name="year" value=${current_year}> </li> <br>
       <li> <select name="month">
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
       </select> </li>
       <br>
     <li><label for="portfel_koniec">Stan portfela na koniec miesiąca: </label><input type="text" name="portfel_koniec"></li> <br>
     <li><label for="konto_koniec">Saldo konta na koniec miesiąca: </label><input type="text" name="konto_koniec"></li> <br>
     <li><label for="paypal_koniec">Saldo PayPal koniec miesiąca: </label><input type="text" name="paypal_koniec"></li> <br>
     <li><label for="nadwyzka">Nadwyżka po poprzednim miesiącu: </label><input type="text" name="nadwyzka"></li> <br>
     <li><label for="pensja">Pensja: </label><input type="text" name="pensja"></li> <br>
    <li><label><input type="checkbox" checked>Copy periodic expenses from last month</label></li>
    <li>
        <input type="submit" value="Apply" onclick="return alert('New month data added!');">
    </li>
    </ul>
    </form>-->


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
          <label for="portfel_koniec">Stan portfela na koniec miesiąca</label>
          <input type="text" class="form-control" name="portfel_koniec" id="portfel_koniec" placeholder="0">
        </div>
        <div class="form-group col-md-4">
          <label for="konto_koniec">Stan konta na koniec miesiąca</label>
          <input type="text" class="form-control" name="konto_koniec" id="konto_koniec" placeholder="0">
        </div>
      </div>
      <div class="form-row">
      <div class="form-group col-md-4">
        <label for="paypal_koniec">Saldo PayPal na koniec miesiąca</label>
        <input type="text" class="form-control" name="paypal_koniec" id="paypal_koniec" placeholder="0">
      </div>
      <div class="form-group col-md-4">
        <label for="nadwyzka">Nadwyżka</label>
        <input type="text" class="form-control" id="nadwyzka" name="nadwyzka" placeholder="0">
      </div>
      </div>
      <div class="form-row">
        <div class="form-group col-md-4">
          <label for="pensja">Pensja</label>
          <input type="text" class="form-control" id="pensja" name="pensja">
        </div>
      </div>
      <div class="form-group">
        <div class="form-check">
          <input class="form-check-input" type="checkbox" id="gridCheck">
          <label class="form-check-label" for="gridCheck">
            Copy periodic expenses from last month
          </label>
        </div>
      </div>
      <button type="submit" class="btn btn-primary">Sign in</button>
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