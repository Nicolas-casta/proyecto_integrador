package com.grupo1.biblioteca.dto;

public class BookDto {

    // DTO para crear libro
    public static class Create {
        private String isbn;
        private String title;
        private String authors;
        private String category;
        private Integer copiesTotal;
        private String description;

        public Create() {}

        public String getIsbn() { return isbn; }
        public void setIsbn(String isbn) { this.isbn = isbn; }

        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }

        public String getAuthors() { return authors; }
        public void setAuthors(String authors) { this.authors = authors; }

        public String getCategory() { return category; }
        public void setCategory(String category) { this.category = category; }

        public Integer getCopiesTotal() { return copiesTotal; }
        public void setCopiesTotal(Integer copiesTotal) { this.copiesTotal = copiesTotal; }

        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
    }

    // DTO para actualizar libro
    public static class Update {
        private String title;
        private String authors;
        private String category;
        private Integer copiesTotal;
        private String description;

        public Update() {}

        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }

        public String getAuthors() { return authors; }
        public void setAuthors(String authors) { this.authors = authors; }

        public String getCategory() { return category; }
        public void setCategory(String category) { this.category = category; }

        public Integer getCopiesTotal() { return copiesTotal; }
        public void setCopiesTotal(Integer copiesTotal) { this.copiesTotal = copiesTotal; }

        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
    }

    // DTO para respuesta
    public static class Response {
        private Long id;
        private String isbn;
        private String title;
        private String authors;
        private String category;
        private Integer copiesTotal;
        private Integer copiesAvailable;
        private String description;
        private Boolean available;

        public Response() {}

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }

        public String getIsbn() { return isbn; }
        public void setIsbn(String isbn) { this.isbn = isbn; }

        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }

        public String getAuthors() { return authors; }
        public void setAuthors(String authors) { this.authors = authors; }

        public String getCategory() { return category; }
        public void setCategory(String category) { this.category = category; }

        public Integer getCopiesTotal() { return copiesTotal; }
        public void setCopiesTotal(Integer copiesTotal) { this.copiesTotal = copiesTotal; }

        public Integer getCopiesAvailable() { return copiesAvailable; }
        public void setCopiesAvailable(Integer copiesAvailable) { this.copiesAvailable = copiesAvailable; }

        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }

        public Boolean getAvailable() { return available; }
        public void setAvailable(Boolean available) { this.available = available; }
    }
}