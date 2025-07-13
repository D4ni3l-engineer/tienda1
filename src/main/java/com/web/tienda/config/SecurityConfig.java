package com.web.tienda.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable() // Desactiva CSRF si estás usando JWT
            .authorizeRequests()
                .antMatchers("/public/**", "/login", "/signup").permitAll() // Rutas públicas
                .antMatchers(HttpMethod.GET, "/api/**").permitAll() // Rutas GET públicas
                .anyRequest().authenticated() // Cualquier otra ruta requiere autenticación
            .and()
            .addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class); // Filtro para JWT
    }

    // Configura la autenticación (puedes integrarlo con JWT o base de datos)
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Configuración para la autenticación en memoria, puedes cambiar esto por JWT o DB
        auth.inMemoryAuthentication()
            .withUser("user").password("{noop}password").roles("USER")
            .and()
            .withUser("admin").password("{noop}admin").roles("ADMIN");
    }
}
