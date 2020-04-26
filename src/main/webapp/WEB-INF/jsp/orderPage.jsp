<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="lang"/>
<html>
<head>
    <title><fmt:message key="user.makeOrder"/></title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <style>
        body {
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
<mrt:nav/>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
        integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>
<form action="make_order" method="POST">
    <table class="table table-bordered table-hover text-center" border="1" style="width: auto">
        <thead class="thead-light text-center">
        <tr>
            <th><fmt:message key="station.dispatch"/></th>
            <th><fmt:message key="station.arrival"/></th>
            <th><fmt:message key="car.type"/></th>
            <th><fmt:message key="count.of.seats"/></th>
            <th><fmt:message key="order.make.order"/></th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td><select class="btn btn-info dropdown-toggle" name="dispatch_station">
                <c:forEach items="${station_list}" var="stationList">
                    <option value="${stationList.stationId}"><c:out value="${stationList.station}"/></option>
                </c:forEach>
            </select></td>
            <td><select class="btn btn-info dropdown-toggle" name="arrival_station">
                <c:forEach items="${station_list}" var="stationList">
                    <option value="${stationList.stationId}"><c:out value="${stationList.station}"/></option>
                </c:forEach>
            </select></td>
            <td><select class="btn btn-info dropdown-toggle" name="car_type">
                <c:forEach items="${carTypeList}" var="car_type">
                    <option value="${car_type}"><c:out value="${car_type}"/></option>
                </c:forEach>
            </select></td>
            <td><input class="form-control" name="count_of_seats"></td>
            <td>
                <input type="hidden" name="routs_id" value="${routs_id}">
                <input type="submit" class="btn btn-success" name="add_order"
                       value="<fmt:message key="order.make.order"/>">
            </td>
        </tr>
    </table>
</form>

<form action="selection_of_the_route_for_ordering" method="GET">
    <input type="hidden" name="departure_station" value="${departure_station}">
    <input type="hidden" name="arrival_station" value="${arrival_station}">
    <input type="hidden" name="departure_date" value="${departure_date}">
    <input type="submit" class="btn btn-primary" value="<fmt:message key="back"/>">
</form>
</body>
</html>
