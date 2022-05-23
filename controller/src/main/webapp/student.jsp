<%@ page import="courses.entity.Student" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: akyna
  Date: 14.05.2022
  Time: 12:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Страница студентов</title>
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
    <h2>Сервис студентов</h2>
    <table border="3" width="50%">

        <caption><b>Список Студентов</b></caption>
        <tr>
            <th>Имя</th>
            <th>Фамилия</th>
            <th>Курсы</th>
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
                <form name="enroll" method="post" action="student">
                    <input name="id" type="hidden" value="<%=student.getId()%>">
                    <input name="action" type="hidden" value="enroll">
                    <button class="gradient-button">Список курсов</button>
                </form>
            </td>

            <td>
                <form name="delete" method="post" action="student">
                    <input name="id" type="hidden" value="<%=student.getId()%>">
                    <button class="gradient-button"><a
                            href="student-form.jsp?id=<%=student.getId()%>&surname=<%=student.getSurname()%>&name=<%=student.getName()%>&action=delete"
                    >Удалить</a></button>
                </form>

                <form name="edit" method="post" action="student">
                    <input name="id" type="hidden" value="<%=student.getId()%>">
                    <button class="gradient-button"><a
                            href="student-form.jsp?id=<%=student.getId()%>&surname=<%=student.getSurname()%>&name=<%=student.getName()%>&action=update"
                    >Редактировать</a></button>
                </form>
            </td>
        </tr>
        <%
            }
        %>
    </table>


    <a class="gradient-button1" href="student-form.jsp">Добавить студента</a>

    <a class="gradient-button1" href="index.jsp">Главная Страница</a>

    <a class="gradient-button1" href="search.jsp">Search Page</a>

</center>

</body>
</html>
