<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
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
        <input type="hidden" name="command" value="createUser">
        <input type="text" id="login" name="login" required maxlength="32"
               pattern="[a-z0-9а-яёєіїґА-ЯЁЭA-Z]{3,32}" title="<fmt:message key="registration_jsp.login_title"/>" placeholder="<fmt:message key="registration_jsp.login"/>">
        <span class="validity"/><br><br>
        <input name="email" type="email" maxlength="45" title="<fmt:message key="registration_jsp.email_title"/>" required placeholder="<fmt:message key="registration_jsp.email"/>"><br><br>
        <input name="name" type="text" pattern="[a-zа-яёєіїґА-ЯЁЭA-Z]{2,20}[ ]+[a-zа-яёєіїґА-ЯЁЭA-Z]{2,24}"
               maxlength="45" title="<fmt:message key="registration_jsp.full_name_title"/>" required placeholder="<fmt:message key="registration_jsp.full_name"/>">
        <span class="validity"/><br><br>
        <input type="password" pattern="[^ ]{4,20}" name="password" title="<fmt:message key="registration_jsp.password_title"/>" maxlength="20" required placeholder="<fmt:message key="registration_jsp.password"/>">
        <span class="validity"/> <br><br>
        <input type="submit" value="<fmt:message key="registration_jsp.sign_up"/>">
    </form>
</body>
</html>
