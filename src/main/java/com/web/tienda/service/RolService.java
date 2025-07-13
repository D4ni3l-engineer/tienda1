package com.web.tienda.service;

import java.util.List;

import com.web.tienda.model.Rol;

public interface RolService  {
    List<Rol> listar();
    Rol obtenerPorId(Integer id);
    Rol obtenerPorNombre(String nombre);
}
