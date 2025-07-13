package com.web.tienda.dto;

public class CategoriaResponseDTO {
    private Integer idCategoria;
    private String nombre;

    public CategoriaResponseDTO(){
        
    }

    public CategoriaResponseDTO(Integer idCategoria, String nombre) {
        this.idCategoria = idCategoria;
        this.nombre = nombre;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public String getNombre() {
        return nombre;
    }
}
