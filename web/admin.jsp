<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="mylib" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="s" uri="/WEB-INF/tags/SecurityTag" %>
<s:checkPermision user="${currentUser}" redirect="error_page.jsp"/>
<html>
<head>
    <title>Admin page</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <mylib:UserInfo user="${currentUser}"/>
    <div class="center">
        <div class="name">
            <h1 class="name">Receipts</h1>
        </div>
        <form class="input" action="controller" method="post">
            <input type="hidden" name="command" value="updateReceipts">
            Filter by:
            <input class="radio" name="filter" type="radio" value="User">
            User
            <div class="button">
                <input name="userName" maxlength="32" placeholder="Enter username">
            </div>
            </input>
            <input class="radio" name="filter" type="radio" value="Date">
            Date
            <div class="button">
                <input name="date" type="date">
            </div>
            <input type="hidden" name="filter" value="None">
            <text class="right">Sort by:</text>
            <select name="sort">
                <option value="None" selected="selected">None</option>
                <option value="Time">Time</option>
                <option value="Price">Price</option>
            </select>
            <br>
            <input type="submit" value="Submit">
        </form>
        <div class="list">
            <table border="1">
                <tr>
                    <th>Id</th>
                    <th>Username</th>
                    <th>Price</th>
                    <th>Departure</th>
                    <th>Destination</th>
                    <th>Time</th>
                    <th>State</th>
                </tr>
                <c:forEach var="receipt" items="${requiredReceipts}">
                    <tr>
                        <th>${receipt.id}</th>
                        <th>${receipt.userName}</th>
                        <th>${receipt.priceDouble}</th>
                        <th>${receipt.departure}</th>
                        <th>${receipt.destination}</th>
                        <th>${receipt.time}</th>
                        <th>${receipt.getStateString()}</th>
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
                        <input type="submit" value="Previous">
                        <input type="hidden" name="direction" value="previous">
                    </c:when>
                    <c:otherwise>
                        Previous
                    </c:otherwise>
                </c:choose>

                ${page}

                <c:choose>
                    <c:when test="${page < maxPossiblePage}">
                        <input type="submit" value="Next">
                        <input type="hidden" name="direction" value="next">
                    </c:when>
                    <c:otherwise>
                        Next
                    </c:otherwise>
                </c:choose>
            </form>
    </div>
    </div>
</body>
</html>
