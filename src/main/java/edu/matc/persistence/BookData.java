package edu.matc.persistence;

import edu.matc.entity.Book;
import java.util.logging.Level;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Access books in the book table.
 *
 * @author cmalin
 */
public class BookData {

    private final java.util.logging.Logger log = java.util.logging.Logger.getLogger("edu.matc.book");

    /**
     * Gets all books.
     *
     * @return the all books
     */
    public List<Book> getAllBooks() {

        String sql = "SELECT * FROM book";
        log.log(Level.INFO, "Executing SQL: " + sql);
        return executeQuery(sql);
    }

    /**
     * Gets books by author.
     *
     * @param author the author
     * @return the books by author
     */
    public List<Book> getBooksByAuthor(String author) {

        String sql = "SELECT * FROM book WHERE author like '%" + author + "%'";
        return executeQuery(sql);
    }

    /**
     * Executes the query passed in and returns the list of books
     *
     * @param sql
     * @return list of books
     */
    private List<Book> executeQuery(String sql) {


        List<Book> books = new ArrayList<Book>();
        Database database = Database.getInstance();
        Connection connection = null;
        try {
            log.log(Level.INFO, "Connecting to DB");
            database.connect();
            connection = database.getConnection();
            log.log(Level.INFO, "Successfully connected to DB");
            Statement selectStatement = connection.createStatement();
            ResultSet results = selectStatement.executeQuery(sql);
            while (results.next()) {
                Book employee = createBookFromResults(results);
                books.add(employee);
            }
            database.disconnect();
        } catch (SQLException sqle) {
            log.log(Level.SEVERE, "BookData.getAllBooks()...SQLException: " + sqle);
        } catch (Exception e) {
            log.log(Level.SEVERE, "BookData.getAllBooks()...Exception: " + e);
        }
        return books;
    }

    /**
     * Create and return a book from the resultset
     *
     * @param results
     * @return
     * @throws SQLException
     */
    private Book createBookFromResults(ResultSet results) throws SQLException {
        Book book = new Book();
        book.setAuthor(results.getString("author"));
        book.setTitle(results.getString("title"));
        book.setPublisher(results.getString("publisher"));
        //dates are correct in db, but when retrieved as localdate, they were one day early...
        LocalDate dob = results.getDate("publish_date").toLocalDate().plusDays(1);
        book.setPublishDate(dob);
        book.setId(results.getInt("id"));

        return book;
    }

    /**
     * Insert book.
     *
     * @param book the book
     * @return the number of records inserted
     */
    public Integer insert(Book book) {
        int recordsUpdated = 0;
        log.info("Inserting " + book);
        Database database = Database.getInstance();
        Connection connection = null;
        String sql = "INSERT INTO book ("
                + " title,"
                + " author,"
                + " publisher,"
                + " publish_date,"
                + " id) VALUES ("
                + " ?, ?, ?, ?, ?)";

        return executeSql(book, sql);
    }


    /**
     * Update book.
     *
     * @param book the book
     * @return the number of records updated
     */
    public Integer update(Book book) {
        log.info("Updating " + book);

        Connection connection = null;
        String sql = "UPDATE book SET title = ?, author = ?, publisher = ?, publish_date = ? WHERE id = ?";

        return executeSql(book, sql);
    }



    private Integer executeSql(Book book, String sql) {
        Connection connection;
        Database database = Database.getInstance();
        int recordsUpdated = 0;
        try {
            database.connect();
            connection = database.getConnection();

            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, book.getTitle());
            st.setString(2, book.getAuthor());
            st.setString(3, book.getPublisher());
            st.setDate(4, Date.valueOf(book.getPublishDate()));
            st.setInt(5, book.getId());

            recordsUpdated = st.executeUpdate();
            st.close();
        } catch (SQLException sqle) {
            log.log(Level.SEVERE, "BookData.executeSQL...SQLException: ", sqle);
        } catch (Exception e) {
            log.log(Level.SEVERE, "BookData.executeSQL()...Exception: ", e);
        }
        return recordsUpdated;
    }

    /**
     * Gets the book by id.
     *
     * @param id the id
     * @return the by id
     */
    public Book getById(String id) {
        String sql = "SELECT * FROM book WHERE id = " + id;
        log.info("Here's the select sql " + sql);
        return executeQuery(sql).get(0);
    }

    /**
     * Delete book
     *
     * @param id the id of the book to delete
     * @return the number of records deleted
     */
    public Integer delete(Integer id) {
        log.info("Deleting book with id " + id);

        Connection connection = null;
        String sql = "delete from book WHERE id = ?";

        Database database = Database.getInstance();
        int recordsUpdated = 0;
        try {
            database.connect();
            connection = database.getConnection();

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);

            recordsUpdated = st.executeUpdate();
            st.close();
        } catch (SQLException sqle) {
            log.log(Level.SEVERE, "BookData.deleteBook...SQLException: ", sqle);
        } catch (Exception e) {
            log.log(Level.SEVERE, "UserData.deleteUser()...Exception: ", e);
        }
        return recordsUpdated;
    }
}