<%@ page contentType = "text/html; charset = UTF-8" %>
<html>
   <head>
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/new_data.css">
      <title>Enter Month Data</title>
   </head>

   <body>
      <h1><b>${message}</b></h1>
      <h2><b>Wybierz miesiąc i wprowadź dane dla tego miesiąca.
       Możesz wprowadzić dane tylko jednego miesiąca
      naraz. <br> Dane zostaną zapisane dopiero po kliknięciu przycisku akceptacji.</b></h2>
   <p3>
   <form>
   <ul>
     <li>Year:
     <input type="text" name="year"> </li> <br>
       <li> <select>
         <option value="january">January</option>
         <option value="february">February</option>
         <option value="march">March</option>
         <option value="april">April</option>
         <option value="may">May</option>
         <option value="june">June</option>
         <option value="july">July</option>
         <option value="august">August</option>
         <option value="september">September</option>
         <option value="october">October</option>
         <option value="november">November</option>
         <option value="december">December</option>
       </select> </li>
       <br>
     <li><label for="portfel_koniec">Stan portfela na koniec miesiąca: </label><input type="text" name="portfel_koniec"></li> <br>
     <li><label for="konto_koniec">Saldo konta na koniec miesiąca: </label><input type="text" name="konto_koniec"></li> <br>
     <li><label for="paypal_koniec">Saldo PayPal koniec miesiąca: </label><input type="text" name="paypal_koniec"></li> <br>
     <li><label for="nadwyzka">Nadwyżka po poprzednim miesiącu: </label><input type="text" name="nadwyzka"></li> <br>
     <li><label for="pensja">Pensja: </label><input type="text" name="pensja"></li> <br>
    <li><label><input type="checkbox" checked>Copy periodic expenses from last month</label></li>
    <li>
        <input type="submit" value="Apply" >
    </li>
    </ul>
    </form>
    </p3>
   </body>
   <h2>Application by <b>Monika Gruszka & Michał Szymański</b></h2>
   <h2>Theme created with love by <a href=\"http://pablogarciafernandez.com\" target="_blank">Pablo García Fernández</a></h2>

</html>