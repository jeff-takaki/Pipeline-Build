package edu.matc.controller;

import edu.matc.persistence.BookData;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Add or edit the book
 * @author cmalin1
 */

@WebServlet(
        urlPatterns = {"/deleteBook"}
)

public class DeleteBook extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        BookData bookData = new BookData();
        bookData.delete(Integer.valueOf(req.getParameter("id")));

        RequestDispatcher dispatcher = req.getRequestDispatcher("searchBook?searchTerm=&submit=viewAll");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}