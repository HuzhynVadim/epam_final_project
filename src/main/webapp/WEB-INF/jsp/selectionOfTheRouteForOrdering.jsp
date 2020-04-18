<%@ page import="java.time.Period" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="true" %>
<%@ taglib prefix = "period" uri = "/WEB-INF/tags/custom.tld" %>

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
        <th>dateDispatch</th>
        <th>dateArrival</th>
        <th>travelTime</th>
        <th>detail</th>
        <th>order</th>
    </tr>
    <c:forEach items="${rout_list}" var="rout">
        <tr>
            <c:set var="dispatchTime" value="${rout.stations.get(0).stationDispatchData}"/>
            <c:set var="arrivalTime" value="${rout.stations.get(rout.stations.size() - 1).stationArrivalDate}"/>
            <td>${rout.routName}</td>
            <td>${rout.routNumber}</td>
            <td>${dispatchTime}</td>
            <td>${arrivalTime}</td>
            <td><period:period dateFrom="${dispatchTime}" dateTo="${arrivalTime}"/></td>
            <td>
                <form action="details_search_rout" method="GET">
                    <input type="submit" name="oder" value="<fmt:message key="details"/>">
                </form>
            </td>
            <td>
                <form action="make_order" method="GET">
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
