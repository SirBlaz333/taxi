<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="mylib" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="s" uri="/WEB-INF/tags/SecurityTag" %>
<s:checkPermission user="${currentUser}" role="user" redirect="error_page.jsp"/>
<html>
<head>
    <title>Edit user</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <div class="center">
        <h1>Edit user</h1>
        Your login: ${currentUser.login}<br>
        Your current discount: ${currentUser.discount}%<br>
        <form action="controller" method="post">
            <input type="hidden" name="command" value="updateUser">
            <input name="email" type="email" maxlength="45" title="max amount of symbols is 45" placeholder="Email: ${currentUser.email}"><br>
            <input name="name" type="text" pattern="[a-z0-9а-яёєіїґА-ЯЁЭA-Z]{2,20}[ ]+[a-z0-9а-яёєіїґА-ЯЁЭA-Z]{2,25}"
                   maxlength="45" title="Enter first name(from 2 to 20 symbols) and second name(from 2 to 25 symbols)" placeholder="Name: ${currentUser.name}">
            <span class="validity"/><br>
            <input type="password" pattern="[^ ]{4,20}" name="password" title="4 to 20 (no spaces)" maxlength="20" placeholder="Password">
            <span class="validity"/><br><br>
            <input type="submit" value="Save changes"><br><br>
            <a href="index.jsp">Go back</a>
        </form>
    </div>
</body>
</html>
