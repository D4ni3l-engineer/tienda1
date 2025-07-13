package com.web.tienda.service;

import java.util.List;

import com.web.tienda.dto.ClienteRequestDTO;
import com.web.tienda.dto.ClienteResponseDTO;

public interface ClienteService {
    ClienteResponseDTO guardar(ClienteRequestDTO dto);
    ClienteResponseDTO actualizar(ClienteRequestDTO dto);
    List<ClienteResponseDTO> listar();
    ClienteResponseDTO obtenerPorId(Integer id);
    void eliminar(Integer id);
}
