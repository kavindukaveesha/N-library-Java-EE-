package com.team2.controller.client;

import com.google.gson.Gson;
import com.mysql.cj.Session;
import com.team2.models.User;
import com.team2.service.UserService;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kavin
 */
@WebServlet(name = "ProfileController", urlPatterns = {"/student/profile", "/student/profile/change-password", "/student/profile/change-profile-picture"})
public class ProfileController extends HttpServlet {

    private UserService userService;

    public ProfileController() {
        this.userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/student/profile":
                    showProfile(request, response);
                    break;
                case "/student/profile/change-password":
                    changePassword(request, response);
                    break;
                case "/student/profile/change-profile-picture":
                    changeProfilePicture(request, response);
                    break;

                default:
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);

    }

    private void showProfile(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
        if (loggedInUser != null) {
            int userId = loggedInUser.getUserId();

            // Pass the user data to JSP
            request.setAttribute("loggedInUser", loggedInUser);
            User student = userService.showUserById(userId);
            request.setAttribute("student", student);

            // Forward to the JSP
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/client/profile/profile.jsp");
            dispatcher.forward(request, response);
        } else {
            // Redirect to login page if not logged in
            response.sendRedirect("/N-Library-personal/login"); // Or handle this appropriately for your app
        }
    }

    private void changePassword(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
        boolean updatedPassword = false;
        if (loggedInUser != null) {
            int userId = loggedInUser.getUserId();
            String userPassword = loggedInUser.getPassword();
            if (userPassword == null ? currentPassword == null : userPassword.equals(currentPassword)) {
                updatedPassword = userService.changeUserPassword(userId, newPassword);
                if (updatedPassword) {
                    request.setAttribute("status", "passwordUpdateSuccess");
                } else {
                    request.setAttribute("status", "passwordUpdateFailed");
                }
            } else {
                request.setAttribute("status", "noMatch");

            }

            // Pass the user data to JSP
            request.setAttribute("loggedInUser", loggedInUser);
            User student = userService.showUserById(userId);
            request.setAttribute("student", student);

            // Forward to the JSP
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/client/profile/profile.jsp");
            dispatcher.forward(request, response);

        } else {
            // Redirect to login page if not logged in
            response.sendRedirect("/N-Library-personal/login");
        }

    }

    private void changeProfilePicture(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
        if (loggedInUser != null) {
            int userId = loggedInUser.getUserId();
            String userPassword = loggedInUser.getPassword();

            PrintWriter out = response.getWriter();
            out.print(userPassword);

//
//            // Pass the user data to JSP
//            request.setAttribute("loggedInUser", loggedInUser);
//            User student = userService.showUserById(userId);
//            request.setAttribute("student", student);
//
//            // Forward to the JSP
//            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/client/profile/profile.jsp");
//            dispatcher.forward(request, response);
        } else {
            // Redirect to login page if not logged in
            response.sendRedirect("/N-Library-personal/student/profile"); // Or handle this appropriately for your app
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
