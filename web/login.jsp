<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="mylib" tagdir="/WEB-INF/tags" %>
<html>
  <head>
    <title><fmt:message key="title.taxi"/></title>
    <link rel="stylesheet" href="style.css">
  </head>
  <body>
    <mylib:Settings/>
    <form action="controller" method="post" accept-charset="UTF-8">
      <fmt:message key="login_jsp.label.login"/>:
      <br>
      <input type="hidden" name="command" value="login">
      <input name="login"><br>
      <fmt:message key="login_jsp.label.password"/>:
      <br>
      <input type="password" name="password"><br>
      <input type="submit" value="<fmt:message key="login_jsp.label.login_button"/>">
    </form>
    <a href="registration.jsp"><fmt:message key="login_jsp.label.sign_up"/></a>
  </body>
</html>