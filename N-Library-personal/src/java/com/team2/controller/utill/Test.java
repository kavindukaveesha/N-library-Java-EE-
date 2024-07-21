package com.team2.controller.utill;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CheckDBConnection")
public class Test extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        Connection connection = null;
        try {
            // Database connection details
            String jdbcURL = "jdbc:mysql://localhost:3306/library_manage";
            String jdbcUsername = "root";
            String jdbcPassword = "";
            
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establish the connection
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            
            if (connection != null) {
                out.println("<h3>Database connection is successful!</h3>");
            } else {
                out.println("<h3>Failed to make database connection!</h3>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            out.println("<h3>SQL Exception: " + e.getMessage() + "</h3>");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            out.println("<h3>Class Not Found Exception: " + e.getMessage() + "</h3>");
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
