package com.grupo1.biblioteca.controller;

import com.grupo1.biblioteca.dto.UserDto;
import com.grupo1.biblioteca.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // GET /api/users - Obtener todos los usuarios
    @GetMapping
    public ResponseEntity<List<UserDto.Response>> getAllUsers() {
        List<UserDto.Response> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // GET /api/users/{id} - Obtener usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<UserDto.Response> getUserById(@PathVariable Long id) {
        try {
            UserDto.Response user = userService.getUserById(id);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // POST /api/users - Crear nuevo usuario
    @PostMapping
    public ResponseEntity<UserDto.Response> createUser(@RequestBody UserDto.Create dto) {
        try {
            UserDto.Response newUser = userService.createUser(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // PUT /api/users/{id} - Actualizar usuario
    @PutMapping("/{id}")
    public ResponseEntity<UserDto.Response> updateUser(@PathVariable Long id, @RequestBody UserDto.Create dto) {
        try {
            UserDto.Response updatedUser = userService.updateUser(id, dto);
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /api/users/{id} - Eliminar usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // GET /api/users/username/{username} - Buscar por username
    @GetMapping("/username/{username}")
    public ResponseEntity<UserDto.Response> getUserByUsername(@PathVariable String username) {
        try {
            UserDto.Response user = userService.getUserByUsername(username);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}