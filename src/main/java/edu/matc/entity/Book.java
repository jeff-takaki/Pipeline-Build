package edu.matc.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * A class to represent a book.
 *
 * @author cmalin
 */
public class Book {
    private String title;
    private String author;
    private String publisher;
    private int id;
    private LocalDate publishDate;
    static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");


    /**
     * Instantiates a new Book.
     */
    public Book() {
    }

    /**
     * Instantiates a new Book.
     *
     * @param title   the title
     * @param author    the author
     * @param publisher    the publisher
     * @param publishDate the publish date
     */
    public Book(String title, String author, String publisher, String publishDate) {
        this();
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publishDate = LocalDate.parse(publishDate, dateTimeFormatter);
    }

    /**
     * Instantiates a new Book.
     *
     * @param id          the id
     * @param title    the title
     * @param author    the author
     * @param publisher    the publisher
     * @param publishDate the publish date
     */
    public Book(int id, String author, String title, String publisher, String publishDate) {
        this(author, title, publisher, publishDate);
        this.id = id;

    }

    /**
     * Gets author.
     *
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets author.
     *
     * @param author the author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets publisher.
     *
     * @return the publisher
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * Sets publisher.
     *
     * @param publisher the publisher
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }


    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets publish date.
     *
     * @return the publish date
     */
    public LocalDate getPublishDate() {

        return publishDate;
    }

    /**
     * Gets publish Date
     *
     * @return the publish date as a string
     */
    public String getPublishDateForDisplay() {

        return dateTimeFormatter.format(publishDate);
    }

    /**
     * Sets publish date.
     *
     * @param publishDate the publish date
     */
    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    /**
     * Gets age.
     *
     * @return the age
     */
    public int getAge() {

        return (int)ChronoUnit.YEARS.between(publishDate, LocalDate.now());
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", id=" + id +
                ", publishDate=" + publishDate +
                ", age=" + getAge() +
                '}';
    }
}