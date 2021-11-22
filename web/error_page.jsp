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
        ${errorMessage}
    </text>
    <br>
    <c:choose>
        <c:when test="${currentUser != null}">
            <a href="index.jsp">Go back to user page</a>
        </c:when>
        <c:otherwise>
            <a href="login.jsp">Log in</a>
        </c:otherwise>
    </c:choose>
</body>
</html>
