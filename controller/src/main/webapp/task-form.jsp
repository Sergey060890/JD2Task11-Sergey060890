
<%@ page import="courses.entity.Task" %>
<%@ page import="java.util.Objects" %>
<%@ page import="DTO.TaskDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="courses.entity.Course" %><%--
  Created by IntelliJ IDEA.
  User: akyna
  Date: 14.05.2022
  Time: 12:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Задание</title>
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
    <h2>Добавление задания</h2>
    <form name="add" method="post" action="task" autocomplete="off">
        Описание задания: <label>
        <input name="description" type="text" required placeholder="Описание">
    </label> <br/>
        <input name="action" type="hidden" value="add">
        <button class="gradient-button1">Сохранить</button>
    </form>



    <% } else if (Objects.equals(request.getParameter("action"), "update")) {%>
    <h2>Редактирование задания</h2>
    <form name="toUpdate" method="post" action="task" autocomplete="off">
        <input name="id" type="hidden" value="<%=Integer.parseInt(request.getParameter("id"))%>">
        Описание: <label>
        <input name="description" type="text" value="<%=request.getParameter("description")%>">
    </label> <br/>
        <input name="action" type="hidden" value="update">
        <button class="gradient-button1">Сохранить</button>

    </form>

    <% } %>
</center>


<center>
    <a class="gradient-button1" href="index.jsp">Главная страница</a>

    <a class="gradient-button1" href="task.jsp">Назад</a>

</center>


</body>
</html>
