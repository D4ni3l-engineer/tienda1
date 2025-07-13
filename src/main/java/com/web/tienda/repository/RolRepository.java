package com.web.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.tienda.model.Rol;

public interface RolRepository extends JpaRepository<Rol, Integer> {
    Rol findByNombre(String nombre);
}
