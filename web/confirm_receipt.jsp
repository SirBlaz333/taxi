<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="/WEB-INF/tags/SecurityTag" %>
<%@ taglib prefix="mylib" tagdir="/WEB-INF/tags" %>
<s:checkPermission user="${currentUser}" role="user" redirect="error_page.jsp"/>
<html>
<head>
    <title><fmt:message key="confirm_receipt_jsp.title"/></title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<h1><fmt:message key="confirm_receipt_jsp.your_receipt"/>:</h1><br>
    <fmt:message key="confirm_receipt_jsp.car_type"/>:
    <mylib:CarType carType="${currentReceipt.carType}"/>
    <br>
    <c:if test="${amountOfCars != null}">
        <fmt:message key="confirm_receipt_jsp.amount_of_cars"/>: ${amountOfCars}<br>
    </c:if>
    <fmt:message key="confirm_receipt_jsp.amount_of_passengers"/>: ${currentReceipt.passengers}<br>
    <fmt:message key="confirm_receipt_jsp.departure"/>: ${currentReceipt.departure}<br>
    <fmt:message key="confirm_receipt_jsp.destination"/>: ${currentReceipt.destination}<br>
    <fmt:message key="confirm_receipt_jsp.length"/>: ${currentReceipt.lengthDouble}<br>
    <fmt:message key="confirm_receipt_jsp.minimal_price"/>: 20<br>
    <c:choose>
        <c:when test="${amountOfCars != null}">
            <fmt:message key="confirm_receipt_jsp.price_per_next_km_for_1_car"/>: ${currentReceipt.pricePerKm}₴<br>
        </c:when>
        <c:otherwise>
            <fmt:message key="confirm_receipt_jsp.price_per_next_km"/>: ${currentReceipt.pricePerKm}₴<br>
        </c:otherwise>
    </c:choose>
    <fmt:message key="confirm_receipt_jsp.discount"/>: ${currentUser.discount}%<br>
    <fmt:message key="confirm_receipt_jsp.total_price"/>: ${currentReceipt.priceDouble}₴<br>
    <fmt:message key="confirm_receipt_jsp.creation_time"/>: ${currentReceipt.time}<br>
    <form action="controller" method="post">
        <input type="hidden" name="command" value="deleteReceipt">
        <input type="submit" value="<fmt:message key="confirm_receipt_jsp.delete_receipt"/>">
    </form>
    <form action="controller" method="post">
        <input type="hidden" name="command" value="confirmReceipt">
        <input type="submit" value="<fmt:message key="confirm_receipt_jsp.confirm_receipt"/>">
</form>
</body>
</html>