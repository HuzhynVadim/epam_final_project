<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mrt" %>

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
        <th><fmt:message key="edit"/></th>
        <th><fmt:message key="delete"/></th>
    </tr>
    <c:forEach items="${rout_m_list}" var="item">
        <tr>

            <td>${item.order}</td>
            <td>${item.station}</td>
            <td>${item.stationArrivalDate}</td>
            <td>${item.stationDispatchData}</td>
            <td>
                <form action="administrator_edit_info_details_set_rout" method="GET">
                    <input type="hidden" name="routs_id" value="${item.routsId}">
                    <input type="submit" name="edit_info_rout_mapping"
                           value="<fmt:message key="admin.editInformation"/>">
                </form>
            </td>
            <td>
                <form action="remove_rout_mapping" method="POST">
                    <input type="hidden" name="routs_id" value="${item.routsId}">
                    <input type="hidden" name="station_id" value="${item.stationId}">
                    <input type="submit" name="remove_rout_to_station_mapping"
                           value="<fmt:message key="admin.remove"/>">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<form action="administrator_set_rout_mapping" method="GET">
    <input type="hidden" name="routs_id" value="${routs_id}">
    <input type="submit" name="add_rout_mapping" value="<fmt:message key="admin.addRout"/>">
</form>
<p>
<form action="administrator_account" method="GET">
    <input type="submit" value="<fmt:message key="back"/>">
</form>
</p>
</body>
</html>