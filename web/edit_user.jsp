<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="mylib" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="s" uri="/WEB-INF/tags/SecurityTag" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<s:checkPermission user="${currentUser}" role="user" redirect="error_page.jsp"/>
<html>
<head>
    <title><fmt:message key="edit_user_jsp.title_user"/></title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <mylib:Settings/>
    <div class="center">
        <h1><fmt:message key="edit_user_jsp.edit_user"/></h1>
        <fmt:message key="edit_user_jsp.your_login"/>: ${currentUser.login}<br>
        <fmt:message key="edit_user_jsp.your_discount"/>: ${currentUser.discount}%<br>
        <form action="controller" method="post">
            <input type="hidden" name="command" value="updateUser">
            <input name="email" type="email" maxlength="45" title="<fmt:message key="registration_jsp.email_title"/>" placeholder="<fmt:message key="registration_jsp.email"/>: ${currentUser.email}"><br>
            <input name="name" type="text" pattern="[a-z0-9а-яёєіїґА-ЯЁЭA-Z]{2,20}[ ]+[a-z0-9а-яёєіїґА-ЯЁЭA-Z]{2,24}"
                   maxlength="45" title="<fmt:message key="registration_jsp.full_name_title"/>" placeholder="<fmt:message key="registration_jsp.full_name"/>: ${currentUser.name}">
            <span class="validity"/><br>
            <input type="password" pattern="[^ ]{4,20}" name="password" title="<fmt:message key="registration_jsp.password_title"/>" maxlength="20" placeholder="<fmt:message key="registration_jsp.password"/>">
            <span class="validity"/><br><br>
            <input type="submit" value="<fmt:message key="edit_user_jsp.save_changes"/>"><br><br>
            <a href="index.jsp"><fmt:message key="settings_jsp.label.to_index"/></a>
        </form>
    </div>
</body>
</html>
