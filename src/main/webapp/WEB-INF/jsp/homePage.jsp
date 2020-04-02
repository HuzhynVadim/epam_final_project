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
    <form action="ticket_order" method="POST">
        <p><b><fmt:message key="rout.from"/></b><br>
            <input name="departure station" type="text">
        </p>
        <p><b><fmt:message key="rout.to"/></b><br>
            <input name="arrival station" type="text">

        </p>
        <p><b><fmt:message key="rout.when"/></b><br>
            <input name="departure date" type="date">

        </p>
<%--        <p><b><fmt:message key="rout.car_type"/></b><br>--%>
<%--            <select name="car type[]">--%>
<%--                <option><fmt:message key="selectCarType"/></option>--%>
<%--                <option><fmt:message key="car.type.compartment"/></option>--%>
<%--                <option><fmt:message key="car.type.reserved_seat"/></option>--%>
<%--                <option><fmt:message key="car.type.common"/></option>--%>
<%--            </select>--%>
<%--        </p>--%>
        <p><b></b><br>
            <input type="submit" name="rout_select" value="<fmt:message key="rout.select"/>">
        </p>
    </form>
    <c:choose>
        <c:when test="${role == 'ADMIN'}">
            <form action="administrator_account" method="GET">
                <input type="submit" name="order" value="<fmt:message key="admin.account"/>">
            </form>
        </c:when>
        <c:when test="${role == 'USER'}">
            <form action="user_account" method="GET">
                <input type="submit" name="order" value="<fmt:message key="user.account"/>">
            </form>
        </c:when>
    </c:choose>
    <form action="login.jsp">
        <input type="submit" value="<fmt:message key="back"/>">
    </form>
</center>
</body>
</html>
