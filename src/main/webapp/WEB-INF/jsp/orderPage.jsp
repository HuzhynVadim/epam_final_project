<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="lang"/>
<html>
<head>
    <title><fmt:message key="user.makeOrder"/></title>
</head>
<body>
<form action="make_order" method="POST">
    <table border="1">
        <tr>
            <th><fmt:message key="station.dispatch"/></th>
            <th><fmt:message key="station.arrival"/></th>
            <th><fmt:message key="car.type"/></th>
            <th><fmt:message key="count.of.seats"/></th>
        </tr>
        <tr>
            <td><select name="dispatch_station">
                <c:forEach items="${station_list}" var="stationList">
                    <option value="${stationList.stationId}"><c:out value="${stationList.station}"/></option>
                </c:forEach>
                </select></td>
            <td><select name="arrival_station">
                <c:forEach items="${station_list}" var="stationList">
                    <option value="${stationList.stationId}"><c:out value="${stationList.station}"/></option>
                </c:forEach>
                </select></td>
            <td><select name="car_type">
                <c:forEach items="${carTypeList}" var="car_type">
                    <option value="${car_type}"><c:out value="${car_type}"/></option>
                </c:forEach>
            </select></td>
            <td><input name="count_of_seats"></td>

            <td>
                <input type="submit" name="add_order" value="<fmt:message key="order.make.order"/>">
                <input type="hidden" name="routs_id" value="${routs_id}">
            </td>
        </tr>
    </table>
</form>

<form action="selection_of_the_route_for_ordering" method="GET">
    <input type="hidden" name="departure_station" value="${departure_station}">
    <input type="hidden" name="arrival_station" value="${arrival_station}">
    <input type="hidden" name="departure_date" value="${departure_date}">
    <input type="submit" value="<fmt:message key="back"/>">
</form>
</body>
</html>
