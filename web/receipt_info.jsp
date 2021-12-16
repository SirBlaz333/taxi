<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="mylib" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="s" uri="/WEB-INF/tags/SecurityTag" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<s:checkPermission user="${currentUser}" role="admin" redirect="error_page.jsp"/>
<html>
<head>
    <title><fmt:message key="receipt_info_jsp.title"/></title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <div class="center">
        <h1><fmt:message key="receipt_info_jsp.order"/> №${receiptInfo.id}</h1>
        <fmt:message key="receipt_info_jsp.login"/>: ${receiptInfo.user.login}<br>
        <fmt:message key="receipt_info_jsp.full_name"/>: ${receiptInfo.user.name}<br>
        <fmt:message key="receipt_info_jsp.car_type"/>: <mylib:CarType carType="${carTypeInfo}"/><br>
        <fmt:message key="receipt_info_jsp.amount_of_passengers"/>: ${receiptInfo.passengers}<br>
        <fmt:message key="receipt_info_jsp.departure"/>: ${receiptInfo.departure}<br>
        <fmt:message key="receipt_info_jsp.destination"/>: ${receiptInfo.destination}<br>
        <fmt:message key="receipt_info_jsp.length"/> ${receiptInfo.lengthDouble}<br>
        <fmt:message key="receipt_info_jsp.total_price"/>: ${receiptInfo.priceDouble}₴<br>
        <fmt:message key="receipt_info_jsp.creation_time"/>: ${receiptInfo.time}<br>
        <fmt:message key="receipt_info_jsp.state"/>: <mylib:ReceiptState state="${receiptInfo.state.toString()}"/><br>
        <c:if test="${receiptInfo.state.toString().equals('CONFIRMED')}">
            <form action="controller" method="post">
                <input type="hidden" name="command" value="completeReceipt">
                <input type="submit" value="<fmt:message key="receipt_info_jsp.complete_receipt"/>">
            </form>
        </c:if><br>
        <a href="admin.jsp"><fmt:message key="receipt_info_jsp.back"/></a>
    </div>
</body>
</html>
