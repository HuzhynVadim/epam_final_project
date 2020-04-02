<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="lang"/>
<html>
<head>
    <title><fmt:message key="registration"/></title>
</head>
<body>
<center>
    <form action="registration" method="POST">
    <p><b><fmt:message key="user.email"/></b><br>
        <input name="email"  type="text">
    </p>
    <p><b><fmt:message key="user.password"/></b><br>
        <input name="password" type="password">
    </p>
    <p><b><fmt:message key="user.first_name"/></b><br>
        <input name="first_name" type="text">
    </p>
    <p><b><fmt:message key="user.last_name"/></b><br>
        <input name="last_name" type="text">
    </p>
    <p><b><fmt:message key="user.birth_date"/></b><br>
        <input name="birth_date" type="date">
    </p>
    <p><b><fmt:message key="user.phone"/></b><br>
        <input name="phone" type="text">
    </p>
    <p><b></b><br>
        <input type="submit" name="registration" value="<fmt:message key="register"/>">
    </p>
    </form>
</center>
<form action="login.jsp">
    <input type="submit" value="<fmt:message key="back"/>">
</form>
</body>
</html>