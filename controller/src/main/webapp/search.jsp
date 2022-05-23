<%@ page import="courses.entity.Task" %>
<%@ page import="managment.implementation.StudentServiceImpl" %><%--
  Created by IntelliJ IDEA.
  User: akyna
  Date: 14.05.2022
  Time: 21:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Search</title>
</head>
<body>
<table>

    <form name="test" method="post" action="student">
        ID: <input name="id" type="text" value="Id">
        <input name="action" type="hidden" value="search">
        <button>Search</button>
    </form>
    <br>
    <% Task task2 = (Task) request.getAttribute("task");%>
    <%=task2 %>
</table>

</body>
</html>
