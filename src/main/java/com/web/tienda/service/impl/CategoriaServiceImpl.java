package com.web.tienda.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.tienda.dto.CategoriaRequestDTO;
import com.web.tienda.dto.CategoriaResponseDTO;
import com.web.tienda.exception.ResourceNotFoundException;
import com.web.tienda.model.Categoria;
import com.web.tienda.repository.CategoriaRepository;
import com.web.tienda.service.CategoriaService;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public CategoriaResponseDTO guardar(CategoriaRequestDTO dto) {
        Categoria categoria = new Categoria();
        categoria.setNombre(dto.getNombre());
        categoriaRepository.save(categoria);
        return new CategoriaResponseDTO(categoria.getIdCategoria(), categoria.getNombre());
    }

    @Override
    public CategoriaResponseDTO actualizar( CategoriaRequestDTO dto) {
        Categoria categoria = categoriaRepository.findById(dto.getIdCategoria())
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con ID: " + dto.getIdCategoria()));
        categoria.setNombre(dto.getNombre());
        categoriaRepository.save(categoria);
        return new CategoriaResponseDTO(categoria.getIdCategoria(), categoria.getNombre());
    }

    @Override
    public List<CategoriaResponseDTO> listar() {
        return categoriaRepository.findAll()
                .stream()
                .map(c -> new CategoriaResponseDTO(c.getIdCategoria(), c.getNombre()))
                .collect(Collectors.toList());
    }

    @Override
    public CategoriaResponseDTO obtenerPorId(Integer id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con ID: " + id));
        return new CategoriaResponseDTO(categoria.getIdCategoria(), categoria.getNombre());
    }

    @Override
    public void eliminar(Integer id) {
        if (!categoriaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Categoría no encontrada con ID: " + id);
        }
        categoriaRepository.deleteById(id);
    }
}
