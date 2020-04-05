<%@ page import="ua.nure.huzhyn.exception.ServiceException" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>General error pge</title>
</head>
<body>
<center>
    <h1>Something went wrong, please try again later</h1>
    HTTP STATUS CODE: <%out.print(((ServiceException)exception).getStackTrace());%>
</center>
</body>
</html>
