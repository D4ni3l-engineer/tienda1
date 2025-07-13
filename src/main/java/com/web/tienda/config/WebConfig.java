package com.web.tienda.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Permite CORS (Cross-Origin Resource Sharing) para todas las rutas
        registry.addMapping("/**").allowedOrigins("http://localhost:3000"); // Cambia la URL por la de tu frontend
    }

    // Puedes agregar más configuraciones, por ejemplo, recursos estáticos o interceptores
}
