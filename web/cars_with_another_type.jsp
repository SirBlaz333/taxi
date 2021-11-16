<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html>
<head>
    <title>Taxi</title>
    <link rel="stylesheet" href="listStyle.css">
</head>
<body>
    <text>Choose another taxi type:</text>
    <c:forEach var="carType" items="${carTypeOptions}">
        <form action="controller" method="post">
            <input type="hidden" name="command" value="createReceipt">
            <input type="hidden" name="car_type" value="${carType}">
            <input type="submit" value="${carType}">
        </form>
    </c:forEach>
    <a href="choose_page.jsp">Go back</a>
</body>
</html>
