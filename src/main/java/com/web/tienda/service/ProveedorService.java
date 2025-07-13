package com.web.tienda.service;

import java.util.List;

import com.web.tienda.dto.ProveedorRequestDTO;
import com.web.tienda.dto.ProveedorResponseDTO;

public interface ProveedorService {
    ProveedorResponseDTO guardar(ProveedorRequestDTO dto);
    ProveedorResponseDTO actualizar(ProveedorRequestDTO dto);
    List<ProveedorResponseDTO> listar();
    ProveedorResponseDTO obtenerPorId(Integer id);
    void eliminar(Integer id);
}