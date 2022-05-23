<%@ page import="DTO.CourseDTO" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: akyna
  Date: 19.05.2022
  Time: 00:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Страница администратора</title>
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
    <h2>Сервис администратора</h2>
    <table border="6" width="50%">
        <caption><b>Список курсов и закрепленных учителей</b></caption>
        <th>№</th>
        <th>Курсы</th>
        <th>Часы</th>
        <th>Имя учителя</th>
        <th>Фамилия учителя</th>
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
            <td><%= course.getTeacherName()%>
            </td>
            <td><%= course.getTeacherSurname()%>
            </td>
            <td>
                <form name="enroll" method="post" action="admin">
                    <input name="id" type="hidden" value="<%=course.getId()%>">
                    <input name="action" type="hidden" value="enroll">
                    <button class="gradient-button">Добавить/удалить учителя с курса</button>
                </form>
            </td>
        </tr>
        <%
            }
        %>
    </table>

    <a class="gradient-button1" href="course">Управление Курсами</a>

    <a class="gradient-button1" href="index.jsp">Главная страница</a>

    <a class="gradient-button1" href="teacher">Информация об Учителях</a>


</center>
</body>
</html>
