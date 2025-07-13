package com.web.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.tienda.model.Proveedor;

public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {}