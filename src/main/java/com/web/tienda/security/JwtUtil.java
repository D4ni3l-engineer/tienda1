package com.web.tienda.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    // Se debe almacenar esta clave de forma segura y no hardcodeada
    private String jwtSecret = "your_jwt_secret_key"; // Cambia esta clave por una más segura
    private long jwtExpirationMs = 86400000; // 24 horas de expiración

    // Método para generar el token JWT
    public String generateToken(String username) {
        // Fecha de expiración
        Date expirationDate = new Date(System.currentTimeMillis() + jwtExpirationMs);

        // Genera el token JWT
        return Jwts.builder()
                .setSubject(username) // Username como "subject" del JWT
                .setIssuedAt(new Date()) // Fecha de emisión
                .setExpiration(expirationDate) // Fecha de expiración
                .signWith(SignatureAlgorithm.HS512, jwtSecret) // Firma con la clave secreta
                .compact(); // Lo compacta para crear el JWT
    }

    // Método para extraer el username del token JWT
    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    // Método para extraer las claims (información) del token JWT
    public Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
    }

    // Método para verificar si un token es válido
    public boolean validateToken(String token) {
        try {
            // Si el token no ha expirado y la firma es correcta, será válido
            return !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }

    // Método para verificar si el token ha expirado
    private boolean isTokenExpired(String token) {
        Date expiration = extractClaims(token).getExpiration();
        return expiration.before(new Date());
    }

    // Método para resolver el token del encabezado Authorization
    public String getTokenFromHeader(String header) {
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }
}
