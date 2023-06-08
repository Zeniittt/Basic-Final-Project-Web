/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package book.controller;

import book.dao.BookDAO;
import book.dto.Book;
import book.dto.Cart;
import book.dto.LineItem;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "CartControl", urlPatterns = {"/cart/*"})
public class CartControl extends HttpServlet {

    private static final String defaultURL = "/Cart.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String url = defaultURL;
        if (requestURI.endsWith("/showCart")) {
            showCart(request, response);
        } else if (requestURI.endsWith("/addItem")) {
            url = addItem(request, response);
        } else if (requestURI.endsWith("/increaseQuantity")) {
            url = increaseQuantity(request, response);
        } else if (requestURI.endsWith("/decreaseQuantity")) {
            url = decreaseQuantity(request, response);
        } else if (requestURI.endsWith("/removeItem")) {
            url = removeItem(request, response);
        }

        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    private String showCart(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null || cart.getCount() == 0) {
            request.setAttribute("emptyCart", "Your cart is empty");
        } else {
            request.getSession().setAttribute("cart", cart);
        }
        return defaultURL;
    }

    private String addItem(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        log("Check Carttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt " + cart);
        if (cart == null) {
            cart = new Cart();
        }
        String idBook = request.getParameter("idBook");
        BookDAO getBook = new BookDAO();
        Book book = getBook.getDetailProductByID(idBook);       
        if (book != null) 
        {
            LineItem lineItem = new LineItem();
            lineItem.setBook(book);
            cart.addItem(lineItem);
        }     
        session.setAttribute("cart", cart);
        return defaultURL;

    }

    private String increaseQuantity(HttpServletRequest request, HttpServletResponse response) {
        String idBook = request.getParameter("idBook");
        String quantity = request.getParameter("quantity");
        int newQuantity = Integer.parseInt(quantity);
        newQuantity += 1;
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        cart.increaseQuantity(Integer.parseInt(idBook), newQuantity);
        session.setAttribute("cart", cart);

        return defaultURL;
    }

    private String decreaseQuantity(HttpServletRequest request, HttpServletResponse response) {
        String idBook = request.getParameter("idBook");
        String quantity = request.getParameter("quantity");
        log("Servlet nay da duoc goi toiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii " + idBook);
        log("Servlet nay da duoc goi toiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii " + quantity);
        int newQuantity = Integer.parseInt(quantity);
        if (newQuantity >= 2) {
            newQuantity -= 1;
            log("Servlet nay da duoc goi toiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii gia tri của newQUan " + newQuantity);
            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("cart");
            cart.decreaseQuantity(Integer.parseInt(idBook), newQuantity);
            session.setAttribute("cart", cart);
            return defaultURL;
        } else {
            newQuantity = 1;
        }
        return defaultURL;
    }

    private String removeItem(HttpServletRequest request,
            HttpServletResponse response) {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        String idBook = request.getParameter("idBook");
        String quantity = request.getParameter("quantity");
        int Quantity = Integer.parseInt(quantity);
        log("Da vao duoc toi vi tri nay cua servlet removeItemmmmmmmmmmmmmmmmmmmmmmmmmm " + Quantity);
        BookDAO getBook = new BookDAO();
        Book book = getBook.getDetailProductByID(idBook);
        log("Da vao duoc toi vi tri nay cua servlet removeItemmmmmmmmmmmmmmmmmmmmmmmmmm22222222222 " + Quantity);
        if (book != null) {
            LineItem lineItem = new LineItem();
            lineItem.setBook(book);
            lineItem.setQuantity(Quantity);
            log("Da vao duoc toi vi tri nay cua servlet removeItemmmmmmmmmmmmmmmmmmmmmmmmmm333333333333 " + Quantity);
            cart.removeItem(lineItem);
        }
        log("Da vao duoc toi vi tri nay cua servlet removeItemmmmmmmmmmmmmmmmmmmmmmmmmm444444444444444444444444 " + Quantity);
        if (cart == null || cart.getCount() == 0)
        {
            request.setAttribute("emptyCart", "Your cart is empty");
        } else session.setAttribute("cart", cart);
        return defaultURL;
    }

}
