<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="net.javaguides.todoapp.model.Todo" %>
<html>
<head>
    <title>User Management Application</title>
    <link rel="stylesheet"
        href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
        crossorigin="anonymous">
</head>
<body>
    <header>
        <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
            <div>
                <a href="https://www.javaguides.net" class="navbar-brand">Todo App</a>
            </div>
            <ul class="navbar-nav">
                <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Todos</a></li>
            </ul>
            <ul class="navbar-nav navbar-collapse justify-content-end">
                <li><a href="<%=request.getContextPath()%>/logout" class="nav-link">Logout</a></li>
            </ul>
        </nav>
    </header>
    <div class="row">
        <div class="container">
            <h3 class="text-center">List of Todos</h3>
            <hr>
            <div class="container text-left">
                <a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add Todo</a>
            </div>
            <br>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>Title</th>
                        <th>Target Date</th>
                        <th>Todo Status</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        List<Todo> todos = (List<Todo>) request.getAttribute("listTodo");
                        if (todos != null) {
                            for (Todo todo : todos) {
                    %>
                    <tr>
                        <td><%= todo.getTitle() %></td>
                        <td><%= todo.getTargetDate() %></td>
                        <td><%= todo.getStatus() %></td>
                        <td>
                            <a href="edit?id=<%= todo.getId() %>">Edit</a>
                            &nbsp;&nbsp;&nbsp;&nbsp;
                            <a href="delete?id=<%= todo.getId() %>">Delete</a>
                        </td>
                    </tr>
                    <%
                            }
                        }
                    %>
                </tbody>
            </table>
        </div>
    </div>
    <jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>
