package com.web.tienda.service;

import java.util.List;

import com.web.tienda.dto.UsuarioRequestDTO;
import com.web.tienda.dto.UsuarioResponseDTO;

public interface UsuarioService {
    UsuarioResponseDTO guardar(UsuarioRequestDTO dto);
    UsuarioResponseDTO actualizar(UsuarioRequestDTO dto);
    List<UsuarioResponseDTO> listar();
    UsuarioResponseDTO obtenerPorId(Integer id);
    void eliminar(Integer id);
}
