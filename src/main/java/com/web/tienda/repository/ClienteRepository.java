package com.web.tienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.tienda.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {}
