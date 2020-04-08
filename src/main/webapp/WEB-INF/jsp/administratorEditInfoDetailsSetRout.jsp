<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="lang"/>
<html>
<head>
    <title><fmt:message key="admin.editInformation"/></title>
</head>
<body>
<table border="1">
    <tr>
        <th><fmt:message key="order"/></th>
        <th><fmt:message key="station.name"/></th>
        <th><fmt:message key="arrivalDate"/></th>
        <th><fmt:message key="dispatchDate"/></th>
    </tr>
    <tr>
        <form action="administrator_edit_info_details_set_rout" method="POST">
            <input type="hidden" name="routs_id" value="${routs_id}">
            <input type="hidden" name="station_current_id" value="${station_id}">
            <td><input name="station_order" value="${current_rout.order}"></td>
            <td><select name="station_station">
                <c:set var="station_id" value="${current_rout.stationId}"/>
                <c:forEach items="${station_list}" var="station">
                    <option
                            <c:choose>
                                <c:when test="${station.stationId eq station_id}">
                                    selected
                                </c:when>
                            </c:choose>
                            value="${station.stationId}"><c:out value="${station.station}"/>
                    </option>
                    >
                </c:forEach>
            </select>
            </td>
            <td><input name="station_arrival_date" type="datetime-local" value="${current_rout.stationArrivalDate}"></td>
            <td><input name="station_dispatch_data" type="datetime-local" value="${current_rout.stationDispatchData}"></td>
            <td>
                <input type="hidden" name="routs_id" value="${routs_id}">
                <input type="hidden" name="station_current_id" value="${station_id}">
                <input type="submit" name="save_edit_information" value="<fmt:message key="admin.saveInformation"/>">
            </td>
        </form>
    </tr>
</table>
<form action="administrator_details_set_rout" method="GET">
    <input type="hidden" name="routs_id" value="${routs_id}">
    <input type="submit"  value="<fmt:message key="back"/>">
</form>
</body>
</html>

