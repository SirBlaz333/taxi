<html>
  <head>
    <title>Taxi</title>
  </head>
  <body>
    <form action="controller" method="post">
      <input type="hidden" name="command" value="login">
      <input name="login"><br>
      <input type="password" name="password"><br>
      <input type="submit" value="Login">
    </form>
    <form action="controller" method="post">
      <input type="hidden" name="command" value="createUser">
      <input type="submit" value="Sign up">
    </form>
  </body>
</html>