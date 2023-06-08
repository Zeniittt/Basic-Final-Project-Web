/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package book.controller;

import book.dao.BookDAO;
import book.dao.CategoryDAO;
import book.dto.Book;
import book.dto.Category;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "DetailBookControl", urlPatterns = {"/detail/*"})
public class DetailBookControl extends HttpServlet {

    private static final String defaultURL = "/DetailBook.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String url = defaultURL;
        log("Detailllllllllllllllllllllllllllllllllllllllllllllllllllllllll" + requestURI);
        if (requestURI.endsWith("/detailBook")) {
            showDetailBook(request, response);
        }
        request
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    private String showDetailBook(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("idBook");
        log("StringIDDaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" + id);
        BookDAO getBookByID = new BookDAO();
        Book detailBook = getBookByID.getDetailProductByID(id);
        int cateID = getBookByID.getCategoryID(id);
        log("cateIDoooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo " + cateID);
        CategoryDAO getCategoryByID = new CategoryDAO();
        Category cateOfBook = getCategoryByID.getDetailCategory(cateID);
        request.setAttribute("detailBook", detailBook);
        request.setAttribute("cateOfBook", cateOfBook);
        log("CATEGORY OF BOOKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK" + cateOfBook.getCateName());
        return defaultURL;
    }
}
