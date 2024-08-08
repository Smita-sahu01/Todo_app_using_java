package net.javaguides.todoapp.web;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import net.javaguides.todoapp.dao.TodoDao;
import net.javaguides.todoapp.dao.TodoDaoImol;
import net.javaguides.todoapp.model.Todo;

@WebServlet({"/"})
public class TodoController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private TodoDao todoDAO;

    public TodoController() {
    }

    public void init() {
        this.todoDAO = new TodoDaoImol();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String action = request.getServletPath();
    	try {
            switch (action) {
                case "/update":
                    this.updateTodo(request, response);
                    return;
                case "/new":
                    this.showNewForm(request, response);
                    return;
                case "/edit":
                    this.showEditForm(request, response);
                    return;
                case "/list":
                    this.listTodo(request, response);
                    return;
                case "/delete":
                    this.deleteTodo(request, response);
                    return;
                case "/insert":
                    this.insertTodo(request, response);
                    return;
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher("login/login.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException var6) {
            throw new ServletException(var6);
        }
    }

    private void listTodo(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        List<Todo> listTodo = this.todoDAO.selectAllTodos();
        request.setAttribute("listTodo", listTodo);
        RequestDispatcher dispatcher = request.getRequestDispatcher("todo/todo-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("todo/todo-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Todo existingTodo = this.todoDAO.selectTodo((long)id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("todo/todo-form.jsp");
        request.setAttribute("todo", existingTodo);
        dispatcher.forward(request, response);
    }

    private void insertTodo(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String title = request.getParameter("title");
        String username = request.getParameter("username");
        String description = request.getParameter("description");
        boolean isDone = Boolean.valueOf(request.getParameter("isDone"));
        Todo newTodo = new Todo(title, username, description, LocalDate.now(), isDone);
        this.todoDAO.insertTodo(newTodo);
        response.sendRedirect("list");
    }

    private void updateTodo(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String username = request.getParameter("username");
        String description = request.getParameter("description");
        LocalDate targetDate = LocalDate.parse(request.getParameter("targetDate"));
        boolean isDone = Boolean.valueOf(request.getParameter("isDone"));
        Todo updateTodo = new Todo((long)id, title, username, description, targetDate, isDone);
        this.todoDAO.updateTodo(updateTodo);
        response.sendRedirect("list");
    }

    private void deleteTodo(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        this.todoDAO.deleteTodo(id);
        response.sendRedirect("list");
    }
}
