<%@ page contentType = "text/html; charset = UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html>
   <head>
      <link href="https://fonts.googleapis.com/css?family=Dosis" rel="stylesheet">
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
      <link id="theme" rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
      <title>Add new expense</title>
   </head>

   <body>
        <c:if test="${not empty msg_successful}">
           <div class="alert alert-success alert-dismissible fade show" role="alert">
           <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
           ${msg_successful}
           </div>
        </c:if>
        <c:if test="${not empty msg}">
            <div class="alert alert-danger alert-dismissible" role="alert">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            ${msg}
            </div>
        </c:if>
      <h1><b>Add new expense</b></h1>
      <h2><b><a href="http://localhost:8080/money_manager-1.0-SNAPSHOT/">Back to Money Manager</a></b></h2>
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
              <label for="description">Description</label>
              <input type="text" class="form-control" name="description" id="description" placeholder="Shoes">
            </div>
            <div class="form-group col-md-4">
              <label for="value">Value</label>
              <input type="text" class="form-control" name="value" id="value" placeholder="0">
            </div>
          </div>
          <div class="form-row">
          <div class="form-group col-md-4">
            <label for="expense_type">Expense type</label>
            <select name="expense_type" class="form-control">
                <option selected>Choose expense type</option>
                <option value="own_expenses">Own expense</option>
                <option value="periodic_expenses">Periodic expense</option>
                <option value="other_expenses">Other expense</option>
                <option value="oob_expenses">Out of budget expense</option>
                <option value="debts">Debt</option>
                <option value="transfers">Transfer from savings</option>
            </select>
          </div>
          <div class="form-group col-md-4">
              <label for="category">Category</label>
              <select name="category" class="form-control">
                  <option selected>Choose expense category</option>
                  <option value="clothes">Clothes</option>
                  <option value="cosmetics">Cosmetics</option>
                  <option value="hobby_books">Hobby and books</option>
                  <option value="meetings">Dates and meetings</option>
                  <option value="other">Other</option>
              </select>
            </div>
          </div>
          <button type="submit" class="btn btn-primary">Add expense</button>
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