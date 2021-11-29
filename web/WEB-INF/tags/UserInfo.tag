<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="user" type="com.Serebriakov.database.entity.User" required="true" rtexprvalue="true" %>

<div class="login">
    <c:choose>
        <c:when test="${user!=null}">
            You logged in as:
            <a href="/edit_user.jsp">${user.login}</a>
            <form class="logout" action="controller" method="post">
                <input type="hidden" name="command" value="logout">
                <input type="submit" value="Logout">
            </form>
        </c:when>
        <c:otherwise>
            You are not logged in! <a class="login" href="login.jsp">Login</a>
        </c:otherwise>
    </c:choose>
</div>