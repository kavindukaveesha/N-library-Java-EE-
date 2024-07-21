package com.team2.service;

import com.team2.Enums.UserType;
import com.team2.controller.utill.DBConnection;
import com.team2.models.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;

public class UserService {

    private static final String INSERT_QUERY = "INSERT INTO user (firstName, lastName, userNic, image, email, phoneNumber, address, password, active, userType) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE user SET firstName = ?, lastName = ?, userNic = ?, image = ?, email = ?, phoneNumber = ?, address = ? WHERE userId = ?";
    private static final String DELETE_QUERY = "DELETE FROM user WHERE userId = ?";
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM user WHERE userId = ?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM user WHERE userType = ?"; // Added WHERE clause to filter by userType
    private static final String SELECT_BY_EMAIL_QUERY = "SELECT * FROM user WHERE email = ?";
    private static final String CHANGE_USER_PASSWORD = "UPDATE user SET password = ? WHERE userId = ?";
    private static final String SELECT_BY_STUDENTID = "SELECT * FROM user WHERE userType = ? AND userId = ?";
    private static final String SELECT_BY_STUDENT_BY_FIRST_NAME = "SELECT * FROM user WHERE userType = ? AND firstName = ?";
    private static final String SELECT_BY_STUDENT_BY_LAST_NAME = "SELECT * FROM user WHERE userType = ? AND lastName = ?";

    // Add a new user
    public int addUser(User user) {
        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getUserNic());
            statement.setString(4, user.getImage());
            statement.setString(5, user.getEmail());
            statement.setString(6, user.getPhoneNumber());
            statement.setString(7, user.getAddress());
            statement.setString(8, user.getPassword());
            statement.setBoolean(9, user.isActive());
            statement.setString(10, user.getUserType().toString());

            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    // Update an existing user
    public boolean updateUser(User user) {
        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getUserNic());
            statement.setString(4, user.getImage());
            statement.setString(5, user.getEmail());
            statement.setString(6, user.getPhoneNumber());
            statement.setString(7, user.getAddress());
            statement.setInt(8, user.getUserId());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isActive(int userId) {
        String query = "SELECT active FROM user WHERE userId = ?";
        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getBoolean("active");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean enableUser(int userId) {
        String enableQuery = "UPDATE user SET active = TRUE WHERE userId = ?";
        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(enableQuery)) {
            statement.setInt(1, userId);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean disableUser(int userId) {
        String disableQuery = "UPDATE user SET active = FALSE WHERE userId = ?";
        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(disableQuery)) {
            statement.setInt(1, userId);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Get all users (with a filter for student type)
    public List<User> showUsers(String userType) {
        List<User> usersList = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(SELECT_ALL_QUERY)) {
            statement.setString(1, userType);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = mapResultSetToUser(resultSet);
                usersList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usersList;
    }

// Get all user by id (with a filter for student type)
    public List<User> findStudentByID(String userType, int userId) {
        List<User> usersList = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(SELECT_BY_STUDENTID)) {
            statement.setString(1, userType);
            statement.setInt(2, userId);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = mapResultSetToUser(resultSet);
                usersList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usersList;
    }

    // Get all user by first name (with a filter for student type)
    public List<User> findStudentByFirstName(String userType,String firstName) {
        List<User> usersList = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(SELECT_BY_STUDENT_BY_FIRST_NAME)) {
            statement.setString(1, userType);
            statement.setString(2, firstName);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = mapResultSetToUser(resultSet);
                usersList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usersList;
    }

    // Get a user by ID
    public User showUserById(int userId) {
        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_QUERY)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToUser(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Delete a user
    public boolean deleteUser(int userId) {
        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setInt(1, userId);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // User login
    public boolean loginUser(String email, String password, HttpSession session) {
        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(SELECT_BY_EMAIL_QUERY)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String storedPassword = resultSet.getString("password");
                if (storedPassword.equals(password)) {
                    User user = mapResultSetToUser(resultSet);
                    session.setAttribute("loggedInUser", user);
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // User logout
    public void logoutUser(HttpSession session) {
        session.invalidate();
    }
//    change user password

    public boolean changeUserPassword(int userId, String newPassword) {
        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(CHANGE_USER_PASSWORD)) {
            statement.setString(1, newPassword);
            statement.setInt(2, userId);

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Return false if an error occurred
    }

    // Helper method to map ResultSet to User object
    private User mapResultSetToUser(ResultSet resultSet) throws SQLException {
        int userId = resultSet.getInt("userId");
        String firstName = resultSet.getString("firstName");
        String lastName = resultSet.getString("lastName");
        String userNic = resultSet.getString("userNic");
        String image = resultSet.getString("image");
        String email = resultSet.getString("email");
        String phoneNumber = resultSet.getString("phoneNumber");
        String address = resultSet.getString("address");
        String password = resultSet.getString("password");
        boolean active = resultSet.getBoolean("active");
        String userTypeString = resultSet.getString("userType");

        UserType userType = UserType.valueOf(userTypeString);

        return new User(userId, firstName,
                lastName, userNic, image, email, phoneNumber, address, password, active, userType);
    }

}
