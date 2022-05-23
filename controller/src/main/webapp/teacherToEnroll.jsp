<%@ page import="java.util.List" %>
<%@ page import="courses.entity.Teacher" %>
<%@ page import="courses.entity.Course" %><%--
  Created by IntelliJ IDEA.
  User: akyna
  Date: 19.05.2022
  Time: 00:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Управление учителями</title>
</head>
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
    <h2>Управление закреплением учителей за курсами</h2>
    <table border="3" width="50%">

        <caption><b>Список курсов</b></caption>
        <tr>
            <th>Описание</th>
            <th>Часы</th>
            <th>Действия</th>
        </tr>

        <% List<Teacher> teacherList = (List<Teacher>) request.getAttribute("listTeachers");
            for (Teacher teacher : teacherList) {
        %>
        <tr>
            <td><%= teacher.getName()%>
            </td>
            <td><%= teacher.getSurname()%>
            </td>
            <td><% Course course = (Course) request.getAttribute("courseEnroll");%>
                <form name="toEnroll" method="post" action="admin">
                    <% if (!teacher.getCourses().contains(course)) { %>
                    <input name="id" type="hidden" value="<%=course.getId()%>">
                    <input name="idTeacher" type="hidden" value="<%=teacher.getId()%>">
                    <input name="action" type="hidden" value="teacherToEnroll">
                    <button class="gradient-button">Добавить на курс</button>
                    <% } else { %>
                </form>
                <form name="cancelEnroll" method="post" action="admin">
                    <input name="id" type="hidden" value="<%=course.getId()%>">
                    <input name="idTeacher" type="hidden" value="<%=teacher.getId()%>">
                    <input name="action" type="hidden" value="cancelToEnroll">
                    <button class="gradient-button">Удалить с курса</button>
                    <% } %>
                </form>
            </td>
        </tr>
        <%
            }
        %>
    </table>
    <a class="gradient-button1" href="index.jsp">Главная страница</a>

    <a class="gradient-button1" href="admin">Назад</a>
</center>
</body>
</html>
