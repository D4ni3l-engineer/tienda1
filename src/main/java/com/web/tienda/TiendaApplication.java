package com.web.tienda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class TiendaApplication implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(TiendaApplication.class, args);
	}

	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Permitir acceso a archivos de la carpeta uploads/
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:uploads/");
    }

}
