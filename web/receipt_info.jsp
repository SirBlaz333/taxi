<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="mylib" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="s" uri="/WEB-INF/tags/SecurityTag" %>
<s:checkPermission user="${currentUser}" role="admin" redirect="error_page.jsp"/>
<html>
<head>
    <title>Receipt info</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <div class="center">
        <h1>Receipt №${receiptInfo.id}</h1>
        Login: ${receiptInfo.user.login}<br>
        Name: ${receiptInfo.user.name}<br>
        Car type: ${carTypeInfo}<br>
        Amount of passengers: ${receiptInfo.passengers}<br>
        Departure: ${receiptInfo.departure}<br>
        Destination: ${receiptInfo.destination}<br>
        Length: ${receiptInfo.lengthDouble}<br>
        Total price: ${receiptInfo.priceDouble}<br>
        Time: ${receiptInfo.time}<br><br>
        <a href="admin.jsp">Back</a>
    </div>
</body>
</html>
