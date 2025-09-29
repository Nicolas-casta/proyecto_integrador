package com.grupo1.biblioteca.service;

import com.grupo1.biblioteca.dto.UserDto;
import com.grupo1.biblioteca.model.User;
import com.grupo1.biblioteca.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Obtener todos los usuarios
    public List<UserDto.Response> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    // Obtener usuario por ID
    public UserDto.Response getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
        return convertToResponse(user);
    }

    // Crear nuevo usuario
    public UserDto.Response createUser(UserDto.Create dto) {
        // Verificar si el username ya existe
        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new RuntimeException("El username ya existe: " + dto.getUsername());
        }

        // Verificar si el email ya existe
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("El email ya existe: " + dto.getEmail());
        }

        // Crear nuevo usuario
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPasswordHash(dto.getPassword()); // Aquí normalmente se encriptaría
        user.setFullName(dto.getFullName());
        user.setRole(dto.getRole() != null ? dto.getRole() : "USER");

        // Guardar en BD
        User savedUser = userRepository.save(user);
        return convertToResponse(savedUser);
    }

    // Actualizar usuario
    public UserDto.Response updateUser(Long id, UserDto.Create dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));

        // Actualizar campos
        if (dto.getUsername() != null) {
            user.setUsername(dto.getUsername());
        }
        if (dto.getEmail() != null) {
            user.setEmail(dto.getEmail());
        }
        if (dto.getFullName() != null) {
            user.setFullName(dto.getFullName());
        }
        if (dto.getRole() != null) {
            user.setRole(dto.getRole());
        }

        User updatedUser = userRepository.save(user);
        return convertToResponse(updatedUser);
    }

    // Eliminar usuario
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado con ID: " + id);
        }
        userRepository.deleteById(id);
    }

    // Buscar por username
    public UserDto.Response getUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + username));
        return convertToResponse(user);
    }

    // Método auxiliar: Convertir User a UserDto.Response
    private UserDto.Response convertToResponse(User user) {
        UserDto.Response response = new UserDto.Response();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setFullName(user.getFullName());
        response.setRole(user.getRole());
        return response;
    }
}