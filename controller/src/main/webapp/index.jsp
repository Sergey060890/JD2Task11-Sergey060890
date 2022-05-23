<%--
  Created by IntelliJ IDEA.
  User: akyna
  Date: 14.05.2022
  Time: 11:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Главная Страница</title>
</head>
<link href="css/button.css" rel="stylesheet">
<style>
    body {
        height: 200px;
        background: linear-gradient(to top left, powderblue, cornflowerblue);
        color: darkblue;
        background-size: cover;
    }

    h2 {
        font-size: xxx-large;
        font-family: Arial;
    }
</style>
<body>
<center>
    <h2>Главная страница</h2>

    <a class="gradient-button1" href="admin">Сервис администратора</a>

    <a class="gradient-button1" href="student">Сервис студентов</a>

    <a class="gradient-button1" href="teacherService">Сервис учителей</a>

    <a class="gradient-button1" href="task">Сервис заданий</a>
</center>
</body>
</html>
