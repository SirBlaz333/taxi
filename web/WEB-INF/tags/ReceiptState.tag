<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="state" type="java.lang.String" required="true" rtexprvalue="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${state.equals('CREATED')}">
    <fmt:message key="state.created"/>
</c:if>
<c:if test="${state.equals('CONFIRMED')}">
    <fmt:message key="state.confirmed"/>
</c:if>
<c:if test="${state.equals('DONE')}">
    <fmt:message key="state.done"/>
</c:if>