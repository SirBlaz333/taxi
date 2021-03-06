<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Error</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <text class="error">
        <fmt:message key="error_page.error"/>!!!
        <br>
        ${errorMessage}
        ${requestScope['javax.servlet.error.message']}
    </text>
    <br>
    <c:choose>
        <c:when test="${currentUser != null}">
            <a href="index.jsp"><fmt:message key="settings_jsp.label.to_index"/></a>
        </c:when>
        <c:otherwise>
            <a href="login.jsp"><fmt:message key="settings_jsp.label.to_login"/></a>
        </c:otherwise>
    </c:choose>
    <c:remove var="errorMessage"/>
    <br>
</body>
</html>
