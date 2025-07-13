package com.web.tienda.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.tienda.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByCorreo(String correo);
}
