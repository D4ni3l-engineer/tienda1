package com.web.tienda.dto;

import java.math.BigDecimal;

public class ProductoResponseDTO {
    private Integer idProducto;
    private String nombre;
    private BigDecimal precioVenta;
    private BigDecimal precioCompra;
    private Integer stock;
    private String imagen;
    private String nombreCategoria;
    private String nombreProveedor;
    private Integer idCategoria;
    private Integer idProveedor;

    public ProductoResponseDTO(Integer idProducto, String nombre, BigDecimal precioVenta, BigDecimal precioCompra,
                               Integer stock, String imagen,
                               Integer idCategoria,
                               Integer idProveedor,String nombreProveedor, String nombreCategoria) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.precioVenta = precioVenta;
        this.precioCompra = precioCompra;
        this.stock = stock;
        this.imagen = imagen;
        this.idCategoria = idCategoria;
        this.idProveedor = idProveedor;
        this.nombreProveedor=nombreProveedor;
        this.nombreCategoria=nombreCategoria;
    }

    public ProductoResponseDTO(Integer idProducto, String nombre, BigDecimal precioVenta, BigDecimal precioCompra,
                               Integer stock, String imagen,
                               Integer idCategoria,
                               Integer idProveedor) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.precioVenta = precioVenta;
        this.precioCompra = precioCompra;
        this.stock = stock;
        this.imagen = imagen;
        this.idCategoria = idCategoria;
        this.idProveedor = idProveedor;
    }

    // Getters
    public Integer getIdProducto() {
        return idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public BigDecimal getPrecioVenta() {
        return precioVenta;
    }

    public BigDecimal getPrecioCompra() {
        return precioCompra;
    }

    public Integer getStock() {
        return stock;
    }

    public String getImagen() {
        return imagen;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }
}