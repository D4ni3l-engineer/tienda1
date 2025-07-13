package com.web.tienda.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.tienda.model.Rol;
import com.web.tienda.repository.RolRepository;
import com.web.tienda.service.RolService;

import java.util.List;

@Service
public class RolServiceImpl implements RolService {

    @Autowired
    private RolRepository rolRepository;

    @Override
    public List<Rol> listar() {
        return rolRepository.findAll();
    }

    @Override
    public Rol obtenerPorId(Integer id) {
        return rolRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Rol no encontrado con ID: " + id));
    }

    @Override
    public Rol obtenerPorNombre(String nombre) {
        Rol rol = rolRepository.findByNombre(nombre);
        if (rol == null) {
            throw new RuntimeException("Rol no encontrado con nombre: " + nombre);
        }
        return rol;
    }
}
