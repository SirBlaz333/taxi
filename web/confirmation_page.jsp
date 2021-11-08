<html>
<head>
    <title>Taxi</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <h1>Your receipt:</h1><br>
    Car type: ${sessionScope.car_type}<br>
    Amount of passengers: ${sessionScope.passengers}<br>
    Departure: ${sessionScope.currentReceipt.getDeparture()}<br>
    Destination: ${sessionScope.currentReceipt.getDestination()}<br>
    Length: ${sessionScope.currentReceipt.getLength()}<br>
    Price per km: ${sessionScope.currentReceipt.getPricePerKm()}<br>
    Total price: ${sessionScope.currentReceipt.getPrice()}<br>
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
