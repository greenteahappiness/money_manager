<%@ page contentType="text/html; charset = UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <link href="https://fonts.googleapis.com/css?family=Dosis" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/upcoming_expenses.css">
    <link id="theme" rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
    <title>Upcoming expenses</title>
</head>

<body>
<section class="jumbotron text-center">
    <div class="container">
        <h1 class="jumbotron-heading"><b>Upcoming expenses</b></h1>
        <h3 class="month-header">Better way of planning upcoming expenses</h3>
        <h2><b><a href=".">Back to Money Manager</a></b></h2>
        <select class="form-control" onchange="changeTheme(value);">
            <option value="default-dark">Default</option>
            <option value="pastel-pink">Pastel pink</option>
        </select>
    </div>
</section>

<div class="column" id="backlog">
    <h2><b>Backlog</b></h2>
    <button type="button" class="btn btn-primary" id="add-card">+</button>
</div>

<div class="column">
    <h2><b>March</b></h2>
</div>

<div class="column">
    <h2><b>April</b></h2>
</div>

<div class="column">
    <h2><b>May</b></h2>
</div>

<div class="column">
    <h2><b>June</b></h2>
</div>

<div class="column">
    <h2><b>July</b></h2>
</div>

<div class="column" id="August">
    <h2><b>August</b></h2>
</div>
</body>
<!--<footer>
<h2>Application by <b>Monika Gruszka & Michał Szymański</b></h2>
<h2>Theme created with love by <a href=\"http://pablogarciafernandez.com\" target="_blank">Pablo García Fernández</a></h2>
</footer>-->
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/main.js"></script>
<script src="${pageContext.request.contextPath}/js/upcoming_expenses.js"></script>
</html>
