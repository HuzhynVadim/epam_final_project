<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mrt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="lang"/>
<html>
<body>
<form action="administrator_set_rout" method="POST">
    <table border="1">
        <tr>
            <th><fmt:message key="order"/></th>
            <th><fmt:message key="station.name"/></th>
            <th><fmt:message key="arrivalDate"/></th>
            <th><fmt:message key="dispatchDate"/></th>
            <th><fmt:message key="delete"/></th>
        </tr>
        <tr>
            <td><input name="station_order" type=""></td>
            <td><select name="station_station">
                <c:forEach items="${stationList}" var="station">
                    <option value="${station.stationId}"><c:out value="${station.station}"/></option>
                </c:forEach>
            </select></td>
            <td><input name="station_arrival_date" type="datetime-local"></td>
            <td><input name="station_dispatch_date" type="datetime-local"></td>
            <td>
                <form action="rout_delete" method="POST">
                    <input type="hidden" name="station_mapping" value="${station_mapping.routsId}">
                    <input type="submit" name="remove_rout_mapping" value="<fmt:message key="admin.remove"/>">
                </form>
            </td>
            <td>
                <input type="submit" name="add_station_mapping" value="<fmt:message key="addMore"/>">
            </td>
        </tr>
    </table>
    <p>
    <td>
        <input type="submit" name="add_new_station_mapping" value="<fmt:message key="confirm"/>">
    </td>
    </p>
</form>
<p>
<form action="administrator_account" method="GET">
    <input type="submit" value="<fmt:message key="back"/>">
</form>
</p>
</body>
</html>