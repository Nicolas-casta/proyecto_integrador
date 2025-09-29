package com.grupo1.biblioteca.service;

import com.grupo1.biblioteca.dto.LoanDto;
import com.grupo1.biblioteca.model.Book;
import com.grupo1.biblioteca.model.Loan;
import com.grupo1.biblioteca.model.User;
import com.grupo1.biblioteca.repository.BookRepository;
import com.grupo1.biblioteca.repository.LoanRepository;
import com.grupo1.biblioteca.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    // Obtener todos los préstamos
    public List<LoanDto.Response> getAllLoans() {
        return loanRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    // Obtener préstamo por ID
    public LoanDto.Response getLoanById(Long id) {
        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Préstamo no encontrado con ID: " + id));
        return convertToResponse(loan);
    }

    // Crear nuevo préstamo
    public LoanDto.Response createLoan(LoanDto.Create dto) {
        // Buscar usuario
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + dto.getUserId()));

        // Buscar libro
        Book book = bookRepository.findById(dto.getBookId())
                .orElseThrow(() -> new RuntimeException("Libro no encontrado con ID: " + dto.getBookId()));

        // Verificar si hay copias disponibles
        if (!book.isAvailable()) {
            throw new RuntimeException("No hay copias disponibles del libro: " + book.getTitle());
        }

        // Crear préstamo
        Loan loan = new Loan();
        loan.setUser(user);
        loan.setBook(book);
        loan.setLoanDate(LocalDateTime.now());
        loan.setDueDate(LocalDateTime.now().plusDays(14)); // 14 días de préstamo
        loan.setStatus("ACTIVE");

        // Reducir copias disponibles
        book.setCopiesAvailable(book.getCopiesAvailable() - 1);
        bookRepository.save(book);

        // Guardar préstamo
        Loan savedLoan = loanRepository.save(loan);
        return convertToResponse(savedLoan);
    }

    // Devolver libro (marcar préstamo como devuelto)
    public LoanDto.Response returnLoan(Long loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Préstamo no encontrado con ID: " + loanId));

        // Verificar que esté activo
        if (!"ACTIVE".equals(loan.getStatus())) {
            throw new RuntimeException("El préstamo ya fue devuelto o cancelado");
        }

        // Marcar como devuelto
        loan.setStatus("RETURNED");
        loan.setReturnDate(LocalDateTime.now());

        // Aumentar copias disponibles
        Book book = loan.getBook();
        book.setCopiesAvailable(book.getCopiesAvailable() + 1);
        bookRepository.save(book);

        // Guardar cambios
        Loan updatedLoan = loanRepository.save(loan);
        return convertToResponse(updatedLoan);
    }

    // Obtener préstamos de un usuario
    public List<LoanDto.Response> getLoansByUser(Long userId) {
        return loanRepository.findByUserId(userId).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    // Obtener préstamos activos de un usuario
    public List<LoanDto.Response> getActiveLoansByUser(Long userId) {
        return loanRepository.findByUserIdAndStatus(userId, "ACTIVE").stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    // Obtener préstamos de un libro
    public List<LoanDto.Response> getLoansByBook(Long bookId) {
        return loanRepository.findByBookId(bookId).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    // Método auxiliar: Convertir Loan a LoanDto.Response
    private LoanDto.Response convertToResponse(Loan loan) {
        LoanDto.Response response = new LoanDto.Response();
        response.setId(loan.getId());
        response.setUserId(loan.getUser().getId());
        response.setUsername(loan.getUser().getUsername());
        response.setBookId(loan.getBook().getId());
        response.setBookTitle(loan.getBook().getTitle());
        response.setLoanDate(loan.getLoanDate());
        response.setDueDate(loan.getDueDate());
        response.setReturnDate(loan.getReturnDate());
        response.setStatus(loan.getStatus());
        return response;
    }
}