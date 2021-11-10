<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Choose taxi</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <text class="error">${sessionScope.NoSuchCarError}</text>
    <br><br>
    <form action="controller" method="post">
        <input type="hidden" name="command" value="chooseSeveralCars">
        <input type="submit" value="Choose several cars">
    </form>
    <form action="controller" method="post">
        <input type="hidden" name="command" value="chooseCarsWithAnotherTypeCommand">
        <input type="submit" value="Choose cars with another type">
    </form>
</body>
</html>
