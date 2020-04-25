<%@ page import="ua.nure.huzhyn.exception.ServiceException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage='true' %>

<html>
<head>
    <title>General error pge</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <style>
        body {
            background-color: #f5f5f5;
        }

        .ccc {
            display: flex;
            justify-content: center; /*Центрирование по горизонтали*/
            align-items: center;
        }
    </style>
</head>
<body>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
        integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>
<center>
    <center>
        <h1 class="h1 text-center ccc" style="height:100px; width: 100%">Something went wrong, please try again
            later</h1>
        HTTP STATUS CODE: <%out.print(((ServiceException) exception).getStackTrace());%>
        <%--    HTTP STATUS CODE: <%out.print(((ServiceException)exception).getHttpStatusCode());%>--%>
    </center>
</body>
</html>
