<%@ page import="courses.entity.Student" %>
<%@ page import="java.util.Objects" %><%--
  Created by IntelliJ IDEA.
  User: akyna
  Date: 14.05.2022
  Time: 12:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cтудент</title>
    <link href="css/button-small.css" rel="stylesheet">
    <link href="css/button.css" rel="stylesheet">

    <style>
        h2 {
            font-size: xxx-large;
            font-family: Arial;
        }

        caption {
            font-family: Arial;
            font-size: xx-large;
        }

        td {
            font-size: xx-large;
            font-family: Arial;
        }

        tr {
            font-size: xx-large;
            font-family: Arial;
        }

        body {
            background: linear-gradient(to top left, powderblue, cornflowerblue);
            color: darkblue;
            background-size: cover;
        }

        table {
            border: darkblue;
        }

    </style>
<body>

<center>
    <% if (request.getParameter("id") == null) { %>
    <h2>Добавление студента</h2>
    <form name="add" method="post" action="student" autocomplete="off">
        Имя: <label>
        <input name="name" type="text" required placeholder="Имя">
    </label> <br/>
        Фамилия: <label>
        <input name="surname" type="text" required placeholder="Фамилия">
    </label><br/>
        <input name="action" type="hidden" value="add">
        <button class="gradient-button1">Сохранить</button>
    </form>
    <% } else if (Objects.equals(request.getParameter("action"), "update")) {%>
    <h2>Редактирование данных студента</h2>
    <form name="toUpdate" method="post" action="student" autocomplete="off">
        <input name="id" type="hidden" value="<%=Integer.parseInt(request.getParameter("id"))%>">
        Имя: <label>
        <input name="name" type="text" value="<%=request.getParameter("name")%>">
    </label> <br/>
        Фамилия: <label>
        <input name="surname" type="text" value="<%=request.getParameter("surname")%>">
    </label><br/>
        <input name="action" type="hidden" value="update">
        <button class="gradient-button1">Сохранить</button>
    </form>
    <% }
        if (Objects.equals(request.getParameter("action"), "delete")) {%>
    <h2>Удаление студента</h2>
    <form name="toDelete" method="post" action="student" autocomplete="off">
        <input name="id" type="hidden" value="<%=Integer.parseInt(request.getParameter("id"))%>">
        Имя: <label>
        <input name="name" type="text" value="<%=request.getParameter("name")%>">
    </label> <br/>
        Фамилия: <label>
        <input name="surname" type="text" value="<%=request.getParameter("surname")%>">
    </label><br/>
        <input name="action" type="hidden" value="delete">
        <button class="gradient-button1">Удалить</button>
    </form>
    <% } %>
</center>


<center>

    <a class="gradient-button1" href="index.jsp">Главная страница</a>

    <a class="gradient-button1" href="student">Назад</a>

</center>


</body>
</html>
