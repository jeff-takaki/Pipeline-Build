package edu.matc.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test
    void getAge() {
        // create the object that has the method I want to test
        Book book = new Book();

        // set birthdate for the book
        LocalDate birthDate = LocalDate.parse("1968-01-01");
        book.setPublishDate(birthDate);

        // create variable for the expected value
        int expectedAge = 53;

        // call the method, and get the actual value
        int actualAge = book.getAge();

        // compare the two, pass or fail
        assertEquals(expectedAge, actualAge);
    }
}
