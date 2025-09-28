package com.team2.controller.utill;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {

    // Database connection details
    private static final String url = "jdbc:mysql://mysql:3306/library_manage";
    private static final String username = "root";
    private static final String password = "Kavindu12345";

    // Load the MySQL JDBC driver (static block for initialization)
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Error: JDBC Driver not found! " + e.getMessage());
        }
    }

    // Get a database connection
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    // Prepare a statement
    public static PreparedStatement setStatment(String sql) throws SQLException {
        Connection connection = getConnection();
        return connection.prepareStatement(sql);
    }

    // Close database resources safely
    public static void closeResources(Connection con, PreparedStatement pst) {
        try {
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            System.out.println("Error in closing resources: " + ex.getMessage());
        }
    }
    
    // Close database resources safely including ResultSet
    public static void closeResources(Connection con, PreparedStatement pst, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            System.out.println("Error in closing resources: " + ex.getMessage());
        }
    }
}