package com.web.tienda.dto;

public class UsuarioResponseDTO {
    private Integer idUsuario;
    private String nombre;
    private String apellidoP;
    private String apellidoM;
    private String correo;
    private String nombreRol;
    private Integer idRol;

   public UsuarioResponseDTO() {
    }

    public UsuarioResponseDTO(Integer idUsuario, String nombre, String apellidoP, String apellidoM, String correo, String nombreRol) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellidoP = apellidoP;
        this.apellidoM = apellidoM;
        this.correo = correo;
        this.nombreRol = nombreRol;
    }

    public UsuarioResponseDTO(Integer idUsuario, String nombre, String apellidoP, String apellidoM, String correo, Integer idRol) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellidoP = apellidoP;
        this.apellidoM = apellidoM;
        this.correo = correo;
        this.idRol = idRol;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoP() {
        return apellidoP;
    }

    public void setApellidoP(String apellidoP) {
        this.apellidoP = apellidoP;
    }

    public String getApellidoM() {
        return apellidoM;
    }

    public void setApellidoM(String apellidoM) {
        this.apellidoM = apellidoM;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

}