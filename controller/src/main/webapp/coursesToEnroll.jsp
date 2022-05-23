<%@ page import="courses.entity.Course" %>
<%@ page import="java.util.List" %>
<%@ page import="courses.entity.Student" %><%--
  Created by IntelliJ IDEA.
  User: Alex Mikhalevich
  Date: 17.05.2022
  Time: 11:46  
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <h2>Управление курсами</h2>
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
                <% Student student = (Student) request.getAttribute("studentToEnroll");%>
                <form name="toEnroll" method="post" action="student">
                    <% if (!student.getCourses().contains(course)) { %>
                    <input name="id" type="hidden" value="<%=student.getId()%>">
                    <input name="idCourse" type="hidden" value="<%=course.getId()%>">
                    <input name="action" type="hidden" value="coursesToEnroll">
                    <button class="gradient-button">Записать на курс</button>
                    <% } else { %>
                </form>
                <form name="cancelEnroll" method="post" action="student">
                    <input name="id" type="hidden" value="<%=student.getId()%>">
                    <input name="idCourse" type="hidden" value="<%=course.getId()%>">
                    <input name="action" type="hidden" value="coursesToCancelEnroll">
                    <button class="gradient-button">Отчислить с курса</button>
                    <% } %>
                </form>
            </td>
        </tr>
        <%
            }
        %>
    </table>
    <a class="gradient-button1" href="index.jsp">Главная страница</a>

    <a class="gradient-button1" href="student">Назад</a>
</center>
</body>
</html>
