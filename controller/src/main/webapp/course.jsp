<%@ page import="courses.entity.Course" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: akyna
  Date: 14.05.2022
  Time: 17:38
  To change this template use File | Settings | File Templates.
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
</head>
<body>
<center>
    <h2>Страница курсов</h2>
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
                <form name="delete" method="post" action="course">
                    <input name="id" type="hidden" value="<%=course.getId()%>">
                    <button class="gradient-button"><a
                            href="course-form.jsp?id=<%=course.getId()%>&description=<%=course.getDescription()%>&hours=<%=course.getHours()%>&action=delete"
                    >Удалить</a></button>
                </form>

                <form name="edit" method="post" action="course">
                    <input name="id" type="hidden" value="<%=course.getId()%>">
                    <button class="gradient-button"><a
                            href="course-form.jsp?id=<%=course.getId()%>&description=<%=course.getDescription()%>&hours=<%=course.getHours()%>&action=update"
                    >Редактировать</a></button>
                </form>

            </td>
        </tr>
        <%
            }
        %>
    </table>
    <a class="gradient-button1" href="course-form.jsp">Добавить курс</a>

    <a class="gradient-button1" href="index.jsp">Главная страница</a>

    <a class="gradient-button1" href="admin">Назад</a>
</center>
</body>
</html>
