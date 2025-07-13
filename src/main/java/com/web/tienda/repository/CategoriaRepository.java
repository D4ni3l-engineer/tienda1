package com.web.tienda.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.web.tienda.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {}