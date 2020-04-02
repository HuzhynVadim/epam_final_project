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
        <th><fmt:message key="station"/></th>
        <th><fmt:message key="admin.editInformation"/></th>
    </tr>
    <tr>
        <form action="administrator_edit_info_station" method="POST">
            <input type="hidden" name="station_id" value="${current_station.stationId}">
            <td><input name="station" value="${current_station.station}"></td>
            <td>
                <input type="submit" name="save_edit_information" value="<fmt:message key="admin.saveInformation"/>">
            </td>
        </form>
    </tr>
</table>
<form action="administrator_account" method="GET">
    <input type="submit" value="<fmt:message key="back"/>">
</form>
</body>
</html>
