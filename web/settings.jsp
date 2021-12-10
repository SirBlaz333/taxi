<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title><fmt:message key="settings_jsp.title"/></title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <form action="changeLocal.jsp" method="post">
        <fmt:message key="settings_jsp.label.set_locale"/>
        <select name="locale">
            <c:forEach items="${applicationScope.locales}" var="locale">
                <option value="${locale.key}">${locale.value}</option>
            </c:forEach>
        </select>
        <input type="submit" value="<fmt:message key="global.submit"/>"/>
    </form>
    <c:choose>
        <c:when test="${currentUser != null}">
            <a href="index.jsp"><fmt:message key="settings_jsp.label.to_index"/></a>
        </c:when>
        <c:otherwise>
            <a href="login.jsp"><fmt:message key="settings_jsp.label.to_login"/></a>
        </c:otherwise>
    </c:choose>
</body>
</html>
