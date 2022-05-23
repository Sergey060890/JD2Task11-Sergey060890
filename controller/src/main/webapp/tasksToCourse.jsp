<%@ page import="courses.entity.Course" %>
<%@ page import="java.util.List" %>
<%@ page import="courses.entity.Task" %><%--
  Created by IntelliJ IDEA.
  User: Alex Mikhalevich
  Date: 19.05.2022
  Time: 16:11  
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Страница курсов</title>
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
    <h2>Управление назначением заданий на курсы</h2>
    <table border="3" width="50%">

        <caption><b>Список курсов</b></caption>
        <tr>
            <th>Описание</th>
            <th>Часы</th>
            <th>Действия</th>
        </tr>

        <% List<Course> courseList = (List<Course>) request.getAttribute("courses");
            for (Course course : courseList) {
        %>
        <tr>
            <td><%= course.getDescription()%>
            </td>
            <td><%= course.getHours()%>
            </td>
            <td>
                <% Task task = (Task) request.getAttribute("taskToAssign");%>
                <form name="toAdd" method="post" action="task">
                    <% if (!course.getTasks().contains(task)) { %>
                    <input name="id" type="hidden" value="<%=task.getId()%>">
                    <input name="idCourse" type="hidden" value="<%=course.getId()%>">
                    <input name="action" type="hidden" value="addTaskToCourse">
                    <button class="gradient-button">Добавить задание на данный курс</button>
                    <% } else { %>
                </form>
                <form name="toCancel" method="post" action="task">
                    <input name="id" type="hidden" value="<%=task.getId()%>">
                    <input name="idCourse" type="hidden" value="<%=course.getId()%>">
                    <input name="action" type="hidden" value="cancelTaskToCourse">
                    <button class="gradient-button">Удалить задание с данного курса</button>
                    <% } %>
                </form>
            </td>
        </tr>
        <%
            }
        %>
    </table>
    <a class="gradient-button1" href="index.jsp">Главная страница</a>

    <a class="gradient-button1" href="teacher">Назад</a>
</center>
</body>
</html>
