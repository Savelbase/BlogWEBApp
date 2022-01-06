<%--
  Created by IntelliJ IDEA.
  User: slava
  Date: 09.12.2021
  Time: 13:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/water.css@2/out/water.css">
</head>
<body>
<form method="post">
    <label for="firstName" >
        First Name :
        <input type="text" name="firstName" id="firstName">
    </label>
    <label for="lastName" >
        Last Name :
        <input type="text" name="lastName" id="lastName">
    </label>
    <label for="login" >
        Login :
        <input type="text" name="login" id="login">
    </label>
    <label for="login" >
        Password :
        <input type="password" name="password" id="password">
    </label>
    <input type="submit" name="Save">
</form>
</body>
</html>
