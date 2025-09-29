CREATE TABLE loans (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    book_id BIGINT NOT NULL,
    loan_date TIMESTAMP NOT NULL,
    due_date TIMESTAMP NOT NULL,
    return_date TIMESTAMP NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',

    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (book_id) REFERENCES books(id)
);

INSERT INTO loans (user_id, book_id, loan_date, due_date, status) VALUES
(2, 1, NOW(), DATE_ADD(NOW(), INTERVAL 14 DAY), 'ACTIVE'),
(3, 4, NOW() - INTERVAL 5 DAY, DATE_ADD(NOW() - INTERVAL 5 DAY, INTERVAL 14 DAY), 'ACTIVE');