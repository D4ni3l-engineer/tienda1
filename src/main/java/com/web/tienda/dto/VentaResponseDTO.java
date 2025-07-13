package com.web.tienda.dto;

import java.time.LocalDate;

public class VentaResponseDTO {
    private Integer idVenta;
    private String clienteNombre;
    private LocalDate fechaVenta;
    private String observacion;

    public VentaResponseDTO(Integer idVenta, String clienteNombre, LocalDate fechaVenta, String observacion) {
        this.idVenta = idVenta;
        this.clienteNombre = clienteNombre;
        this.fechaVenta = fechaVenta;
        this.observacion = observacion;
    }

    // Getters
    public Integer getIdVenta() {
        return idVenta;
    }

    public String getClienteNombre() {
        return clienteNombre;
    }

    public LocalDate getFechaVenta() {
        return fechaVenta;
    }

    public String getObservacion() {
        return observacion;
    }
}
