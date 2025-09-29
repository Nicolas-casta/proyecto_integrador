package com.grupo1.biblioteca.repository;

import com.grupo1.biblioteca.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    // Buscar préstamos por usuario
    List<Loan> findByUserId(Long userId);

    // Buscar préstamos por libro
    List<Loan> findByBookId(Long bookId);

    // Buscar préstamos por estado
    List<Loan> findByStatus(String status);

    // Buscar préstamos activos de un usuario
    List<Loan> findByUserIdAndStatus(Long userId, String status);

    // Buscar préstamos activos de un libro
    List<Loan> findByBookIdAndStatus(Long bookId, String status);
}