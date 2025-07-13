package com.web.tienda.dto;

import java.math.BigDecimal;

public class DetalleVentaResponseDTO {

    private Integer idProducto;
    private String nombreProducto;
    private int cantidad;
    private BigDecimal precio;
    private BigDecimal total;

    public DetalleVentaResponseDTO(Integer idProducto, String nombreProducto, int cantidad, BigDecimal precio, BigDecimal total) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.precio = precio;
        this.total = total;
    }


    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
