package com.grupo1.biblioteca.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LoanServiceTest {

    @Autowired
    private LoanService loanService;

    @Test
    void testGetAllLoans() {
        var loans = loanService.getAllLoans();
        assertNotNull(loans);
    }
}