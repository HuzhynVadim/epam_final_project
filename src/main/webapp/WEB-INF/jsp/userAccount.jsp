<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mrt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="lang"/>
<html>
<head>
    <title><fmt:message key="user.account"/></title>
</head>
<body>
<div align="right">
    <fmt:message key="enterRole"></fmt:message>
    <mrt:role role="${user.role}"></mrt:role>
</div>
<center>
    <fmt:message key="account"></fmt:message>
</center>
<p>
    <h12><fmt:message key="user.order.information"/></h12>
</p>
<table border="1">
    <tr>
        <th><fmt:message key="order.user.information"/></th>
        <th><fmt:message key="order.train.number"/></th>
        <th><fmt:message key="order.car.number"/></th>
        <th><fmt:message key="order.car.type"/></th>
        <th><fmt:message key="order.price"/></th>
        <th><fmt:message key="order.arrival.date"/></th>
        <th><fmt:message key="order.dispatch.date"/></th>
        <th><fmt:message key="order.date"/></th>
        <th><fmt:message key="order.status"/></th>
        <th><fmt:message key="admin.remove"/></th>
        <th><fmt:message key="admin.editInformation"/></th>
    </tr>
    <c:forEach items="${order_list}" var="order">
        <tr>
            <td>${order.user}</td>
            <td>${order.trainNumber}</td>
            <td>${order.carNumber}</td>
            <td>${order.carType}</td>
            <td>${order.price}</td>
            <td>${order.arrivalDate}</td>
            <td>${order.dispatchDate}</td>
            <td>${order.orderDate}</td>
            <td>${order.orderStatus}</td>
            <td>
                <form action="order_delete" method="POST">
                    <input type="hidden" name="order" value="${order.orderId}">
                    <input type="submit" name="remove_order" value="<fmt:message key="user.remove"/>">
                </form>
            </td>
            <td>
                <form action="user_edit_info_order" method="GET">
                    <input type="hidden" name="order" value="${order.orderId}">
                    <input type="submit" name="edit_info_order" value="<fmt:message key="user.editInformation"/>">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<p>
<form action="home" method="GET">
    <input type="submit" value="<fmt:message key="back"/>">
</form>
</p>
</body>
</html>