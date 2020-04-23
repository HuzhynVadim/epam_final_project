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
</head>
<body>
<table border="1">
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
                    <input type="submit" name="oder" value="<fmt:message key="details"/>">
                </form>
            </td>
            <td>
                <form action="make_order" method="GET">
                    <input type="hidden" name="routs_id" value="${rout.routsId}">
                    <input type="hidden" name="train_id" value="${rout.trainId}">
                    <input type="hidden" name="departure_station" value="${departure_station}">
                    <input type="hidden" name="arrival_station" value="${arrival_station}">
                    <input type="hidden" name="departure_date" value="${departure_date}">
                    <input type="submit" name="oder" value="<fmt:message key="order"/>">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<form action="home" method="GET">
    <input type="submit" value="<fmt:message key="back"/>">
</form>
</body>
</html>
