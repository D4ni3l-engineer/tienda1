package com.web.tienda.dto;

public class LoginRequestDTO {

    private String correo;
    private String contrasenia;

    public LoginRequestDTO() {
    }

    public LoginRequestDTO(String correo, String contrasenia) {
        this.correo = correo;
        this.contrasenia = contrasenia;
    }

    // Getters
    public String getCorreo() {
        return correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    // Setters
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
}
