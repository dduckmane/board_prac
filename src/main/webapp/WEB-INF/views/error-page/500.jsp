<%--
  Created by IntelliJ IDEA.
  User: minsungkim
  Date: 2022/12/25
  Time: 12:08 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <c:forEach var="error" items="${errors}">
        ${error}
    </c:forEach>
</body>
</html>
