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
        <th><fmt:message key="train.number"/></th>
        <th><fmt:message key="car.type"/></th>
        <th><fmt:message key="car.number"/></th>
        <th><fmt:message key="car.seats"/></th>
    </tr>
    <tr>
        <form action="administrator_edit_info_car" method="POST">
            <input type="hidden" name="car_id" value="${current_car.carId}">
            <td><select name="train_id">
                <c:set var="train_id" value="${current_car.trainId}"/>
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
            <td><select name="car_type">
                <c:set var="train_id" value="${current_car.carType}"/>
                <c:forEach items="${carTypeList}" var="car_type">
                    <option
                            <c:choose>
                                <c:when test="${car_type eq current_car.carType}">
                                    selected
                                </c:when>
                            </c:choose>
                            value="${car_type}"><c:out value="${car_type}"/>
                    </option>
                </c:forEach>
            </select>
            </td>
            <td><input name="car_number" value="${current_car.carNumber}"></td>
            <td><input name="seats" value="${current_car.seats}"></td>
            <td>
                <input type="hidden" name="car_id" value="${current_car.carId}">
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
