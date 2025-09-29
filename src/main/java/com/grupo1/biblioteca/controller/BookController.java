package com.grupo1.biblioteca.controller;

import com.grupo1.biblioteca.dto.BookDto;
import com.grupo1.biblioteca.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    // GET /api/books - Obtener todos los libros
    @GetMapping
    public ResponseEntity<List<BookDto.Response>> getAllBooks() {
        List<BookDto.Response> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    // GET /api/books/{id} - Obtener libro por ID
    @GetMapping("/{id}")
    public ResponseEntity<BookDto.Response> getBookById(@PathVariable Long id) {
        try {
            BookDto.Response book = bookService.getBookById(id);
            return ResponseEntity.ok(book);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // POST /api/books - Crear nuevo libro
    @PostMapping
    public ResponseEntity<BookDto.Response> createBook(@RequestBody BookDto.Create dto) {
        try {
            BookDto.Response newBook = bookService.createBook(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(newBook);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // PUT /api/books/{id} - Actualizar libro
    @PutMapping("/{id}")
    public ResponseEntity<BookDto.Response> updateBook(@PathVariable Long id, @RequestBody BookDto.Update dto) {
        try {
            BookDto.Response updatedBook = bookService.updateBook(id, dto);
            return ResponseEntity.ok(updatedBook);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /api/books/{id} - Eliminar libro
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        try {
            bookService.deleteBook(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // GET /api/books/search?title=xxx - Buscar por título
    @GetMapping("/search")
    public ResponseEntity<List<BookDto.Response>> searchByTitle(@RequestParam String title) {
        List<BookDto.Response> books = bookService.searchByTitle(title);
        return ResponseEntity.ok(books);
    }

    // GET /api/books/category/{category} - Filtrar por categoría
    @GetMapping("/category/{category}")
    public ResponseEntity<List<BookDto.Response>> getBooksByCategory(@PathVariable String category) {
        List<BookDto.Response> books = bookService.getBooksByCategory(category);
        return ResponseEntity.ok(books);
    }

    // GET /api/books/available - Obtener solo libros disponibles
    @GetMapping("/available")
    public ResponseEntity<List<BookDto.Response>> getAvailableBooks() {
        List<BookDto.Response> books = bookService.getAvailableBooks();
        return ResponseEntity.ok(books);
    }
}