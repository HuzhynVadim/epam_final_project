<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mrt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="lang"/>
<html>
<head>
    <title><fmt:message key="homepage"/></title>
</head>
<body>
<form>
    <div align="right">
        <fmt:message key="enterRole"></fmt:message>
        <mrt:role role="${user.role}"></mrt:role>
    </div>
</form>
<center>
    <form action="selection_of_the_route_for_ordering" method="GET">
        <p><b><fmt:message key="rout.from"/></b><br>
            <input name="departure_station" type="text">
        </p>
        <p><b><fmt:message key="rout.to"/></b><br>
            <input name="arrival_station" type="text">

        </p>
        <p><b><fmt:message key="rout.when"/></b><br>
            <input name="departure_date" type="datetime-local">

        </p>
        <p><b></b><br>
            <input type="submit" name="route_search" value="<fmt:message key="rout.search"/>">
        </p>
    </form>
    <c:choose>
        <c:when test="${role == 'ADMIN'}">
            <form action="administrator_account" method="GET">
                <input type="hidden" name="user_id" value="${user.userId}">
                <input type="submit" name="account" value="<fmt:message key="admin.account"/>">
            </form>
        </c:when>
        <c:when test="${role == 'USER'}">
            <form action="user_account" method="GET">
                <input type="hidden" name="user_id" value="${user.userId}">
                <input type="submit" name="account" value="<fmt:message key="user.account"/>">
            </form>
        </c:when>
    </c:choose>
    <form action="login.jsp">
        <input type="submit" value="<fmt:message key="back"/>">
    </form>
</center>
</body>
</html>
