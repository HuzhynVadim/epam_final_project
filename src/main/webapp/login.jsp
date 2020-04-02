<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="lang"/>
<html>
<head>
    <title><fmt:message key="signIn"/></title>
</head>
<body>
<center>
<form action="login" method="POST">
    <p><b><fmt:message key="user.email"/></b><br>
        <input name="login" type="text" size="20">
    </p>
    <p><b><fmt:message key="user.password"/></b><br>
        <input name="password" type="password" size="20">
    </p>
    <p><br>
        <input type="submit" name="signIn" value="<fmt:message key="signIn"/>">
    </p>
</form>
    <form action="registration" method="POST">
            <nav>
                <a href="registration.jsp"><fmt:message key="registration"/></a>
            </nav>
    </form>
    <form action="change_language" method="POST">
        <input type="submit" name="lang" value="en">
        <input type="submit" name="lang" value="ru">
    </form>
</center>
</body>
</html>