package com.web.tienda.dto;


public class ProveedorResponseDTO {
    private Integer idProveedor;
    private String razonSocial;
    private String direccion;
    private String pagWeb;
    private String telefono;
    private String correo;

    public ProveedorResponseDTO(){
        
    }

    public ProveedorResponseDTO(Integer idProveedor, String razonSocial, String direccion, String pagWeb, String telefono, String correo) {
        this.idProveedor = idProveedor;
        this.razonSocial = razonSocial;
        this.direccion = direccion;
        this.pagWeb = pagWeb;
        this.telefono = telefono;
        this.correo = correo;
    }

    public ProveedorResponseDTO(Integer idProveedor, String razonSocial) {
        this.idProveedor = idProveedor;
        this.razonSocial = razonSocial;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getPagWeb() {
        return pagWeb;
    }

    public String getTelefono() {
        return telefono;
    }


    public String getCorreo() {
        return correo;
    }
}

