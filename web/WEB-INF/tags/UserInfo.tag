<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="user" type="com.Serebriakov.database.entity.User" required="true" rtexprvalue="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="login">
    <c:choose>
        <c:when test="${user!=null}">
            <fmt:message key="user_info.label.you_logged_in_as"/>:
            <a href="/edit_user.jsp">${user.login}</a>
            <form class="logout" action="controller" method="post">
                <input type="hidden" name="command" value="logout">
                <input type="submit" value="<fmt:message key="user_info.label.logout"/>">
            </form>
        </c:when>
        <c:otherwise>
            <fmt:message key="user_info.label.you_are_not_logged_in"/> <a class="login" href="login.jsp"><fmt:message key="login_jsp.label.login"/></a>
        </c:otherwise>
    </c:choose>
</div>