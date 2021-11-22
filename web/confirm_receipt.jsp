<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html>
<head>
    <title>Confirm receipt</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<h1>Your receipt:</h1><br>
    Receipt id:${currentReceipt.id}<br>
    <c:if test="${amountOfCars != null}">
        Amount of cars: ${amountOfCars}<br>
    </c:if>
    Car type: ${currentReceipt.carType}<br>
    Amount of passengers: ${currentReceipt.passengers}<br>
    Departure: ${currentReceipt.departure}<br>
    Destination: ${currentReceipt.destination}<br>
    Length: ${currentReceipt.lengthDouble}<br>
    Price per km for 1 car: ${currentReceipt.pricePerKm}<br>
    Total price: ${currentReceipt.priceDouble}<br>
    Time: ${currentReceipt.time}<br>
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