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
<div align="right">
    <fmt:message key="enterRole"></fmt:message>
    <mrt:role role="${user.role}"></mrt:role>
</div>
<table border="1">
    <tr>
        <th><fmt:message key="rout.name"/></th>
        <th><fmt:message key="rout.number"/></th>
        <th><fmt:message key="train.number"/></th>
        <th><fmt:message key="admin.editInformation"/></th>
    </tr>
    <tr>
        <form action="administrator_edit_info_rout" method="POST">
            <input type="hidden" name="routs_id" value="${current_rout.routsId}">
            <td><input name="rout_name" value="${current_rout.routName}"></td>
            <td><input name="rout_number" value="${current_rout.routNumber}"></td>
            <td><select name="train_number">
                <c:set var="train_id" value="${current_rout.trainId}"/>
                <c:forEach items="${trainList}" var="train">
                    <option
                            <c:choose>
                                <c:when test="${train.trainId eq train_id}">
                                    selected
                                </c:when>
                            </c:choose>
                            value="${train.trainId}"><c:out value="${train.trainNumber}"/>
                    </option>
                </c:forEach>
            </select>
            </td>
            <td>
                <input type="hidden" name="routs_id" value="${current_rout.routsId}">
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
