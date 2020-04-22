<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="lang"/>
<html>
<head>
    <title><fmt:message key="admin.addRout"/></title>
</head>
<body>
<div align="right">
    <fmt:message key="enterRole"></fmt:message>
    <mrt:role role="${user.role}"></mrt:role>
</div>
<form action="administrator_add_rout" method="POST">
    <table border="1">
        <tr>
            <th><fmt:message key="rout.name"/></th>
            <th><fmt:message key="rout.number"/></th>
            <th><fmt:message key="train.number"/></th>
        </tr>
        <tr>
            <td><input name="rout_name"></td>
            <td><input name="rout_number"></td>
            <td><select name="train_number">
                <c:forEach items="${trainList}" var="trainList">
                    <option value="${trainList.trainId}"><c:out value="${trainList.trainNumber}"/></option>
                </c:forEach>
            </select></td>

            <td>
                <input type="submit" name="add_rout" value="<fmt:message key="admin.addRout"/>">
            </td>
        </tr>
    </table>
</form>
<form action="administrator_account" method="GET">
    <input type="submit" value="<fmt:message key="back"/>">
</form>
</body>
</html>
