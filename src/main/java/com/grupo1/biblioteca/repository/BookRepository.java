package com.grupo1.biblioteca.repository;

import com.grupo1.biblioteca.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    // Buscar libro por ISBN
    Optional<Book> findByIsbn(String isbn);

    // Buscar libros por título (contiene texto)
    List<Book> findByTitleContainingIgnoreCase(String title);

    // Buscar libros por autor (contiene texto)
    List<Book> findByAuthorsContainingIgnoreCase(String authors);

    // Buscar libros por categoría
    List<Book> findByCategory(String category);

    // Buscar libros disponibles (copias > 0)
    List<Book> findByCopiesAvailableGreaterThan(Integer copies);
}
