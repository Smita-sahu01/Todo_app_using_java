package net.javaguides.todoapp.utils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Iterator;

public class JDBCUtils {
    private static String jdbcURL = "jdbc:mysql://localhost:3306/todo_management";
    private static String jdbcUsername = "root";
    private static String jdbcPassword = "Smitarani@2002";

    public JDBCUtils() {
    }

    public static Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException var2) {
            var2.printStackTrace();
        } catch (ClassNotFoundException var3) {
            var3.printStackTrace();
        }

        return connection;
    }

    public static void printSQLException(SQLException ex) {
        Iterator var2 = ex.iterator();

        while(true) {
            Throwable e;
            do {
                if (!var2.hasNext()) {
                    return;
                }

                e = (Throwable)var2.next();
            } while(!(e instanceof SQLException));

            e.printStackTrace(System.err);
            System.err.println("SQLState: " + ((SQLException)e).getSQLState());
            System.err.println("Error Code: " + ((SQLException)e).getErrorCode());
            System.err.println("Message: " + e.getMessage());

            for(Throwable t = ex.getCause(); t != null; t = t.getCause()) {
                System.out.println("Cause: " + t);
            }
        }
    }

    public static Date getSQLDate(LocalDate date) {
        return Date.valueOf(date);
    }

    public static LocalDate getUtilDate(Date sqlDate) {
        return sqlDate.toLocalDate();
    }
}
