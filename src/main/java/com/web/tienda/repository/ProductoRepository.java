package com.web.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.tienda.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {}

