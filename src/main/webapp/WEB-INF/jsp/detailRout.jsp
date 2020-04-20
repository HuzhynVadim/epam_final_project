<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mrt" %>
<%@ taglib prefix="period" uri="/WEB-INF/tags/custom.tld" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="lang"/>
<html>
<body>
<table border="1">
    <tr>
        <th><fmt:message key="order"/></th>
        <th><fmt:message key="station.name"/></th>
        <th><fmt:message key="arrivalDate"/></th>
        <th><fmt:message key="dispatchDate"/></th>
        <th><fmt:message key="parkingTime"/></th>
    </tr>
    <c:forEach items="${rout_m_list}" var="item">
        <tr>
            <td>${item.order}</td>
            <td>${item.station}</td>
            <td>${item.stationArrivalDate}</td>
            <td>${item.stationDispatchData}</td>
            <td><period:period dateFrom="${item.stationArrivalDate}" dateTo="${item.stationDispatchData}"/></td>
        </tr>
    </c:forEach>
</table>
<p>
<form action="selection_of_the_route_for_ordering" method="GET">
    <input type="hidden" name="departure_station" value="${departure_station}">
    <input type="hidden" name="arrival_station" value="${arrival_station}">
    <input type="hidden" name="departure_date" value="${departure_date}">
    <input type="submit" value="<fmt:message key="back"/>">
</form>
</p>
</body>
</html>