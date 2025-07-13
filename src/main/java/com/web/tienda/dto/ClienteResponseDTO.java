package com.web.tienda.dto;

public class ClienteResponseDTO {
    private Integer idCliente;
    private String nombre;
    private String apellidoP;
    private String apellidoM;
    private String direccion;
    private String telefono;

    public ClienteResponseDTO(Integer idCliente, String nombre, String apellidoP, String apellidoM, String direccion, String telefono) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellidoP = apellidoP;
        this.apellidoM = apellidoM;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidoP() {
        return apellidoP;
    }

    public String getApellidoM() {
        return apellidoM;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }
}
