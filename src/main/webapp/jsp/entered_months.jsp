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
      <h1><b>Entered months</b></h1>
      <h2><b><a href="http://localhost:8080/money_manager-1.0-SNAPSHOT/">Back to Money Manager</a></b></h2>
  <p3>
    <form class="data-form col-md-offset-3" method="post" style="margin: 0 35%;">
        <div class="form-row">
        <div class="form-group col-md-4">
          <label for="year">Year</label>
          <input type="text" name="year" class="form-control" id="year" value=${current_year}>
        </div>
        </div>
        <div class="form-row">
        <div class="form-group col-md-4 text-center">
              <button type="submit" name="confirm_year" value="Confirm year" class="btn btn-primary">Confirm year</button>
        </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-4">
                  <label for="open_month">Open months</label>
                  <select name="open_month" class="form-control">
                      <c:forEach items="${open_months_list}" var="val">
                           <option value="${val}"><c:out value = "${val}"/></option>
                      </c:forEach>
                  </select>
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-4 text-center">
                <button type="submit" name="close_month" value="Close month" class="btn btn-primary">Close month</button>
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-4 text-center">
              <label for="closed_month">Closed months</label>
              <select name="closed_month" class="form-control">
                  <c:forEach items="${closed_months_list}" var="val">
                       <option value="${val}"><c:out value = "${val}"/></option>
                  </c:forEach>
              </select>
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-4 text-center">
              <button type="submit" name="reopen_month" value="Reopen month" class="btn btn-primary">Reopen month</button>
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