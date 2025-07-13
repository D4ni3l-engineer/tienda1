package com.web.tienda.dto;

public class JwtResponseDTO {

    private String token;
    private String rol;
    private String correo;

    public JwtResponseDTO(String token, String rol, String correo) {
        this.token = token;
        this.rol = rol;
        this.correo = correo;
    }

    public String getToken() {
        return token;
    }

    public String getRol() {
        return rol;
    }

    public String getCorreo() {
        return correo;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}