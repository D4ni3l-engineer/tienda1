package com.web.tienda.service;

import java.util.List;

import com.web.tienda.dto.DetalleVentaResponseDTO;
import com.web.tienda.dto.VentaRequestDTO;
import com.web.tienda.dto.VentaResponseDTO;

public interface VentaService {
    VentaResponseDTO guardar(VentaRequestDTO dto);
    VentaResponseDTO actualizar(VentaRequestDTO dto);
    List<VentaResponseDTO> listar();
    VentaResponseDTO obtenerPorId(Integer id);
    void eliminar(Integer id);
    List<DetalleVentaResponseDTO> obtenerDetallesPorIdVenta(Integer idVenta);
}
