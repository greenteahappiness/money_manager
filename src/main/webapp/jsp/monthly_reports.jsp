<%@ page contentType = "text/html; charset = UTF-8" %>
<html>
   <head>
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/new_data.css">
      <title>Enter Month Data</title>
   </head>

   <body>
      <h1><b>Monthly reports</b></h1>
      <h2><b>Specify year and month for your report</b></h2>
  <p3>
   <form method="post">
   <ul>
     <li>Year:
     <input type="text" name="year" value=${current_year}> </li> <br>
       <li>Month: <br> <select name="month">
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
       <li>
           <table>
           <tr>
           <td><h2><input name="one_or_all" type="submit" value="One month report" > </td></h2>
           <td><h2><input name="one_or_all" type="submit" value="All months report" > </td></h2>
           </table>
       </li>
    </ul>
    </form>
    </p3>
   </body>
   <h2>Application by <b>Monika Gruszka & Michał Szymański</b></h2>
   <h2>Theme created with love by <a href=\"http://pablogarciafernandez.com\" target="_blank">Pablo García Fernández</a></h2>

</html>