package com.web.tienda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.web.tienda.annotations.BitacoraLog;
import com.web.tienda.dto.ProveedorRequestDTO;
import com.web.tienda.dto.ProveedorResponseDTO;
import com.web.tienda.service.ProveedorService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @BitacoraLog(accion = "Listar proveedores", modulo = "Proveedores")
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("proveedores", proveedorService.listar());
        return "proveedor/lista";
    }

    @BitacoraLog(accion = "Mostrar formulario nuevo proveedor", modulo = "Proveedores")
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("proveedor", new ProveedorRequestDTO());
        return "proveedor/nuevo";
    }

    @BitacoraLog(accion = "Registrar proveedor", modulo = "Proveedores")
    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("proveedor") ProveedorRequestDTO dto,BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "proveedor/nuevo";
        }
        proveedorService.guardar(dto);
        redirectAttributes.addFlashAttribute("mensaje", "¡Proveedor registrado correctamente!");
        return "redirect:/proveedores";
    }

    @BitacoraLog(accion = "Mostrar formulario modificar proveedor", modulo = "Proveedores")
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        ProveedorResponseDTO dto = proveedorService.obtenerPorId(id);
        ProveedorRequestDTO proveedor = new ProveedorRequestDTO();

        proveedor.setIdProveedor(dto.getIdProveedor());
        proveedor.setRazonSocial(dto.getRazonSocial());
        proveedor.setDireccion(dto.getDireccion());
        proveedor.setPagWeb(dto.getPagWeb());
        proveedor.setTelefono(dto.getTelefono());
        proveedor.setCorreo(dto.getCorreo());

        model.addAttribute("proveedor", proveedor);
        return "proveedor/modificar";
    }

    @BitacoraLog(accion = "Actualizar proveedor", modulo = "Proveedores")
    @PostMapping("/actualizar")
    public String actualizar(@Valid @ModelAttribute("proveedor") ProveedorRequestDTO dto,BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "proveedor/modificar";
        }
        proveedorService.actualizar(dto);
        redirectAttributes.addFlashAttribute("mensaje", "¡Proveedor actualizado con éxito!");
        return "redirect:/proveedores";
    }

    @BitacoraLog(accion = "Eliminar proveedor", modulo = "Proveedores")
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        proveedorService.eliminar(id);
        redirectAttributes.addFlashAttribute("mensaje", "Proveedor eliminado correctamente");
        return "redirect:/proveedores";
    }
}
