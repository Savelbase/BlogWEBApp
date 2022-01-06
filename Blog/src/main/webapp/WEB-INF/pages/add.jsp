<%--
  Created by IntelliJ IDEA.
  User: slava
  Date: 06.12.2021
  Time: 12:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/water.css@2/out/water.css">
</head>
<body>
<h1>Добавление нового поста</h1>
<form method="post" enctype="multipart/form-data">
    <label for="post-name">Post Name : <br>
        <input type="text" name="post-name" id="post-name"><br>
    </label>
    <label for="post-text">Post Text : <br>
        <input name="post-text" id="post-text" style="width: 300px; height: 120px"/><br>
    </label>Post Date : <br>
    <input type="date" name="date" id="postdate"/><br>
    Add Image : <br>
    <input type="file" name="image" id="image">
    <input type="submit" value="add">
</form>
</body>
</html>
