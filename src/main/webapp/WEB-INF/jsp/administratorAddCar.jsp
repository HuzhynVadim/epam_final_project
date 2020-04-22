<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="lang"/>
<html>
<head>
    <title><fmt:message key="admin.addCar"/></title>
</head>
<body>
<div align="right">
    <fmt:message key="enterRole"></fmt:message>
    <mrt:role role="${user.role}"></mrt:role>
</div>
<form action="administrator_add_car" method="POST">
    <table border="1">
        <tr>
            <th><fmt:message key="train.number"/></th>
            <th><fmt:message key="car.type"/></th>
            <th><fmt:message key="car.number"/></th>
            <th><fmt:message key="car.seats"/></th>
        </tr>
        <tr>
            <td><select name="train_id">
                <c:forEach items="${trainList}" var="trainList">
                    <option value="${trainList.trainId}"><c:out value="${trainList.trainNumber}"/></option>
                </c:forEach>
            </select></td>
            <td><select name="car_type">
                <c:forEach items="${carTypeList}" var="car_type">
                    <option value="${car_type}"><c:out value="${car_type}"/></option>
                </c:forEach>
            </select></td>
            <td><input name="car_number"></td>
            <td><input name="seats"></td>
            <td>
                <input type="submit" name="add_car" value="<fmt:message key="admin.addCar"/>">
            </td>
        </tr>
    </table>
</form>
<form action="administrator_account" method="GET">
    <input type="submit" value="<fmt:message key="back"/>">
</form>
</body>
</html>
