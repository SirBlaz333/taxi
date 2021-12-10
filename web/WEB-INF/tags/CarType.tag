<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="carType" type="java.lang.String" required="true" rtexprvalue="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${carType.equals('Public hire taxi')}">
    <fmt:message key="type.public_hire_taxi"/>
</c:if>
<c:if test="${carType.equals('Minicab')}">
    <fmt:message key="type.minicab"/>
</c:if>
<c:if test="${carType.equals('Minibus')}">
    <fmt:message key="type.minibus"/>
</c:if>