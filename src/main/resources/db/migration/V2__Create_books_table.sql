CREATE TABLE books (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    isbn VARCHAR(20) NOT NULL UNIQUE,
    title VARCHAR(255) NOT NULL,
    authors VARCHAR(500) NOT NULL,
    category VARCHAR(100) NOT NULL,
    copies_total INT NOT NULL DEFAULT 1,
    copies_available INT NOT NULL DEFAULT 1,
    description TEXT
);

INSERT INTO books (isbn, title, authors, category, copies_total, copies_available, description) VALUES
('978-0134685991', 'Effective Java', 'Joshua Bloch', 'Programming', 3, 3, 'Best practices for Java'),
('978-0596009205', 'Head First Design Patterns', 'Eric Freeman', 'Programming', 2, 2, 'Design patterns'),
('978-0135166307', 'Clean Code', 'Robert Martin', 'Programming', 4, 4, 'Clean code practices'),
('978-0345816023', '1984', 'George Orwell', 'Fiction', 5, 5, 'Dystopian novel'),
('978-0061120084', 'To Kill a Mockingbird', 'Harper Lee', 'Fiction', 3, 3, 'American classic');