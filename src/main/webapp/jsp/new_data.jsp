<%@ page contentType = "text/html; charset = UTF-8" %>
<html>
   <head>
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/new_data.css">
      <title>Enter Month Data</title>
   </head>

   <body>
      <h1><b>Enter month data</b></h1>
      <h2><b><a href="http://localhost:8080/money_manager-1.0-SNAPSHOT/">Back to Money Manager</a></b></h2>

      <h4><b>Wybierz miesiąc i wprowadź dane dla tego miesiąca.
       Możesz wprowadzić dane tylko jednego miesiąca
      naraz. <br> Dane zostaną zapisane dopiero po kliknięciu przycisku akceptacji.</b></h4>
   <p3>
   <form method="post">
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
    </form>
    </p3>
   </body>
   <h2>Application by <b>Monika Gruszka & Michał Szymański</b></h2>
   <h2>Theme created with love by <a href=\"http://pablogarciafernandez.com\" target="_blank">Pablo García Fernández</a></h2>

</html>