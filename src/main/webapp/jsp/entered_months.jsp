<%@ page contentType = "text/html; charset = UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
   <head>
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/new_expense.css">
      <title>Add new expense</title>
   </head>

   <body>
      <h1><b>Entered months</b></h1>
  <p3>
   <form method="post">
   <ul>
     <li>Year:
     <input type="text" name="year" value=${current_year}> </li> <br>
       <li>
          <h2><input type="submit" name="confirm_year" value="Confirm year" ></h2>
      </li>
        <li>Open months: <br> <select name="open_month">
             <c:forEach items="${open_months_list}" var="val">
                 <option value="${val}"><c:out value = "${val}"/></option>
             </c:forEach>
       </select> </li> <br>
       <li>
           <h2><input type="submit" name="close_month" value="Close month" ></h2>
       </li>

        <li>Closed months: <br> <select name="closed_month">
          <c:forEach items="${closed_months_list}" var="val">
            <option value="${val}"><c:out value = "${val}"/></option>
          </c:forEach>
       </select> </li> <br>
       <li>
           <h2><input type="submit" name="reopen_month" value="Reopen month" ></h2>
       </li>
    </ul>
    </form>
    </p3>
   </body>
   <h2>Application by <b>Monika Gruszka & Michał Szymański</b></h2>
   <h2>Theme created with love by <a href=\"http://pablogarciafernandez.com\" target="_blank">Pablo García Fernández</a></h2>

</html>