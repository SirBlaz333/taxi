<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="mylib" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title><fmt:message key="title.taxi"/></title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <mylib:Settings/>
    <mylib:UserInfo user="${currentUser}"/>
    <form action="controller" method="post" accept-charset="UTF-8">
        <h1><fmt:message key="index_jsp.label.enter_receipt"/>:</h1>
        <text class="page"><fmt:message key="index_jsp.label.choose_car_type"/>:</text><br>
        <input type="hidden" name="command" value="createReceipt">
        <input type="radio" name="car_type" value="Public hire taxi" required><fmt:message key="type.public_hire_taxi"/></input><br>
        <input type="radio" name="car_type" value="Minicab"><fmt:message key="type.minicab"/></input><br>
        <input type="radio" name="car_type" value="Minibus"><fmt:message key="type.minibus"/></input><br>
        <br>
        <text class="page"><fmt:message key="index_jsp.label.enter_amount_of_passengers"/>:</text>
        <input name="passengers" type="number" min="1" max="100" required>
        <br><br>
        <text class="page"><fmt:message key="index_jsp.label.enter_departure"/></text>
        <input name="departure" type="text" maxlength="45" required>
        <br><br>
        <text class="page"><fmt:message key="index_jsp.label.enter_destination"/></text>
        <input name="destination" type="text" maxlength="45" required>
        <br><br>
        <input type="submit" value="<fmt:message key="global.submit"/>">
    </form>
</body>
</html>
