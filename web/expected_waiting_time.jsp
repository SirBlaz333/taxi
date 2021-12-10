<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="mylib" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="s" uri="/WEB-INF/tags/SecurityTag" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<s:checkPermission user="${currentUser}" role="user" redirect="error_page.jsp"/>
<html>
<head>
    <title><fmt:message key="title.taxi"/></title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <div class="thanks">
        <text><fmt:message key="expected_waiting_time_jsp.thanks"/></text><br>
        <text><fmt:message key="expected_waiting_time_jsp.time"/>: ${ExpectedWaitingTime} <fmt:message key="expected_waiting_time_jsp.minutes"/></text><br>
        <a href="index.jsp"><fmt:message key="expected_waiting_time_jsp.new_receipt"/></a>
    </div>
</body>
</html>
