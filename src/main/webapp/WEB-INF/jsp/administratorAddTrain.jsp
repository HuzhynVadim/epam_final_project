<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="lang"/>
<html>
<head>
    <title><fmt:message key="admin.addTrain"/></title>
</head>
<body>
<div align="right">
    <fmt:message key="enterRole"></fmt:message>
    <mrt:role role="${user.role}"></mrt:role>
</div>
<form action="administrator_add_train" method="POST">
    <table border="1">
        <tr>
            <th><fmt:message key="train.number"/></th>
        </tr>
        <tr>
            <td><input name="train_number"></td>
            <td>
                <input type="submit" name="add_train" value="<fmt:message key="admin.addTrain"/>">
            </td>
        </tr>
    </table>
</form>
<form action="administrator_account" method="GET">
    <input type="submit" value="<fmt:message key="back"/>">
</form>
</body>
</html>
