<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html>
<head>
    <title>Taxi</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <form action="controller" method="post" accept-charset="UTF-8">
        <input type="hidden" name="command" value="createUser">
        <br>Login:<br>
        <input type="text" id="login" name="login" required maxlength="32"
               pattern="[a-z0-9а-яёєіїґА-ЯЁЭA-Z]{6,32}" title="6 to 32 letters or numbers">
        <span class="validity"></span>
        <br>Email:<br>
        <input name="email" type="email" maxlength="45" title="max amount of symbols is 45" required>
        <br>Password:<br>
        <input type="password" pattern="[^ ]{4,20}" name="password" title="4 to 20 (no spaces)" maxlength="20" required>
        <span class="validity"></span> <br><br>
        <input type="submit" value="Sign up">
    </form>
</body>
</html>
