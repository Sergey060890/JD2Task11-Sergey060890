<%@ page import="DTO.TaskDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Objects" %>
<%@ page import="DTO.CourseDTO" %>
<%@ page import="courses.entity.Task" %>
<%@ page import="courses.entity.Course" %><%--
  Created by IntelliJ IDEA.
  User: akyna
  Date: 19.05.2022
  Time: 03:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Страница заданий</title>
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
    <table border="3" width="50%">
        <caption><b>Список заданий</b></caption>
        <th>Описание</th>
        <th>Курс</th>
        <th>Действия</th>
        <%
            List<TaskDTO> taskList = (List<TaskDTO>) request.getAttribute("tasks");
            for (TaskDTO task : taskList
            ) { %>

        <tr>
            <td>
                <%=task.getDescription()%>
            </td>
            <td>
                <%=task.getCourse()%>
                <form name="assignToCourse" method="post" action="task">
                    <input name="id" type="hidden" value="<%=task.getId()%>">
                    <input name="action" type="hidden" value="assignToCourse">
                    <button class="gradient-button">Выбрать/редактировать курс</button>
                </form>
            </td>
            <td>

<%--                <form name="assignToStudent" method="post" action="task">--%>
<%--                    <input name="id" type="hidden" value="<%=task.getId()%>">--%>
<%--                    <input name="action" type="hidden" value="assignToStudent">--%>
<%--                    <button class="gradient-button">Добавление/удаление задания студенту</button>--%>
<%--                </form>--%>


    <form name="toDelete" method="post" action="task" autocomplete="off">
        <input name="id" type="hidden" value="<%=task.getId()%>">
    </label> <br/>
        <input name="action" type="hidden" value="delete">
        <button class="gradient-button1">Удалить задание</button>
    </form>
    <form name="update" method="post" action="task">
        <input name="id" type="hidden" value="<%=task.getId()%>">
        <button class="gradient-button1"><a
                href="task-form.jsp?id=<%=task.getId()%>&description=<%=task.getDescription()%>&review=<%=task.getReview()%>&course=<%=task.getCourse()%>&action=update"
        >Редактировать</a></button>
    </form>
            </td>
        </tr>
        <%
            }
        %>

    </table>

    <a class="gradient-button1" href="task-form.jsp">Добавить задание</a>

    <a class="gradient-button1" href="index.jsp">Главная страница</a>

    <a class="gradient-button1" href="index.jsp">Назад</a>

</center>


</body>
</html>
