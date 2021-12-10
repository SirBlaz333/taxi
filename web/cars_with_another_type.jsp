<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tags/SecurityTag" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="mylib" tagdir="/WEB-INF/tags" %>
<s:checkPermission user="${currentUser}" role="user" redirect="error_page.jsp"/>
<html>
<head>
    <title><fmt:message key="title.taxi"/></title>
    <link rel="stylesheet" href="choose_cars.css">
</head>
<body>
    <h1><fmt:message key="cars_with_another_type.choose_type"/>:</h1>
    <c:forEach var="carType" items="${carTypeOptions}">
        <form action="controller" method="post">
            <input type="hidden" name="command" value="createReceipt">
            <input type="hidden" name="car_type" value="${carType}">
            <input type="submit" value="<mylib:CarType carType="${carType}"/>">
        </form>
    </c:forEach>
    <br><br>
    <a href="choose_options.jsp"><fmt:message key="cars_with_another_type.go_back"/></a>
</body>
</html>
