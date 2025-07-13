package com.web.tienda.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.web.tienda.model.Bitacora;

public interface BitacoraRepository extends JpaRepository<Bitacora, Integer> {
}