<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html>
<head>
    <title>Confirm receipt</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<h1>Your receipt:</h1><br>
    Receipt id:${sessionScope.currentReceipt.getId()}<br>
    <c:if test="${sessionScope.amountOfCars != null}">
        Amount of cars: ${sessionScope.amountOfCars}<br>
    </c:if>
    Car type: ${sessionScope.currentReceipt.getCarType()}<br>
    Amount of passengers: ${sessionScope.currentReceipt.getPassengers()}<br>
    Departure: ${sessionScope.currentReceipt.getDeparture()}<br>
    Destination: ${sessionScope.currentReceipt.getDestination()}<br>
    Length: ${sessionScope.currentReceipt.getLengthDouble()}<br>
    Price per km for 1 car: ${sessionScope.currentReceipt.getPricePerKm()}<br>
    Total price: ${sessionScope.currentReceipt.getPriceDouble()}<br>
    Time: ${sessionScope.currentReceipt.getDate()}<br>
    <form action="controller" method="post">
        <input type="hidden" name="command" value="deleteReceipt">
        <input type="submit" value="Delete receipt">
    </form>
    <form action="controller" method="post">
        <input type="hidden" name="command" value="confirmReceipt">
        <input type="submit" value="Confirm receipt">
</form>
</body>
</html>