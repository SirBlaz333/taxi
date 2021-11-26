<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="mylib" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>Taxi</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <mylib:UserInfo user="${currentUser}"/>
    <form action="controller" method="post" accept-charset="UTF-8">
        <h1>Enter receipt:</h1>
        <text class="page">Choose car type:</text><br>
        <input type="hidden" name="command" value="createReceipt">
        <input type="radio" name="car_type" value="Public hire taxi" required>Public hire taxi</input><br>
        <input type="radio" name="car_type" value="Minicab">Minicab</input><br>
        <input type="radio" name="car_type" value="Minibus">Minibus</input><br>
        <br>
        <text class="page">Enter amount of passengers:</text>
        <input name="passengers" type="number" min="1" max="100" required>
        <br><br>
        <text class="page">Enter departure</text>
        <input name="departure" type="text" maxlength="45" required>
        <br><br>
        <text class="page">Enter destination</text>
        <input name="destination" type="text" maxlength="45" required>
        <br><br>
        <input type="submit" value="Submit">
    </form>
</body>
</html>
