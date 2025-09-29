package com.grupo1.biblioteca.dto;

import java.time.LocalDateTime;

public class LoanDto {

    // DTO para crear préstamo
    public static class Create {
        private Long userId;
        private Long bookId;

        public Create() {}

        public Long getUserId() { return userId; }
        public void setUserId(Long userId) { this.userId = userId; }

        public Long getBookId() { return bookId; }
        public void setBookId(Long bookId) { this.bookId = bookId; }
    }

    // DTO para respuesta
    public static class Response {
        private Long id;
        private Long userId;
        private String username;
        private Long bookId;
        private String bookTitle;
        private LocalDateTime loanDate;
        private LocalDateTime dueDate;
        private LocalDateTime returnDate;
        private String status;

        public Response() {}

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }

        public Long getUserId() { return userId; }
        public void setUserId(Long userId) { this.userId = userId; }

        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }

        public Long getBookId() { return bookId; }
        public void setBookId(Long bookId) { this.bookId = bookId; }

        public String getBookTitle() { return bookTitle; }
        public void setBookTitle(String bookTitle) { this.bookTitle = bookTitle; }

        public LocalDateTime getLoanDate() { return loanDate; }
        public void setLoanDate(LocalDateTime loanDate) { this.loanDate = loanDate; }

        public LocalDateTime getDueDate() { return dueDate; }
        public void setDueDate(LocalDateTime dueDate) { this.dueDate = dueDate; }

        public LocalDateTime getReturnDate() { return returnDate; }
        public void setReturnDate(LocalDateTime returnDate) { this.returnDate = returnDate; }

        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
    }
}