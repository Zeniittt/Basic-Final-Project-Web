/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package book.controller;

import book.dao.BookDAO;
import book.dto.Book;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "ShopController", urlPatterns = {"/shop/*"})
public class ShopController extends HttpServlet {

        private static final String defaultURL = "/Shop.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String requestURI = request.getRequestURI();       
        String url = defaultURL;
        if (requestURI.endsWith("/showAllBook")) 
        {
            url = showAllBook(request, response);
        }
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

     private String showAllBook(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        BookDAO getBook = new BookDAO();
        List<Book> listBook = getBook.getAllBook();
        log("check showAllBookkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
        request.setAttribute("listBook", listBook);
        return defaultURL;
    }
  
}
