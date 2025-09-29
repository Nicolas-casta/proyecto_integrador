package com.grupo1.biblioteca.model;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String isbn;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String authors;

    @Column(nullable = false)
    private String category;

    @Column(name = "copies_total", nullable = false)
    private Integer copiesTotal;

    @Column(name = "copies_available", nullable = false)
    private Integer copiesAvailable;

    private String description;

    // Constructor vacío (obligatorio para JPA)
    public Book() {}

    // Constructor con parámetros básicos
    public Book(String isbn, String title, String authors, String category, Integer copiesTotal) {
        this.isbn = isbn;
        this.title = title;
        this.authors = authors;
        this.category = category;
        this.copiesTotal = copiesTotal;
        this.copiesAvailable = copiesTotal; // Al inicio todas las copias están disponibles
    }

    // Constructor completo
    public Book(String isbn, String title, String authors, String category,
                Integer copiesTotal, Integer copiesAvailable, String description) {
        this.isbn = isbn;
        this.title = title;
        this.authors = authors;
        this.category = category;
        this.copiesTotal = copiesTotal;
        this.copiesAvailable = copiesAvailable;
        this.description = description;
    }

    // Metodo útil para verificar si hay copias disponibles
    public boolean isAvailable() {
        return copiesAvailable != null && copiesAvailable > 0;
    }

    // Metodo para reducir copias disponibles (cuando se presta)
    public void reducirCopiaDisponible() {
        if (copiesAvailable > 0) {
            copiesAvailable--;
        }
    }

    // Metodo para aumentar copias disponibles (cuando se devuelve)
    public void aumentarCopiaDisponible() {
        if (copiesAvailable < copiesTotal) {
            copiesAvailable++;
        }
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getCopiesTotal() {
        return copiesTotal;
    }

    public void setCopiesTotal(Integer copiesTotal) {
        this.copiesTotal = copiesTotal;
    }

    public Integer getCopiesAvailable() {
        return copiesAvailable;
    }

    public void setCopiesAvailable(Integer copiesAvailable) {
        this.copiesAvailable = copiesAvailable;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", authors='" + authors + '\'' +
                ", category='" + category + '\'' +
                ", copiesAvailable=" + copiesAvailable +
                '}';
    }
}