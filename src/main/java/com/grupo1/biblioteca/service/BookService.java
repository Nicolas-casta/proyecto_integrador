package com.grupo1.biblioteca.service;

import com.grupo1.biblioteca.dto.BookDto;
import com.grupo1.biblioteca.model.Book;
import com.grupo1.biblioteca.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    // Obtener todos los libros
    public List<BookDto.Response> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    // Obtener libro por ID
    public BookDto.Response getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado con ID: " + id));
        return convertToResponse(book);
    }

    // Crear nuevo libro
    public BookDto.Response createBook(BookDto.Create dto) {
        // Verificar si el ISBN ya existe
        if (bookRepository.findByIsbn(dto.getIsbn()).isPresent()) {
            throw new RuntimeException("El ISBN ya existe: " + dto.getIsbn());
        }

        // Crear nuevo libro
        Book book = new Book();
        book.setIsbn(dto.getIsbn());
        book.setTitle(dto.getTitle());
        book.setAuthors(dto.getAuthors());
        book.setCategory(dto.getCategory());
        book.setCopiesTotal(dto.getCopiesTotal());
        book.setCopiesAvailable(dto.getCopiesTotal()); // Al inicio todas disponibles
        book.setDescription(dto.getDescription());

        // Guardar en BD
        Book savedBook = bookRepository.save(book);
        return convertToResponse(savedBook);
    }

    // Actualizar libro
    public BookDto.Response updateBook(Long id, BookDto.Update dto) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado con ID: " + id));

        // Actualizar campos
        if (dto.getTitle() != null) {
            book.setTitle(dto.getTitle());
        }
        if (dto.getAuthors() != null) {
            book.setAuthors(dto.getAuthors());
        }
        if (dto.getCategory() != null) {
            book.setCategory(dto.getCategory());
        }
        if (dto.getCopiesTotal() != null) {
            book.setCopiesTotal(dto.getCopiesTotal());
        }
        if (dto.getDescription() != null) {
            book.setDescription(dto.getDescription());
        }

        Book updatedBook = bookRepository.save(book);
        return convertToResponse(updatedBook);
    }

    // Eliminar libro
    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new RuntimeException("Libro no encontrado con ID: " + id);
        }
        bookRepository.deleteById(id);
    }

    // Buscar libros por título
    public List<BookDto.Response> searchByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    // Buscar libros por categoría
    public List<BookDto.Response> getBooksByCategory(String category) {
        return bookRepository.findByCategory(category).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    // Obtener libros disponibles
    public List<BookDto.Response> getAvailableBooks() {
        return bookRepository.findByCopiesAvailableGreaterThan(0).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    // Método auxiliar: Convertir Book a BookDto.Response
    private BookDto.Response convertToResponse(Book book) {
        BookDto.Response response = new BookDto.Response();
        response.setId(book.getId());
        response.setIsbn(book.getIsbn());
        response.setTitle(book.getTitle());
        response.setAuthors(book.getAuthors());
        response.setCategory(book.getCategory());
        response.setCopiesTotal(book.getCopiesTotal());
        response.setCopiesAvailable(book.getCopiesAvailable());
        response.setDescription(book.getDescription());
        response.setAvailable(book.isAvailable());
        return response;
    }
}