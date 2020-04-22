<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="lang"/>
<html>
<head>
    <title><fmt:message key="admin.addStation"/></title>
</head>
<body>
<div align="right">
    <fmt:message key="enterRole"></fmt:message>
    <mrt:role role="${user.role}"></mrt:role>
</div>
<form action="administrator_add_station" method="POST">
    <table border="1">
        <tr>
            <th><fmt:message key="station"/></th>
        </tr>
        <tr>
            <td><input name="station_station"></td>
            <td>
                <input type="submit" name="add_station" value="<fmt:message key="admin.addStation"/>">
            </td>
        </tr>
    </table>
</form>
<form action="administrator_account" method="GET">
    <input type="submit" value="<fmt:message key="back"/>">
</form>
</body>
</html>
