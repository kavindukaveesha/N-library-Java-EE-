
package com.test;

import com.team2.controller.utill.RamdomBookIdGenarator;
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author kavin
 */
@WebServlet(name = "Test", urlPatterns = {"/Test"})
public class Test extends HttpServlet {
   
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String text = "kavind";
        String shot = RamdomBookIdGenarator.generateBookId(text, 3);
        PrintWriter out = response.getWriter();
        out.print(shot);
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
