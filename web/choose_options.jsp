<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tags/SecurityTag" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="mylib" tagdir="/WEB-INF/tags" %>
<s:checkPermission user="${currentUser}" role="user" redirect="error_page.jsp"/>
<html>
<head>
    <title><fmt:message key="title.taxi"/></title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <text class="error">
        <c:if test="${NoSuchCarErrorID == 1}">
            <fmt:message key="choose_options_jsp.no_such_car_error"/>
        </c:if>
        <c:if test="${NoSuchCarErrorID == 2}">
            <fmt:message key="choose_options_jsp.no_such_car_error2"/>
        </c:if>
        (<mylib:CarType carType="${currentReceipt.carType}"/>)
    </text>
    <br><br>
    <form action="controller" method="post">
        <input type="hidden" name="command" value="chooseSeveralCars">
        <input type="submit" value="<fmt:message key="choose_options_jsp.choose_several_cars"/>">
    </form>
    <form action="controller" method="post">
        <input type="hidden" name="command" value="chooseCarsWithAnotherType">
        <input type="submit" value="<fmt:message key="choose_options_jsp.choose_cars_with_another_type"/>">
    </form>
    <form action="controller" method="post">
        <input type="hidden" name="command" value="deleteReceipt">
        <input type="submit" value="<fmt:message key="confirm_receipt_jsp.delete_receipt"/>">
    </form>
</body>
</html>
