package com.web.tienda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.tienda.model.DetalleVenta;

@Repository
public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Integer> {
    List<DetalleVenta> findByVenta_IdVenta(Integer idVenta);
}