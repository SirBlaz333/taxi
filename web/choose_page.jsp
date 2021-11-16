<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html>
<head>
    <title>Choose taxi</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <text class="error">${NoSuchCarError}</text>
    <br><br>
    <form action="controller" method="post">
        <input type="hidden" name="command" value="chooseSeveralCars">
        <input type="submit" value="Choose several cars">
    </form>
    <form action="controller" method="post">
        <input type="hidden" name="command" value="chooseCarsWithAnotherType">
        <input type="submit" value="Choose cars with another type">
    </form>
</body>
</html>
