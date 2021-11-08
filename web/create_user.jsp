<html>
<head>
    <title>Taxi</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <form action="controller" method="post" accept-charset="UTF-8">
        <input type="hidden" name="command" value="createUser">
        <br>Login:<br>
        <input name="login">
        <br>Email:<br>
        <input name="email" type="email">
        <br>Password:<br>
        <input type="password" name="password"><br><br>
        <input type="submit" value="Sign up">
    </form>
</body>
</html>
