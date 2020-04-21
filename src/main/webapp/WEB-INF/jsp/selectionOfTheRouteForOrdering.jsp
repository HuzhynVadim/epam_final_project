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
        <th>nameRout</th>
        <th>numberRout</th>
        <th>trainNumber</th>
        <th>dateDispatch</th>
        <th>travelTime</th>
        <th>dateArrival</th>
        <th>countCompartment</th>
        <th>countReserved</th>
        <th>countCommon</th>
        <th>detail</th>
        <th>order</th>
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
            <td>${rout.compartmentFreeSeatsCount}</td>
            <td>${rout.reservedFreeSeatsCount}</td>
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
