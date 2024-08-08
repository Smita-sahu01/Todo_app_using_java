package net.javaguides.todoapp.dao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import net.javaguides.todoapp.model.LoginBean;
import net.javaguides.todoapp.utils.JDBCUtils;

public class LoginDao {
    public LoginDao() {
    }

    public boolean validate(LoginBean loginBean) throws Throwable {
        boolean status = false;
        Class.forName("com.mysql.jdbc.Driver");

        try {
            Throwable var3 = null;
            Object var4 = null;

            try {
                Connection connection = JDBCUtils.getConnection();

                try {
                    PreparedStatement preparedStatement = connection.prepareStatement("select * from users where username = ? and password = ? ");

                    try {
                        preparedStatement.setString(1, loginBean.getUsername());
                        preparedStatement.setString(2, loginBean.getPassword());
                        System.out.println(preparedStatement);
                        ResultSet rs = preparedStatement.executeQuery();
                        status = rs.next();
                    } finally {
                        if (preparedStatement != null) {
                            preparedStatement.close();
                        }

                    }
                } catch (Throwable var21) {
                    if (var3 == null) {
                        var3 = var21;
                    } else if (var3 != var21) {
                        var3.addSuppressed(var21);
                    }

                    if (connection != null) {
                        connection.close();
                    }

                    throw var3;
                }

                if (connection != null) {
                    connection.close();
                }
            } catch (Throwable var22) {
                if (var3 == null) {
                    var3 = var22;
                } else if (var3 != var22) {
                    var3.addSuppressed(var22);
                }

                throw var3;
            }
        } catch (SQLException var23) {
            JDBCUtils.printSQLException(var23);
        }

        return status;
    }
}
