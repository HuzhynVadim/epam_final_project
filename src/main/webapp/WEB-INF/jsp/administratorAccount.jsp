<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mrt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="lang"/>
<html>
<head>
    <title><fmt:message key="admin.account"/></title>
</head>
<body>
<center>
    <fmt:message key="account"></fmt:message>
</center>
<p>
    <h12><fmt:message key="admin.rout.information"/></h12>
</p>
<table border="1">
    <tr>
        <th><fmt:message key="rout.name"/></th>
        <th><fmt:message key="rout.number"/></th>
        <th><fmt:message key="train.number"/></th>
        <th><fmt:message key="details"/></th>
        <th><fmt:message key="admin.editInformation"/></th>
        <th><fmt:message key="delete"/></th>
    </tr>
    <c:forEach items="${rout_list}" var="rout">
        <tr>
            <td>${rout.routName}</td>
            <td>${rout.routNumber}</td>
            <td>${rout.trainNumber}</td>
            <td>
                <form action="administrator_details_set_rout" method="GET">
                    <input type="hidden" name="rout" value="${rout.routsId}">
                    <input type="submit" name="details" value="<fmt:message key="admin.details"/>">
                </form>
            </td>
            <td>
                <form action="administrator_edit_info_rout" method="GET">
                    <input type="hidden" name="rout" value="${rout.routsId}">
                    <input type="submit" name="edit_info_rout" value="<fmt:message key="admin.editInformation"/>">
                </form>
            </td>
            <td>
                <form action="rout_delete" method="POST">
                    <input type="hidden" name="rout" value="${rout.routsId}">
                    <input type="submit" name="remove_rout" value="<fmt:message key="admin.remove"/>">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<form action="administrator_add_rout" method="GET">
    <input type="submit" name="add_rout" value="<fmt:message key="admin.addRout"/>">
</form>
<p>
    <h12><fmt:message key="admin.station.information"/></h12>
</p>
<table border="1">
    <tr>
        <th><fmt:message key="station"/></th>
        <th><fmt:message key="edit"/></th>
        <th><fmt:message key="delete"/></th>
    </tr>
    <c:forEach items="${station_list}" var="station">
        <tr>
            <td>${station.station}</td>
            <td>
                <form action="administrator_edit_info_station" method="GET">
                    <input type="hidden" name="station" value="${station.stationId}">
                    <input type="submit" name="edit_info_station" value="<fmt:message key="admin.editInformation"/>">
                </form>
            </td>
            <td>
                <form action="station_delete" method="POST">
                    <input type="hidden" name="station" value="${station.stationId}">
                    <input type="submit" name="remove_station" value="<fmt:message key="admin.remove"/>">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<form action="administrator_add_station" method="GET">
    <input type="submit" name="add_station" value="<fmt:message key="admin.addStation"/>">
</form>
<p>
    <h12><fmt:message key="admin.order.information"/></h12>
</p>
<table border="1">
    <tr>
        <th><fmt:message key="order.id"/></th>
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
            <td>${order.orderId}</td>
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
                    <input type="submit" name="remove_order" value="<fmt:message key="admin.remove"/>">
                </form>
            </td>
            <td>
                <form action="administrator_edit_info_order" method="GET">
                    <input type="hidden" name="order" value="${order.orderId}">
                    <input type="submit" name="edit_info_order" value="<fmt:message key="admin.editInformation"/>">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<p>
    <h12><fmt:message key="admin.user.information"/></h12>
</p>
<table border="1">
    <tr>

        <th><fmt:message key="user.email"/></th>
        <th><fmt:message key="user.first_name"/></th>
        <th><fmt:message key="user.last_name"/></th>
        <th><fmt:message key="user.birth_date"/></th>
        <th><fmt:message key="user.phone"/></th>
        <th><fmt:message key="admin.blockStatus"/></th>
        <th><fmt:message key="admin.block"/></th>
    </tr>
    <c:forEach items="${user_list}" var="user">

        <tr>
            <td>${user.email}</td>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.birthDate}</td>
            <td>${user.phone}</td>
            <td>${user.blocked}</td>
            <td>
                <c:choose>
                    <c:when test="${user.blocked == false}">
                        <form action="user_block" method="POST">
                            <input type="hidden" name="user_id" value="${user.userId}">
                            <input type="hidden" name="block_status" value="true">
                            <input type="submit" name="block" value="<fmt:message key="admin.block"/>">
                        </form>
                    </c:when>
                    <c:when test="${user.blocked == true}">
                        <form action="user_block" method="POST">
                            <input type="hidden" name="user_id" value="${user.userId}">
                            <input type="hidden" name="block_status" value="false">
                            <input type="submit" name="block" value="<fmt:message key="admin.unblock"/>">
                        </form>
                    </c:when>
                </c:choose>
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