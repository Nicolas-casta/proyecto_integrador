package com.grupo1.biblioteca.service;

import com.grupo1.biblioteca.model.Book;
import com.grupo1.biblioteca.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Test
    void testGetAllBooks() {
        var books = bookService.getAllBooks();
        assertNotNull(books);
    }
}