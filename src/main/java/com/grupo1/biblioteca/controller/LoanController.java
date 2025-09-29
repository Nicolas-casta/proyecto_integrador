package com.grupo1.biblioteca.controller;

import com.grupo1.biblioteca.dto.LoanDto;
import com.grupo1.biblioteca.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    // GET /api/loans - Obtener todos los préstamos
    @GetMapping
    public ResponseEntity<List<LoanDto.Response>> getAllLoans() {
        List<LoanDto.Response> loans = loanService.getAllLoans();
        return ResponseEntity.ok(loans);
    }

    // GET /api/loans/{id} - Obtener préstamo por ID
    @GetMapping("/{id}")
    public ResponseEntity<LoanDto.Response> getLoanById(@PathVariable Long id) {
        try {
            LoanDto.Response loan = loanService.getLoanById(id);
            return ResponseEntity.ok(loan);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // POST /api/loans - Crear nuevo préstamo
    @PostMapping
    public ResponseEntity<LoanDto.Response> createLoan(@RequestBody LoanDto.Create dto) {
        try {
            LoanDto.Response newLoan = loanService.createLoan(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(newLoan);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // PUT /api/loans/{id}/return - Devolver libro
    @PutMapping("/{id}/return")
    public ResponseEntity<LoanDto.Response> returnLoan(@PathVariable Long id) {
        try {
            LoanDto.Response returnedLoan = loanService.returnLoan(id);
            return ResponseEntity.ok(returnedLoan);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // GET /api/loans/user/{userId} - Préstamos de un usuario
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<LoanDto.Response>> getLoansByUser(@PathVariable Long userId) {
        List<LoanDto.Response> loans = loanService.getLoansByUser(userId);
        return ResponseEntity.ok(loans);
    }

    // GET /api/loans/user/{userId}/active - Préstamos activos de un usuario
    @GetMapping("/user/{userId}/active")
    public ResponseEntity<List<LoanDto.Response>> getActiveLoansByUser(@PathVariable Long userId) {
        List<LoanDto.Response> loans = loanService.getActiveLoansByUser(userId);
        return ResponseEntity.ok(loans);
    }

    // GET /api/loans/book/{bookId} - Préstamos de un libro
    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<LoanDto.Response>> getLoansByBook(@PathVariable Long bookId) {
        List<LoanDto.Response> loans = loanService.getLoansByBook(bookId);
        return ResponseEntity.ok(loans);
    }
}