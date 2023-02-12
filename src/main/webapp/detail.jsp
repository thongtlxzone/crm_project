<%@ page isELIgnored="false" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>

    </head>

    <body>
        <b> Hello </b>
        ${username}
        ${password}
        <c:if test = "${username == 'cybersoft' && password == 'admin@123'}">
            <b><c:out value="Hello JSP" /> </b>
        </c:if>
    </body>
</html>