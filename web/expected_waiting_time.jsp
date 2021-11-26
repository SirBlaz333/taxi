<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="mylib" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>Expected waiting time</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <div class="thanks">
        <text>Thank You for you order!</text><br>
        <text>Your expected waiting time: ${ExpectedWaitingTime} minutes</text><br>
        <a href="index.jsp">Make one more receipt</a>
    </div>
</body>
</html>
