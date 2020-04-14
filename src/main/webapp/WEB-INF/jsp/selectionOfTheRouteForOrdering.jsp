<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="true" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="lang"/>
<html>
<head>
    <title><fmt:message key="rout.search"/></title>
</head>
<body>
<table border="1">
    <tr>
        <th>nameRout</th>
        <th>numberRout</th>
        <th>numberCar</th>
        <th>dateArrival</th>
        <th>dateDispatch</th>
        <th>travelTime</th>
    </tr>
<%--    <c:forEach items="${}" var="">--%>
        <tr>
            <td> </td>
            <td> </td>
            <td> </td>
            <td> </td>
            <td> </td>
            <td> </td>
            <td>
                <form action="make_order" method="GET">
<%--                    <input type="hidden" name="" value="${}">--%>
                    <input type="submit" name="" value="<fmt:message key=""/>">
                </form>
            </td>
        </tr>
<%--    </c:forEach>--%>
</table>
<form action="home" method="GET">
    <input type="submit" value="<fmt:message key="back"/>">
</form>
</body>
</html>
