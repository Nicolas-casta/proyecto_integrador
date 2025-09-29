package com.grupo1.biblioteca.repository;

import com.grupo1.biblioteca.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Buscar usuario por username
    Optional<User> findByUsername(String username);

    // Buscar usuario por email
    Optional<User> findByEmail(String email);

    // Verificar si existe un username
    boolean existsByUsername(String username);

    // Verificar si existe un email
    boolean existsByEmail(String email);
}