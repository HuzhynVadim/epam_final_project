<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="lang"/>

<html>
<head>
    <title><fmt:message key="signIn"/></title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link href="css/login.css" rel="stylesheet">
</head>
<body class="text-center">
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
        integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>
<div style="text-align: center;">
    <div class="form-signin">
        <form action="login" method="POST">
            <img class="mb-4" src="https://image.flaticon.com/icons/svg/1974/1974098.svg" alt="" width="72"
                 height="72">
            <h1 class="h3 mb-3 font-weight-normal"><fmt:message key="user.signin"/></h1>
            <label for="inputEmail" class="sr-only"><fmt:message key="user.email"/></label>
            <input name="login" id="inputEmail" class="form-control" placeholder="<fmt:message key="user.email"/>"
                   type="email" size="20">
            </p>
            <label for="inputPassword" class="sr-only"><fmt:message key="user.password"/></label>
            <input name="password" id="inputPassword" class="form-control"
                   placeholder="<fmt:message key="user.password"/>"
                   type="password" size="20">
            </p>
            <p><br>
                <input type="submit" class="btn btn-primary btn-block" name="signIn"
                       value="<fmt:message key="signIn"/>">
            </p>
        </form>
        <form action="registration" method="POST">
            <nav>
                <a href="registration.jsp" class="btn btn-primary btn-block"><fmt:message key="registration"/></a>
            </nav>
        </form>
    </div>
    <div class="btn-group btn-group-sm" role="group" aria-label="Basic example">
        <form action="change_language" method="POST">
            <input type="submit" class="btn btn-secondary" name="lang" value="en">
            <input type="submit" class="btn btn-secondary" name="lang" value="ru">
        </form>
    </div>
    <p class="mt-5 mb-3 text-muted">&copy; 2020</p>
</div>
</body>
</html>