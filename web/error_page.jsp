<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html>
<head>
    <title>Error</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <text class="error">
        Error!!!
        <br>
        ${sessionScope.errorMessage}
    </text>
    <br>
    <c:choose>
        <c:when test="${sessionScope.currentUser != null}">
            <a href="user_page.jsp">Go back to user page</a>
        </c:when>
        <c:otherwise>
            <a href="index.jsp">Log in</a>
        </c:otherwise>
    </c:choose>
</body>
</html>
