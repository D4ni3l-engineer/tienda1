package com.web.tienda.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,AccessDeniedException accessDeniedException)
            throws IOException, ServletException {

        String accept = request.getHeader("Accept");

        if (accept != null && accept.contains("text/html")) {
            Cookie expiredCookie = new Cookie("token", "");
            expiredCookie.setHttpOnly(true);
            expiredCookie.setSecure(false);
            expiredCookie.setPath("/");
            expiredCookie.setMaxAge(0);
            response.addCookie(expiredCookie);
            response.sendRedirect("/home/");
        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Acceso denegado (403)");
        }
    }
}
