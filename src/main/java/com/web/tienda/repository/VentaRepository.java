package com.web.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.tienda.model.Venta;

public interface VentaRepository extends JpaRepository<Venta, Integer> {}