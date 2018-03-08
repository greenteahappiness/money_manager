<%@ page contentType = "text/html; charset = UTF-8" %>
<html>
   <head>
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/new_expense.css">
      <title>Add new expense</title>
   </head>

   <body>
      <h1><b>Add new expense</b></h1>
      <h2><b><a href="http://localhost:8080/money_manager-1.0-SNAPSHOT/">Back to Money Manager</a></b></h2>
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
       </select> </li> <br>
        <li>Description:
        <input type="text" name="description"> </li> <br>
        <li>Value:
        <input type="text" name="value"> </li> <br>
        <li>Expense type: <br> <select name="expense_type">
             <option value="own_expenses">Own expense</option>
             <option value="periodic_expenses">Periodic expense</option>
             <option value="other_expenses">Other expense</option>
             <option value="oob_expenses">Out of budget expense</option>
             <option value="debts">Debt</option>
             <option value="transfers">Transfer from savings</option>
           </select> </li> <br>
       <li>Category: <br> <select name="category">
            <option value="clothes">Clothes</option>
            <option value="cosmetics">Cosmetics</option>
            <option value="hobby_books">Hobby and books</option>
            <option value="meetings">Dates and meetings</option>
            <option value="other">Other</option>
          </select> </li> <br>
       <li>
           <h2><input type="submit" value="Apply" ></h2>
       </li>
    </ul>
    </form>
    </p3>
   </body>
   <h2>Application by <b>Monika Gruszka & Michał Szymański</b></h2>
   <h2>Theme created with love by <a href=\"http://pablogarciafernandez.com\" target="_blank">Pablo García Fernández</a></h2>

</html>