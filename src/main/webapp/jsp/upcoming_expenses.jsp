<%@ page contentType = "text/html; charset = UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html>
   <head>
     <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
     <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/upcoming_expenses.css">
     <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
     <title>Upcoming expenses</title>
   </head>

   <body>
      <h1><b>Upcoming expenses</b></h1>
      <h3 class="month-header">Better way of planning upcoming expenses</h3>
      <h2><b><a href="http://localhost:8080/money_manager-1.0-SNAPSHOT/">Back to Money Manager</a></b></h2>

      <div class="column">
        <h2><b>Backlog</b></h2>
        <c:forEach items="${backlog_entries}" var="entry">
            <div class="portlet">
                <div class="portlet-header">${entry.description}</div>
                <div class="portlet-content">${entry.category}</div>
            </div>
        </c:forEach>
      </div>

      <div class="column">
        <h2><b>March</b></h2>
        <c:forEach items="${march_entries}" var="entry">
              <div class="portlet">
                  <div class="portlet-header">${entry.description}</div>
                  <div class="portlet-content">${entry.category}</div>
              </div>
        </c:forEach>
      </div>

      <div class="column">
        <h2><b>April</b></h2>
      </div>

      <div class="column">
        <h2><b>May</b></h2>
      </div>

   </body>
   <footer>
   <h2>Application by <b>Monika Gruszka & Michał Szymański</b></h2>
   <h2>Theme created with love by <a href=\"http://pablogarciafernandez.com\" target="_blank">Pablo García Fernández</a></h2>
   </footer>
   <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
   <script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
   <script src="${pageContext.request.contextPath}/js/upcoming_expenses.js"></script>
</html>