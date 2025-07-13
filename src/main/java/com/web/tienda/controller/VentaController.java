package com.web.tienda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.tienda.annotations.BitacoraLog;
import com.web.tienda.dto.DetalleVentaRequestDTO;
import com.web.tienda.dto.DetalleVentaResponseDTO;
import com.web.tienda.dto.VentaRequestDTO;
import com.web.tienda.dto.VentaResponseDTO;
import com.web.tienda.service.ClienteService;
import com.web.tienda.service.ProductoService;
import com.web.tienda.service.VentaService;

@Controller
@RequestMapping("/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ProductoService productoService;

    @BitacoraLog(accion = "Listar ventas", modulo = "Ventas")
    @GetMapping
    public String listarVentas(Model model) {
        model.addAttribute("ventas", ventaService.listar());
        return "venta/lista";
    }

    @BitacoraLog(accion = "Mostrar formulario nueva venta", modulo = "Ventas")
    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("venta", new VentaRequestDTO());
        model.addAttribute("clientes", clienteService.listar());
        model.addAttribute("productos", productoService.listar());
        return "venta/nuevo";
    }

    @BitacoraLog(accion = "Registrar venta", modulo = "Ventas")
    @PostMapping("/guardar")
    public String guardarVenta(@ModelAttribute("venta") VentaRequestDTO dto,@RequestParam("detallesJson") String detallesJson,RedirectAttributes redirectAttributes,Model model) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<DetalleVentaRequestDTO> detalles = objectMapper.readValue(detallesJson, new TypeReference<>() {});
            dto.setDetalles(detalles);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error al procesar los detalles de la venta.");
            model.addAttribute("clientes", clienteService.listar());
            model.addAttribute("productos", productoService.listar());
            return "venta/nuevo";
        }

        if (dto.getIdCliente() == null || dto.getFechaVenta() == null) {
            model.addAttribute("error", "Debe completar todos los campos obligatorios.");
            model.addAttribute("clientes", clienteService.listar());
            model.addAttribute("productos", productoService.listar());
            return "venta/nuevo";
        }

        if (dto.getDetalles() == null || dto.getDetalles().isEmpty()) {
            model.addAttribute("error", "Debe agregar al menos un producto.");
            model.addAttribute("clientes", clienteService.listar());
            model.addAttribute("productos", productoService.listar());
            return "venta/nuevo";
        }

        ventaService.guardar(dto);
        redirectAttributes.addFlashAttribute("mensaje", "¡Venta registrada correctamente!");
        return "redirect:/ventas";
    }

    @BitacoraLog(accion = "Ver detalle de venta", modulo = "Ventas")
    @GetMapping("/detalle/{id}")
    public String verDetalleVenta(@PathVariable("id") Integer idVenta, Model model) {
        VentaResponseDTO venta = ventaService.obtenerPorId(idVenta);
        List<DetalleVentaResponseDTO> detalles = ventaService.obtenerDetallesPorIdVenta(idVenta);

        model.addAttribute("venta", venta);
        model.addAttribute("detalles", detalles);

        return "venta/detalle"; 
    }
    
}
