package edu.matc.controller;

import edu.matc.persistence.BookData;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.*;
import java.io.IOException;


/**
 * Search for books
 * @author cmalin
 */

@WebServlet(
        urlPatterns = {"/searchBook"}
)

public class SearchBook extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        BookData bookData = new BookData();
        if (req.getParameter("submit").equals("search")) {
            req.setAttribute("books", bookData.getBooksByAuthor(req.getParameter("searchTerm")));
        } else {
            req.setAttribute("books", bookData.getAllBooks());
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/results.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}