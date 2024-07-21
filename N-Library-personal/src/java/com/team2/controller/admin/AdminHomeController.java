/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team2.controller.admin;

import com.team2.models.BookCategories;
import com.team2.models.Books;
import com.team2.models.User;
import com.team2.service.BookCategoryService;
import com.team2.service.BookService;
import com.team2.service.UserService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
@WebServlet(name = "AdminHome", urlPatterns = {"/admin/home"})
public class AdminHomeController extends HttpServlet {

    private UserService userService;
    private BookCategoryService bookCategoryService;
    private BookService bookService;

    public AdminHomeController() {
        this.userService = new UserService();
        this.bookCategoryService = new BookCategoryService();
        this.bookService = new BookService();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<User> admins = userService.showUsers("admin");
        List<User> students = userService.showUsers("student");
        List<BookCategories> categories = bookCategoryService.showBookCategories();
        List<Books> books = bookService.showBooks();

        int number_of_students = students.size();
        int number_of_admins = admins.size();
        int number_of_categories = categories.size();
        int number_of_books = books.size();

        List<Map<String, Object>> cardData = new ArrayList<>();
        Map<String, Object> card1 = new HashMap<>();
        card1.put("title", "Available Books");
        card1.put("value", number_of_books);
        cardData.add(card1);

        Map<String, Object> card2 = new HashMap<>();
        card2.put("title", "Students");
        card2.put("value", number_of_students);
        cardData.add(card2);

        Map<String, Object> card3 = new HashMap<>();
        card3.put("title", "Borrowed Books");
        card3.put("value", 250);
        cardData.add(card3);

        Map<String, Object> card4 = new HashMap<>();
        card4.put("title", "Returned Books");
        card4.put("value", 2);
        cardData.add(card4);

        Map<String, Object> card5 = new HashMap<>();
        card5.put("title", "Books Categories");
        card5.put("value", number_of_categories);
        cardData.add(card5);

        Map<String, Object> card6 = new HashMap<>();
        card6.put("title", "Admins");
        card6.put("value", number_of_admins);
        cardData.add(card6);

        request.setAttribute("number_of_students", number_of_students);
        request.setAttribute("number_of_admins", number_of_admins);
        request.setAttribute("number_of_categories", number_of_categories);
        
        
        // Set the card data attribute in the request
        request.setAttribute("cardData", cardData);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/Admin/home/home.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
