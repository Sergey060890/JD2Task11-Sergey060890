<%@ page import="java.util.List" %>
<%@ page import="DTO.CourseDTO" %>
<%@ page import="java.util.Objects" %><%--
  Created by IntelliJ IDEA.
  User: Lidia
  Date: 19.05.2022
  Time: 02:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Teacher Page</title>
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
</head>
<body>

<center>
<div style="text-align: center;">
    <h2>Сервисы Учителя</h2>

    <form name="search" method="post" action="teacherService">
        Введите фамилию учителя: <label>
        <input type="text" name="teacherSurname" required placeholder="Фамилия" size="45">
    </label>
        <input name="action" type="hidden" value="search">
        <button>Найти</button>
    </form>
    <center>
    <table border="3" width="500">
        <caption><h3>Таблица Курсов и Учитилей</h3></caption>
        <th>№</th>
        <th>Курсы</th>
        <th>Часы</th>
        <th>Фамилия учителя</th>
        <th>Имя учителя</th>
        <th>Действия</th>

        <% List<CourseDTO> listOfCourse =
                (List<CourseDTO>) request.getAttribute("listOfAllCourses");
            int i = 1;
            for (CourseDTO course : listOfCourse) {
        %>
        <tr>
            <td><%= i++%>
            </td>
            <td><%= course.getDescription()%>
            </td>
            <td><%= course.getHours()%>
            </td>
            <td><%= course.getTeacherSurname()%>
            </td>
            <td><%= course.getTeacherName()%>
            </td>
            <td>
                <form name="delete" method="post" action="teacher">
                    <input name="id" type="hidden" value="<%=course.getId()%>">
                    <button class="gradient-button"><a
                            href="teacher-form.jsp?id=<%=course.getId()%>&surname=<%=course.getTeacherName()%>&name=<%=course.getTeacherName()%>&action=delete"
                    >Удалить</a></button>
                </form>

                <form name="edit" method="post" action="teacher">
                    <input name="id" type="hidden" value="<%=course.getId()%>">
                    <button class="gradient-button"><a
                            href="teacher-form.jsp?id=<%=course.getId()%>&surname=<%=course.getTeacherSurname()%>&name=<%=course.getTeacherName()%>&action=update"
                    >Редактировать</a></button>
                </form>
                <form name="assignTask" action="task">
                    <input name="action" type="hidden" value="task.jsp">
                    <button class="gradient-button">Управление заданиями</button>
                </form>
            </td>
        </tr>
        <%
            }
        %>
    </table>
    </center>
    <a class="gradient-button1" href="task.jsp">Управление Задачами</a>
    <a class="gradient-button1" href="index.jsp">Главная страница</a>
    <a class="gradient-button1" href="teacherStudent.jsp">Работа со студентами</a>

</div>
</center>
</body>
</html>
