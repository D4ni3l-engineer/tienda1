package com.web.tienda.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CategoriaRequestDTO {
    private Integer idCategoria;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 40, message = "El nombre no puede tener más de 40 caracteres")
    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

     public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }
}