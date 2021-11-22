<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="th" uri="/WEB-INF/ReceiptTag" %>
<html>
  <head>
    <title>Taxi</title>
    <link rel="stylesheet" href="style.css">
  </head>
  <body>
    <form action="controller" method="post" accept-charset="UTF-8">
      Login:
      <br>
      <input type="hidden" name="command" value="login">
      <input name="login"><br>
      Password:
      <br>
      <input type="password" name="password"><br>
      <input type="submit" value="Login">
    </form>
    <a href="registration.jsp">Sign up</a>
  </body>
</html>