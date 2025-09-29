CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    role VARCHAR(20) NOT NULL DEFAULT 'USER'
);

INSERT INTO users (username, email, password_hash, full_name, role) VALUES
('admin', 'admin@biblioteca.com', 'admin123', 'Administrador', 'LIBRARIAN'),
('juan', 'juan@email.com', 'juan123', 'Juan Pérez', 'USER'),
('maria', 'maria@email.com', 'maria123', 'María García', 'USER');