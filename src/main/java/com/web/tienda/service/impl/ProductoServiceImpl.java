package com.web.tienda.service.impl;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.tienda.dto.ProductoRequestDTO;
import com.web.tienda.dto.ProductoResponseDTO;
import com.web.tienda.exception.ResourceNotFoundException;
import com.web.tienda.model.Categoria;
import com.web.tienda.model.Producto;
import com.web.tienda.model.Proveedor;
import com.web.tienda.repository.CategoriaRepository;
import com.web.tienda.repository.ProductoRepository;
import com.web.tienda.repository.ProveedorRepository;
import com.web.tienda.service.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Override
    public ProductoResponseDTO guardar(ProductoRequestDTO dto) {
        Categoria categoria = categoriaRepository.findById(dto.getIdCategoria())
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada"));
        Proveedor proveedor = proveedorRepository.findById(dto.getIdProveedor())
                .orElseThrow(() -> new ResourceNotFoundException("Proveedor no encontrado"));

        Producto producto = new Producto();
        producto.setNombre(dto.getNombre());
        producto.setPrecioVenta(dto.getPrecioVenta());
        producto.setPrecioCompra(dto.getPrecioCompra());
        producto.setStock(dto.getStock());
        producto.setImagen(dto.getImagen());
        producto.setCategoria(categoria);
        producto.setProveedor(proveedor);

        productoRepository.save(producto);

        return new ProductoResponseDTO( producto.getIdProducto(),producto.getNombre(),producto.getPrecioVenta(),producto.getPrecioCompra(),producto.getStock(),producto.getImagen(),categoria.getIdCategoria(),proveedor.getIdProveedor());
    }

    @Override
    public ProductoResponseDTO actualizar(ProductoRequestDTO dto) {
        Producto producto = productoRepository.findById(dto.getIdProducto())
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));

        Categoria categoria = categoriaRepository.findById(dto.getIdCategoria())
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada"));
        Proveedor proveedor = proveedorRepository.findById(dto.getIdProveedor())
                .orElseThrow(() -> new ResourceNotFoundException("Proveedor no encontrado"));

        producto.setNombre(dto.getNombre());
        producto.setPrecioVenta(dto.getPrecioVenta());
        producto.setPrecioCompra(dto.getPrecioCompra());
        producto.setStock(dto.getStock());
        producto.setImagen(dto.getImagen());
        producto.setCategoria(categoria);
        producto.setProveedor(proveedor);

        productoRepository.save(producto);

        return new ProductoResponseDTO( producto.getIdProducto(),producto.getNombre(),producto.getPrecioVenta(),producto.getPrecioCompra(),producto.getStock(),producto.getImagen(),categoria.getIdCategoria(),proveedor.getIdProveedor());
    }

    @Override
    public List<ProductoResponseDTO> listar() {
        return productoRepository.findAll().stream()
                .map(p -> new ProductoResponseDTO(
                        p.getIdProducto(),
                        p.getNombre(),
                        p.getPrecioVenta(),
                        p.getPrecioCompra(),
                        p.getStock(),
                        p.getImagen(),
                        p.getCategoria().getIdCategoria(),
                        p.getProveedor().getIdProveedor(),p.getProveedor().getRazonSocial(),p.getCategoria().getNombre())).collect(Collectors.toList());
    }

    @Override
    public ProductoResponseDTO obtenerPorId(Integer id) {
        Producto p = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));
        return new ProductoResponseDTO(
                p.getIdProducto(),
                p.getNombre(),
                p.getPrecioVenta(),
                p.getPrecioCompra(),
                p.getStock(),
                p.getImagen(),
                p.getCategoria().getIdCategoria(),
               p.getProveedor().getIdProveedor());
    }

    @Override
    public void eliminar(Integer id) {
        if (!productoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Producto no encontrado");
        }
        productoRepository.deleteById(id);
    }
}
