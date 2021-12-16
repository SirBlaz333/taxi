<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="mylib" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="s" uri="/WEB-INF/tags/SecurityTag" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<s:checkPermission user="${currentUser}" role="admin" redirect="error_page.jsp"/>
<html>
<head>
    <title><fmt:message key="admin_page.title"/></title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <mylib:Settings/>
    <mylib:UserInfo user="${currentUser}"/>
    <div class="center">
        <div class="name">
            <h1 class="name"><fmt:message key="admin_page.receipts"/></h1>
        </div>
        <form class="input" action="controller" method="post">
            <input type="hidden" name="command" value="updateReceipts">
            <fmt:message key="admin_page.filer_by"/>:
            <input class="radio" name="filter" type="radio" value="User">
            <fmt:message key="admin_page.user"/>
            <div class="button">
                <input name="userName" maxlength="32" placeholder="<fmt:message key="admin_page.username_placeholder"/>">
            </div>
            </input>
            <input class="radio" name="filter" type="radio" value="Date">
            <fmt:message key="admin_page.date"/>
            <div class="button">
                <input name="date" type="date">
            </div>
            <input type="hidden" name="filter" value="None">
            <text class="right"><fmt:message key="admin_page.sort_by"/>:</text>
            <select name="sort">
                <option value="None" selected="selected"><fmt:message key="admin_page.sort_by_none"/></option>
                <option value="Time"><fmt:message key="admin_page.sort_by_time"/></option>
                <option value="Price"><fmt:message key="admin_page.sort_by_price"/></option>
            </select>
            <br>
            <input type="submit" value="<fmt:message key="admin_page.submit"/>">
        </form>
        <form action="controller" method="post">
            <input type="hidden" name="command" value="updateReceiptsData">
            <input type="submit" value="<fmt:message key="admin_page.update_receipts"/>">
        </form>
        <div class="list">
            <table border="1">
                <tr>
                    <th><fmt:message key="admin_page.table.id"/></th>
                    <th><fmt:message key="admin_page.table.username"/></th>
                    <th><fmt:message key="admin_page.table.price"/></th>
                    <th><fmt:message key="admin_page.table.departure"/></th>
                    <th><fmt:message key="admin_page.table.destination"/></th>
                    <th><fmt:message key="admin_page.table.time"/></th>
                    <th><fmt:message key="admin_page.table.state"/></th>
                </tr>
                <c:forEach var="receipt" items="${requiredReceipts}">
                    <tr>
                        <th>${receipt.id}</th>
                        <th>${receipt.user.login}</th>
                        <th>${receipt.priceDouble}</th>
                        <th>${receipt.departure}</th>
                        <th>${receipt.destination}</th>
                        <th>${receipt.time}</th>
                        <th><mylib:ReceiptState state="${receipt.state.toString()}"/></th>
                        <th><form action="controller" method="post">
                            <input type="hidden" name="command" value="showReceipt">
                            <input type="hidden" name="id" value="${receipt.id}">
                            <input type="submit" value="<fmt:message key="admin_page.table.more"/>">
                        </form></th>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <br>
        <div class="page">
            <form class="page" action="controller" method="post">
                <input type="hidden" name="command" value="page">
                <c:choose>
                    <c:when test="${page > 1}">
                        <input type="submit" value="<fmt:message key="admin_page.page.previous"/>">
                        <input type="hidden" name="direction" value="previous">
                    </c:when>
                    <c:otherwise>
                        <fmt:message key="admin_page.page.previous"/>
                    </c:otherwise>
                </c:choose>

                ${page}

                <c:choose>
                    <c:when test="${page < maxPossiblePage}">
                        <input type="submit" value="<fmt:message key="admin_page.page.next"/>">
                        <input type="hidden" name="direction" value="next">
                    </c:when>
                    <c:otherwise>
                        <fmt:message key="admin_page.page.next"/>
                    </c:otherwise>
                </c:choose>
            </form>
    </div>
    </div>
</body>
</html>
