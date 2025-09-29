package com.grupo1.biblioteca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BibliotecaApplication {

    public static void main(String[] args) {
        SpringApplication.run(BibliotecaApplication.class, args);
        System.out.println("🚀 Biblioteca Virtual iniciada en: http://localhost:8080");
        System.out.println("📚 Swagger UI disponible en: http://localhost:8080/swagger-ui.html");
    }
}