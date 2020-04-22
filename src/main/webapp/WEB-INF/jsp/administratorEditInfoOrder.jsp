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
        <th><fmt:message key="order.status"/></th>
        <th><fmt:message key="admin.editInformation"/></th>
    </tr>
    <tr>
        <form action="administrator_edit_info_order" method="POST">
            <input type="hidden" name="order_id" value="${current_order.orderId}">
            <td><select name="order_status">
                <c:set var="current_order_status" value="${current_order.orderStatus}"/>
                <c:forEach items="${statusList}" var="status">
                    <option
                            <c:choose>
                                <c:when test="${status eq current_order_status}">
                                    selected
                                </c:when>
                            </c:choose>
                            value="${status}"><c:out value="${status}"/>
                    </option>
                </c:forEach>
            </select></td>
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
