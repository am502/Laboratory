<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title></title>
</head>
<body>

<c:choose>
    <c:when test="${flag}">
        <H1><c:out value="${message}"/> </H1>
    </c:when>
    <c:otherwise>
        <H2>Error</H2>
    </c:otherwise>
</c:choose>

</body>
</html>