package com.web.tienda.service;


import java.util.List;

import com.web.tienda.dto.CategoriaRequestDTO;
import com.web.tienda.dto.CategoriaResponseDTO;

public interface CategoriaService {
    CategoriaResponseDTO guardar(CategoriaRequestDTO dto);
    CategoriaResponseDTO actualizar(CategoriaRequestDTO dto);
    List<CategoriaResponseDTO> listar();
    CategoriaResponseDTO obtenerPorId(Integer id);
    void eliminar(Integer id);
}
