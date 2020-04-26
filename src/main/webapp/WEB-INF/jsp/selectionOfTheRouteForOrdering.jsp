<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="true" %>
<%@ taglib prefix="period" uri="/WEB-INF/tags/custom.tld" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="lang"/>
<html>
<head>
    <title><fmt:message key="rout.search"/></title>
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
<table class="table table-bordered table-hover text-center" border="1" style="width: auto">
    <thead class="thead-light text-center">
    <tr>
        <th><fmt:message key="rout.name"/></th>
        <th><fmt:message key="rout.number"/></th>
        <th><fmt:message key="train.number"/></th>
        <th><fmt:message key="dispatchDate"/></th>
        <th><fmt:message key="order.travel.time"/></th>
        <th><fmt:message key="arrivalDate"/></th>
        <th><fmt:message key="compartment.count"/></th>
        <th><fmt:message key="price.compartment"/></th>
        <th><fmt:message key="reserved.count"/></th>
        <th><fmt:message key="price.reserved"/></th>
        <th><fmt:message key="common.count"/></th>
        <th><fmt:message key="price.common"/></th>
        <th><fmt:message key="details"/></th>
        <th><fmt:message key="order"/></th>
    </tr>
    </thead>
    <tbody class="text-center">
    <c:forEach items="${rout_list}" var="rout">
        <tr>
            <c:set var="dispatchTime" value="${rout.stations.get(0).stationDispatchData}"/>
            <c:set var="arrivalTime" value="${rout.stations.get(rout.stations.size() - 1).stationArrivalDate}"/>
            <td>${rout.routName}</td>
            <td>${rout.routNumber}</td>
            <td>${rout.trainNumber}</td>
            <td>${departure_station} - ${dispatchTime}</td>
            <td><period:period dateFrom="${dispatchTime}" dateTo="${arrivalTime}"/></td>
            <td>${arrival_station} - ${arrivalTime}</td>
            <td>${rout.commonFreeSeatsCount}</td>
            <td>${carTypeList.get(0).price}</td>
            <td>${rout.compartmentFreeSeatsCount}</td>
            <td>${carTypeList.get(1).price}</td>
            <td>${rout.reservedFreeSeatsCount}</td>
            <td>${carTypeList.get(2).price}</td>
            <td>
                <form action="detail_rout" method="GET">
                    <input type="hidden" name="routs_id" value="${rout.routsId}">
                    <input type="hidden" name="departure_station" value="${departure_station}">
                    <input type="hidden" name="arrival_station" value="${arrival_station}">
                    <input type="hidden" name="departure_date" value="${departure_date}">
                    <input type="submit" class="btn btn-info" name="oder" value="<fmt:message key="details"/>">
                </form>
            </td>
            <td>
                <form action="make_order" method="GET">
                    <input type="hidden" name="routs_id" value="${rout.routsId}">
                    <input type="hidden" name="train_id" value="${rout.trainId}">
                    <input type="hidden" name="departure_station" value="${departure_station}">
                    <input type="hidden" name="arrival_station" value="${arrival_station}">
                    <input type="hidden" name="departure_date" value="${departure_date}">
                    <input type="submit" class="btn btn-info" name="oder" value="<fmt:message key="order"/>">
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<form action="home" method="GET">
    <input type="submit" class="btn btn-primary" value="<fmt:message key="back"/>">
</form>
</body>
</html>
