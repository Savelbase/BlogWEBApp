<%@ page import="org.itstep.domain.User" %><%--
  Created by IntelliJ IDEA.
  User: slava
  Date: 03.12.2021
  Time: 18:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/water.css@2/out/water.css">
</head>
<body>
<h1>Административная панель</h1>
<c:choose>
    <c:when test="${sessionScope.get('admin') != null}">
        <c:set var="user" value='<%=request.getSession().getAttribute("admin")%>' scope="session"/>
        <p>Name : ${sessionScope.get('admin').getFirstName()}</p>
        <form method="post">
            <input name="add" value="add" hidden style="display: none">
            <input type="submit" value="Add new post">
        </form>
    </c:when>
    <c:otherwise>
        <form method="post">Login
            <input type="text" name="login" required>Password
            <input type="password" name="password" required >
            <input type="submit" value="Sign In">
        </form>
        <form method="post">
            if you dont have an account
            <input hidden name="signup" value="signup" style="display: none">
            <input type="submit" value="Sign Up" >

        </form>
    </c:otherwise>
</c:choose>
</body>
</html>
