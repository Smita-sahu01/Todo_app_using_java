package net.javaguides.todoapp.web;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import java.io.IOException;

import net.javaguides.todoapp.dao.LoginDao;
import net.javaguides.todoapp.model.LoginBean;

@WebServlet({"/login"})
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private LoginDao loginDao;

    public LoginController() {
    }

    public void init() {
        this.loginDao = new LoginDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("login/login.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
			this.authenticate(request, response);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    private void authenticate(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        LoginBean loginBean = new LoginBean();
        loginBean.setUsername(username);
        loginBean.setPassword(password);

        try {
            if (this.loginDao.validate(loginBean)) {
            	System.out.println("login succefully");
                RequestDispatcher dispatcher = request.getRequestDispatcher("todo/todo-list.jsp");
                System.out.println("+++++++++++++++++++++++++++");
                dispatcher.forward(request, response);
            } else {
                HttpSession var8 = request.getSession();
            }
        } catch (ClassNotFoundException var7) {
            var7.printStackTrace();
        }

    }
}
