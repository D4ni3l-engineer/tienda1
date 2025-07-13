package com.web.tienda.service.impl;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.tienda.dto.ProveedorRequestDTO;
import com.web.tienda.dto.ProveedorResponseDTO;
import com.web.tienda.exception.ResourceNotFoundException;
import com.web.tienda.model.Proveedor;
import com.web.tienda.repository.ProveedorRepository;
import com.web.tienda.service.ProveedorService;


@Service
public class ProveedorServiceImpl implements ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Override
    public ProveedorResponseDTO guardar(ProveedorRequestDTO dto) {
        Proveedor p = new Proveedor();
        p.setRazonSocial(dto.getRazonSocial());
        p.setDireccion(dto.getDireccion());
        p.setPagWeb(dto.getPagWeb());
        p.setTelefono(dto.getTelefono());
        p.setCorreo(dto.getCorreo());

        proveedorRepository.save(p);
        return new ProveedorResponseDTO(
                    p.getIdProveedor(),
                    p.getRazonSocial(),
                    p.getDireccion(),
                    p.getPagWeb(),
                    p.getTelefono(),
                    p.getCorreo());
    }

    @Override
    public ProveedorResponseDTO actualizar(ProveedorRequestDTO dto) {
        Proveedor p = proveedorRepository.findById(dto.getIdProveedor())
                .orElseThrow(() -> new ResourceNotFoundException("Proveedor no encontrado con ID: " + dto.getIdProveedor()));

        p.setRazonSocial(dto.getRazonSocial());
        p.setDireccion(dto.getDireccion());
        p.setPagWeb(dto.getPagWeb());
        p.setTelefono(dto.getTelefono());
        p.setCorreo(dto.getCorreo());

        proveedorRepository.save(p);
        return new ProveedorResponseDTO(
                    p.getIdProveedor(),
                    p.getRazonSocial(),
                    p.getDireccion(),
                    p.getPagWeb(),
                    p.getTelefono(),
                    p.getCorreo());
    }

    @Override
    public List<ProveedorResponseDTO> listar() {
        return proveedorRepository.findAll()
                .stream()
                .map(p -> new ProveedorResponseDTO(
                    p.getIdProveedor(),
                    p.getRazonSocial(),
                    p.getDireccion(),
                    p.getPagWeb(),
                    p.getTelefono(),
                    p.getCorreo()))
                .collect(Collectors.toList());
    }

    @Override
    public ProveedorResponseDTO obtenerPorId(Integer id) {
        Proveedor p = proveedorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Proveedor no encontrado con ID: " + id));
        return new ProveedorResponseDTO(
                    p.getIdProveedor(),
                    p.getRazonSocial(),
                    p.getDireccion(),
                    p.getPagWeb(),
                    p.getTelefono(),
                    p.getCorreo()
            );
    }

    @Override
    public void eliminar(Integer id) {
        if (!proveedorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Proveedor no encontrado con ID: " + id);
        }
        proveedorRepository.deleteById(id);
    }
}
