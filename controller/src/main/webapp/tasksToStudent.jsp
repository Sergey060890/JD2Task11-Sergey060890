<%@ page import="courses.entity.Student" %>
<%@ page import="java.util.List" %>
<%@ page import="courses.entity.Task" %><%--
  Created by IntelliJ IDEA.
  User: Alex Mikhalevich
  Date: 19.05.2022
  Time: 16:21  
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
    <h2>Управление назначением заданий студентам</h2>
    <table border="3" width="50%">

        <caption><b>Список студентов</b></caption>
        <tr>
            <th>Имя</th>
            <th>Фамилия</th>
            <th>Действия</th>
        </tr>

        <% List<Student> studentList = (List<Student>) request.getAttribute("students");
            for (Student student : studentList) {
        %>
        <tr>
            <td><%= student.getName()%>
            </td>
            <td><%= student.getSurname()%>
            </td>
            <td>
                <% Task task = (Task) request.getAttribute("taskToAssign");%>
                <form name="toEnroll" method="post" action="task">
                    <input name="id" type="hidden" value="<%=task.getId()%>">
                    <input name="idStudent" type="hidden" value="<%=student.getId()%>">
                    <input name="action" type="hidden" value="addTaskToStudent">
                    <button class="gradient-button">Добавить задание студенту</button>
                </form>
                <form name="cancelEnroll" method="post" action="task">
                    <% if (student.getTasks().contains(task)) { %>
                    <input name="id" type="hidden" value="<%=task.getId()%>">
                    <input name="idStudent" type="hidden" value="<%=student.getId()%>">
                    <input name="action" type="hidden" value="removeTaskFromStudent">
                    <button class="gradient-button">Отменить задание студенту</button>
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