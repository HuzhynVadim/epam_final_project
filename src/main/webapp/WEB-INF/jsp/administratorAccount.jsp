<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mrt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="lang"/>
<html>
<head>
    <title><fmt:message key="admin.account"/></title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <style>
        body {
            width: 100%;
            background-color: #f5f5f5;
        }

        table {
            table-layout: fixed;
            width: auto;
            height: auto;
            text-align: center;
        }

        tr {
            width: auto;
            height: auto;
            text-align: center;

        }

        td {
            width: auto;
            text-align: center;

        }
    </style>
</head>
<body>
<mrt:navigation/>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
        integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>
<div id="1"/>
<div class="h5" align="right">
    <fmt:message key="enterRole"></fmt:message>
    <mrt:role role="${user.role}"></mrt:role>
</div>
<div class="h2" style="text-align: center;">
    <fmt:message key="admin.account"></fmt:message>
</div>
<p class="h4">
    <h12><fmt:message key="admin.rout.information"/></h12>
</p>
<table class="table table-bordered table-hover text-center" border="1" style="width: auto">
    <thead class="thead-light text-center">
    <tr>
        <th><fmt:message key="rout.name"/></th>
        <th><fmt:message key="rout.number"/></th>
        <th><fmt:message key="train.number"/></th>
        <th><fmt:message key="details"/></th>
        <th><fmt:message key="admin.editInformation"/></th>
        <th><fmt:message key="delete"/></th>
    </tr>
    </thead>
    <tbody class="text-center">
    <c:forEach items="${rout_list}" var="rout">
        <tr>
            <td>${rout.routName}</td>
            <td>${rout.routNumber}</td>
            <td>${rout.trainNumber}</td>
            <td>
                <form action="administrator_details_set_rout" method="GET">
                    <input type="hidden" name="routs_id" value="${rout.routsId}">
                    <input type="submit" class="btn btn-info" name="details" value="<fmt:message key="admin.details"/>">
                </form>
            </td>
            <td>
                <form action="administrator_edit_info_rout" method="GET">
                    <input type="hidden" name="routs_id" value="${rout.routsId}">
                    <input type="submit" class="btn btn-info" name="edit_info_rout"
                           value="<fmt:message key="admin.editInformation"/>">
                </form>
            </td>
            <td>
                <form action="rout_delete" method="POST">
                    <input type="hidden" name="routs_id" value="${rout.routsId}">
                    <input type="submit" class="btn btn-danger" name="remove_rout"
                           value="<fmt:message key="admin.remove"/>">
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<form action="administrator_add_rout" method="GET" id="2">
    <input type="submit" class="btn btn-success" name="add_rout" value="<fmt:message key="admin.addRout"/>">
</form>
<p class="h4">
    <h12><fmt:message key="admin.station.information"/></h12>
</p>
<table class="table table-bordered table-hover" border="1" style="width: auto">
    <thead class="thead-light">
    <tr>
        <th><fmt:message key="station"/></th>
        <th><fmt:message key="edit"/></th>
        <th><fmt:message key="delete"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${station_list}" var="station">
        <tr>
            <td>${station.station}</td>
            <td>
                <form action="administrator_edit_info_station" method="GET">
                    <input type="hidden" name="station" value="${station.stationId}">
                    <input type="submit" class="btn btn-info" name="edit_info_station"
                           value="<fmt:message key="admin.editInformation"/>">
                </form>
            </td>
            <td>
                <form action="station_delete" method="POST">
                    <input type="hidden" name="station" value="${station.stationId}">
                    <input type="submit" class="btn btn-danger" name="remove_station"
                           value="<fmt:message key="admin.remove"/>">
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<form action="administrator_add_station" method="GET" id="3">
    <input type="submit" class="btn btn-success" name="add_station" value="<fmt:message key="admin.addStation"/>">
</form>
<p class="h4">
    <h12><fmt:message key="admin.car.information"/></h12>
</p>
<table class="table table-bordered table-hover" border="1" style="width: auto">
    <thead class="thead-light">
    <tr>
        <th><fmt:message key="train.number"/></th>
        <th><fmt:message key="car.type"/></th>
        <th><fmt:message key="car.number"/></th>
        <th><fmt:message key="car.seats"/></th>
        <th><fmt:message key="car.price"/></th>
        <th><fmt:message key="edit"/></th>
        <th><fmt:message key="delete"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${car_list}" var="car">
        <tr>
            <td>${car.trainNumber}</td>
            <td><fmt:message key="${car.carType}"/></td>
            <td>${car.carNumber}</td>
            <td>${car.seats}</td>
            <td>${car.price}</td>
            <td>
                <form action="administrator_edit_info_car" method="GET">
                    <input type="hidden" name="car_id" value="${car.carId}">
                    <input type="submit" class="btn btn-info" name="edit_info_car"
                           value="<fmt:message key="admin.editInformation"/>">
                </form>
            </td>
            <td>
                <form action="car_delete" method="POST">
                    <input type="hidden" name="car_id" value="${car.carId}">
                    <input type="submit" class="btn btn-danger" name="remove_car"
                           value="<fmt:message key="admin.remove"/>">
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<form action="administrator_add_car" method="GET" id="4">
    <input type="submit" class="btn btn-success" name="add_car" value="<fmt:message key="admin.addCar"/>">
