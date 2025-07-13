package com.web.tienda.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.tienda.dto.DetalleVentaRequestDTO;
import com.web.tienda.dto.DetalleVentaResponseDTO;
import com.web.tienda.dto.VentaRequestDTO;
import com.web.tienda.dto.VentaResponseDTO;
import com.web.tienda.exception.ResourceNotFoundException;
import com.web.tienda.model.Cliente;
import com.web.tienda.model.DetalleVenta;
import com.web.tienda.model.Producto;
import com.web.tienda.model.Venta;
import com.web.tienda.repository.ClienteRepository;
import com.web.tienda.repository.DetalleVentaRepository;
import com.web.tienda.repository.ProductoRepository;
import com.web.tienda.repository.VentaRepository;
import com.web.tienda.service.VentaService;

import jakarta.transaction.Transactional;

@Service
public class VentaServiceImpl implements VentaService {

        @Autowired
        private VentaRepository ventaRepository;

        @Autowired
        private ClienteRepository clienteRepository;

        @Autowired
        private DetalleVentaRepository detalleVentaRepository;

        @Autowired
        private ProductoRepository productoRepository;

        @Transactional
        @Override
        public VentaResponseDTO guardar(VentaRequestDTO dto) {
                Cliente cliente = clienteRepository.findById(dto.getIdCliente())
                                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));

                Venta venta = new Venta();
                venta.setCliente(cliente);
                venta.setFechaVenta(dto.getFechaVenta());
                venta.setObservacion(dto.getObservacion());

                ventaRepository.save(venta);

                for (DetalleVentaRequestDTO detDTO : dto.getDetalles()) {
                        DetalleVenta detalle = new DetalleVenta();
                        detalle.setVenta(venta);
                        detalle.setProducto(productoRepository.findById(detDTO.getIdProducto()).orElseThrow());
                        detalle.setCantidad(detDTO.getCantidad());
                        detalle.setPrecio(detDTO.getPrecio());
                        detalle.setTotal(detDTO.getTotal());
                        detalleVentaRepository.save(detalle);
                        Producto producto = productoRepository.findById(detDTO.getIdProducto()).orElseThrow();
                        int nuevoStock = producto.getStock() - detDTO.getCantidad();
                        producto.setStock(nuevoStock);
                        productoRepository.save(producto);
                }

                return new VentaResponseDTO(
                                venta.getIdVenta(),
                                cliente.getNombre(),
                                venta.getFechaVenta(),
                                venta.getObservacion());
        }

        @Override
        public VentaResponseDTO actualizar(VentaRequestDTO dto) {
                Venta venta = ventaRepository.findById(dto.getIdVenta())
                                .orElseThrow(() -> new ResourceNotFoundException("Venta no encontrada"));

                Cliente cliente = clienteRepository.findById(dto.getIdCliente())
                                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));

                venta.setCliente(cliente);
                venta.setFechaVenta(dto.getFechaVenta());
                venta.setObservacion(dto.getObservacion());

                ventaRepository.save(venta);

                return new VentaResponseDTO(
                                venta.getIdVenta(),
                                cliente.getNombre(),
                                venta.getFechaVenta(),
                                venta.getObservacion());
        }

        @Override
        public List<DetalleVentaResponseDTO> obtenerDetallesPorIdVenta(Integer idVenta) {
                List<DetalleVenta> detalles = detalleVentaRepository.findByVenta_IdVenta(idVenta);

                return detalles.stream().map(det -> new DetalleVentaResponseDTO(
                        det.getProducto().getIdProducto(),
                        det.getProducto().getNombre(),
                        det.getCantidad(),
                        det.getPrecio(),
                        det.getTotal()
                )).toList();
        }

        @Override
        public List<VentaResponseDTO> listar() {
                return ventaRepository.findAll()
                                .stream()
                                .map(v -> new VentaResponseDTO(
                                                v.getIdVenta(),
                                                v.getCliente().getNombre(),
                                                v.getFechaVenta(),
                                                v.getObservacion()))
                                .collect(Collectors.toList());
        }

        @Override
        public VentaResponseDTO obtenerPorId(Integer id) {
                Venta venta = ventaRepository.findById(id)
                                .orElseThrow(() -> new ResourceNotFoundException("Venta no encontrada"));
                return new VentaResponseDTO(
                                venta.getIdVenta(),
                                venta.getCliente().getNombre(),
                                venta.getFechaVenta(),
                                venta.getObservacion());
        }

        @Override
        public void eliminar(Integer id) {
                ventaRepository.deleteById(id);
        }


        
}
