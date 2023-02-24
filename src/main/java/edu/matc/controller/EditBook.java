package edu.matc.controller;

import edu.matc.entity.Book;
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
 *
 * @author cmalin1
 */
@WebServlet(
        urlPatterns = {"/editBook"}
)

public class EditBook extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        BookData bookData = new BookData();

        if (req.getParameter("id").equals("")) {
            Book book = new Book(req.getParameter("author"), req.getParameter("title"), req.getParameter("publisher"), req.getParameter("publishDate"));
            bookData.insert(book);
        } else {
            Book book = new Book(Integer.valueOf(req.getParameter("id")), req.getParameter("title"), req.getParameter("author"), req.getParameter("publisher"), req.getParameter("publishDate"));
            bookData.update(book);
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("searchBook?searchTerm=&submit=viewAll");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BookData bookData = new BookData();
        req.setAttribute("book", bookData.getById(req.getParameter("id")));

        RequestDispatcher dispatcher = req.getRequestDispatcher("/editBook.jsp");
        dispatcher.forward(req, resp);
    }
}