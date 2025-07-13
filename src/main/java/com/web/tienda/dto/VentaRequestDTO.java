package com.web.tienda.dto;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.NotNull;

public class VentaRequestDTO {

    private Integer idVenta;

    @NotNull
    private Integer idCliente;

    @NotNull
    private LocalDate fechaVenta;

    private String observacion;

    private List<DetalleVentaRequestDTO> detalles;

    // Getters y setters
    public Integer getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Integer idVenta) {
        this.idVenta = idVenta;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public LocalDate getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(LocalDate fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public List<DetalleVentaRequestDTO> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleVentaRequestDTO> detalles) {
        this.detalles = detalles;
    }
}
