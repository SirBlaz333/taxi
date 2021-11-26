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
        ${requestScope['javax.servlet.error.message']}
    </text>
    <br>
    <c:choose>
        <c:when test="${currentUser != null}">
            <a href="index.jsp">To user page</a>
        </c:when>
        <c:otherwise>
            <a href="login.jsp">To login page</a>
        </c:otherwise>
    </c:choose>
    <c:remove var="errorMessage"/>
    <br>
</body>
</html>
