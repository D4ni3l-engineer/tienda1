package com.web.tienda.service;

import java.util.List;

import com.web.tienda.dto.ProductoRequestDTO;
import com.web.tienda.dto.ProductoResponseDTO;

public interface ProductoService {
    ProductoResponseDTO guardar(ProductoRequestDTO dto);
    ProductoResponseDTO actualizar(ProductoRequestDTO dto);
    List<ProductoResponseDTO> listar();
    ProductoResponseDTO obtenerPorId(Integer id);
    void eliminar(Integer id);
}
