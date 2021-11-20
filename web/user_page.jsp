<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html>
<head>
    <title>Taxi</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <c:choose>
        <c:when test="${currentUser.getRoleString().equals('admin')}">
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
                            <input name="userName" placeholder="Enter username">
                        </div>
                    </input>
                    <input class="radio" name="filter" type="radio" value="Date">
                        Date
                        <div class="button">
                            <input name="date" type="date">
                        </div>
                    </input>
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
                            <th>User id</th>
                            <th>Price</th>
                            <th>Departure</th>
                            <th>Destination</th>
                            <th>Time</th>
                            <th>State</th>
                        </tr>
                        <c:forEach var="receipt" items="${RequiredReceipts}">
                            <tr>
                                <th>${receipt.id}</th>
                                <th>${receipt.userId}</th>
                                <th>${receipt.priceDouble}</th>
                                <th>${receipt.departure}</th>
                                <th>${receipt.destination}</th>
                                <th>${receipt.time}</th>
                                <th>${receipt.getStateString()}</th>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <form action="controller" method="post" accept-charset="UTF-8">
                <h1>Enter receipt:</h1>
                <text class="page">Choose car type:</text><br>
                <input type="hidden" name="command" value="createReceipt">
                <input type="radio" name="car_type" value="Public hire taxi" required>Public hire taxi</input><br>
                <input type="radio" name="car_type" value="Minicab">Minicab</input><br>
                <input type="radio" name="car_type" value="Minibus">Minibus</input><br>
                <br>
                <text class="page">Enter amount of passengers:</text>
                <input name="passengers" type="number" min="1" max="100" required>
                <br><br>
                <text class="page">Enter departure</text>
                <input name="departure" type="text" required>
                <br><br>
                <text class="page">Enter destination</text>
                <input name="destination" type="text" required>
                <br><br>
                <input type="submit" value="Submit">
            </form>
        </c:otherwise>
    </c:choose>
</body>
</html>
