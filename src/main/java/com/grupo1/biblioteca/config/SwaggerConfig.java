package com.grupo1.biblioteca.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SwaggerConfig {
    @GetMapping("/")
    public String inicio() {
        return "redirect:/swagger-ui/index.html";
    }
}
