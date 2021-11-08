<html>
<head>
    <title>Taxi</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <form action="controller" method="post">
        <h1>Enter receipt:</h1>
        <hr>
        <text>Choose car type:</text><br>
        <input type="hidden" name="command" value="createReceipt">
        <input type="radio" name="car_type" value="Public hire taxi">Public hire taxi</input><br>
        <input type="radio" name="car_type" value="Minicab">Minicab</input><br>
        <input type="radio" name="car_type" value="Minibus">Minibus</input><br>
        <hr>
        <text>Enter amount of passengers:</text>
        <input name="passengers" type="number">
        <hr>
        <text>Enter departure</text>
        <input name="departure" type="text">
        <hr>
        <text>Enter destination</text>
        <input name="destination" type="text">
        <br>
        <hr>
        <input type="submit" value="Submit">
    </form>
</body>
</html>
