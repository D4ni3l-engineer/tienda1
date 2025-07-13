package com.web.tienda.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.web.tienda.security.CustomAccessDeniedHandler;
import com.web.tienda.security.CustomAuthenticationEntryPoint;
import com.web.tienda.security.JwtRequestFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtRequestFilter jwtRequestFilter;

    @Autowired
    private CustomAuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private CustomAccessDeniedHandler accessDeniedHandler;

    SecurityConfig(JwtRequestFilter jwtRequestFilter) {
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(authz -> authz
            .requestMatchers(
                "/", 
                "/home/", 
                "/home/login", 
                "/auth/**",
                "/css/**",
                "/js/**", 
                "/imagen/**", 
                "/webjars/**",
                "/uploads/**"
            ).permitAll()
            .requestMatchers("/usuarios/**").hasAuthority("ROLE_ADMIN")
            .requestMatchers("/categorias/**", "/productos/**", "/proveedores/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_ALMACENERO")
            .requestMatchers("/clientes/**", "/ventas/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_VENDEDOR")
            .anyRequest().authenticated()
        )
        .exceptionHandling(ex -> ex
            .accessDeniedHandler(accessDeniedHandler) 
            .authenticationEntryPoint(authenticationEntryPoint)
        )
        .sessionManagement(sess -> 
            sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

    http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
}

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)
            throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
