<html>
  <head>
    <title>Taxi</title>
    <link rel="stylesheet" href="style.css">
  </head>
  <body>
    <form action="controller" method="post">
      Login:
      <br>
      <input type="hidden" name="command" value="login">
      <input name="login"><br>
      Password:
      <br>
      <input type="password" name="password"><br>
      <input type="submit" value="Login">
    </form>
    <a href="create_user.jsp">Sign up</a>
  </body>
</html>