</form>
<p class="h4">
    <h12><fmt:message key="admin.train.information"/></h12>
</p>
<table class="table table-bordered table-hover" border="1" style="width: auto">
    <thead class="thead-light">
    <tr>
        <th><fmt:message key="train.number"/></th>
        <th><fmt:message key="edit"/></th>
        <th><fmt:message key="delete"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${train_list}" var="train">
        <tr>
            <td>${train.trainNumber}</td>
            <td>
                <form action="administrator_edit_info_train" method="GET">
                    <input type="hidden" name="train_id" value="${train.trainId}">
                    <input type="submit" class="btn btn-info" name="edit_info_train"
                           value="<fmt:message key="admin.editInformation"/>">
                </form>
            </td>
            <td>
                <form action="train_delete" method="POST">
                    <input type="hidden" name="train_id" value="${train.trainId}">
                    <input type="submit" class="btn btn-danger" name="remove_train"
                           value="<fmt:message key="admin.remove"/>">
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<form action="administrator_add_train" method="GET" id="5">
    <input type="submit" class="btn btn-success" name="add_train" value="<fmt:message key="admin.addTrain"/>">
</form>
<p class="h4">
    <h12><fmt:message key="admin.user.information"/></h12>
</p>
<table class="table table-bordered table-hover" border="1" style="width: auto">
    <thead class="thead-light">
    <tr>
        <th><fmt:message key="user.email"/></th>
        <th><fmt:message key="user.first_name"/></th>
        <th><fmt:message key="user.last_name"/></th>
        <th><fmt:message key="user.birth_date"/></th>
        <th><fmt:message key="user.phone"/></th>
        <th><fmt:message key="admin.blockStatus"/></th>
        <th><fmt:message key="admin.block"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${user_list}" var="user">

        <tr>
            <td>${user.email}</td>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.birthDate}</td>
            <td>${user.phone}</td>
            <td><fmt:message key="${user.blocked}"/></td>
            <td>
                <c:choose>
                    <c:when test="${user.blocked == false}">
                        <form action="user_block" method="POST">
                            <input type="hidden" name="user_id" value="${user.userId}">
                            <input type="hidden" name="block_status" value="true">
                            <input type="submit" class="btn btn-warning" name="block"
                                   value="<fmt:message key="admin.block"/>">
                        </form>
                    </c:when>
                    <c:when test="${user.blocked == true}">
                        <form action="user_block" method="POST">
                            <input type="hidden" name="user_id" value="${user.userId}">
                            <input type="hidden" name="block_status" value="false">
                            <input type="submit" class="btn btn-warning" name="block"
                                   value="<fmt:message key="admin.unblock"/>">
                        </form>
                    </c:when>
                </c:choose>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<p class="h4" id="6">
    <h12><fmt:message key="admin.order.information"/></h12>
</p>
<table class="table table-bordered table-hover text-center" border="1" style="width: auto">
    <thead class="thead-light text-center">
    <tr>
        <th><fmt:message key="order.user.information"/></th>
        <th><fmt:message key="order.train.number"/></th>
        <th><fmt:message key="rout.name"/></th>
        <th><fmt:message key="order.car.type"/></th>
        <th><fmt:message key="order.car.number"/></th>
        <th><fmt:message key="order.count.of.seats"/></th>
        <th><fmt:message key="order.seats.number"/></th>
        <th><fmt:message key="order.price"/></th>
        <th><fmt:message key="order.dispatch.station"/></th>
        <th><fmt:message key="order.dispatch.date"/></th>
        <th><fmt:message key="order.travel.time"/></th>
        <th><fmt:message key="order.arrival.station"/></th>
        <th><fmt:message key="order.arrival.date"/></th>
        <th><fmt:message key="order.date"/></th>
        <th><fmt:message key="order.status"/></th>
        <th><fmt:message key="admin.editInformation"/></th>
    </tr>
    </thead>
    <tbody class="text-center">
    <c:forEach items="${order_list}" var="order">
            <tr>
                <td>${order.user.firstName} ${order.user.lastName}</td>
                <td>${order.trainNumber}</td>
                <td>${order.routsId}</td>
                <td><fmt:message key="${order.carType}"/></td>
                <td>${order.carNumber}</td>
                <td>${order.countOfSeats}</td>
                <td>${order.seatNumber}</td>
                <td>${order.price}</td>
                <td>${order.dispatchStation}</td>
                <td>${order.arrivalDate}</td>
                <td>${order.travelTime}</td>
                <td>${order.arrivalStation}</td>
                <td>${order.dispatchDate}</td>
                <td>${order.orderDate}</td>
                <td><fmt:message key="${order.orderStatus}"/></td>
                <td>
                    <form action="administrator_edit_info_order" method="GET">
                        <input type="hidden" name="order_id" value="${order.orderId}">
                        <input type="submit" class="btn btn-info" name="edit_info_order"
                               value="<fmt:message key="admin.editInformation"/>">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<p>
<form action="home" method="GET">
    <input type="submit" class="btn btn-primary" value="<fmt:message key="back"/>">
</form>
</p>
</body>
</html>