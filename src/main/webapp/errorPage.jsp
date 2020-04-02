<%@ page import="ua.nure.huzhyn.exception.ServiceException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage='true' %>

<html>
<head>
    <title>Error page</title>
</head>
<body>

<center>
    <h1>
        HTTP STATUS CODE: <%out.print(((ServiceException)exception).getHttpStatusCode());%>
    </h1>
</center>

<br/>
<br/>
<br/>

<center>
    <h3>
        <%out.print(exception.getMessage());%>
    </h3>
</center>

</body>
</html>
