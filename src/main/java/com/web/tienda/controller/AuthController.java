package com.web.tienda.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.tienda.dto.LoginRequestDTO;
import com.web.tienda.security.JwtUtil;
import com.web.tienda.security.UserDetailsServiceImpl;
import com.web.tienda.service.LoginIntentoMemoriaService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private LoginIntentoMemoriaService intentoService;

    /*@PostMapping("/login")
    public void createAuthenticationToken(@ModelAttribute LoginRequestDTO loginRequest, HttpServletResponse response) throws IOException {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getCorreo(), loginRequest.getContrasenia())
            );
        } catch (BadCredentialsException e) {
            response.sendRedirect("/home/?error"); 
            return;
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getCorreo());
        final String jwt = jwtUtil.generateToken(userDetails);

        Cookie cookie = new Cookie("token", jwt);
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setPath("/");
        cookie.setMaxAge(24 * 60 * 60);
        response.addCookie(cookie);

        response.sendRedirect("/home/dashboard");
    }*/

    @PostMapping("/login")
    public void createAuthenticationToken(@ModelAttribute LoginRequestDTO loginRequest, HttpServletResponse response) throws IOException {
        String username = loginRequest.getCorreo();

        if (intentoService.estaBloqueado(username)) {
            response.sendRedirect("/home/?bloqueado"); 
            return;
        }

        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, loginRequest.getContrasenia())
            );
            intentoService.loginExitoso(username);
        } catch (BadCredentialsException e) {
            intentoService.loginFallido(username);
            response.sendRedirect("/home/?error");
            return;
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final String jwt = jwtUtil.generateToken(userDetails);

        Cookie cookie = new Cookie("token", jwt);
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setPath("/");
        cookie.setMaxAge(24 * 60 * 60);
        response.addCookie(cookie);

        response.sendRedirect("/home/dashboard");
    }

    @GetMapping("/logout")
    public void logout(HttpServletResponse response) throws IOException {
        Cookie cookie = new Cookie("token", null); 
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        response.sendRedirect("/home/");
    }
}
