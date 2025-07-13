package com.web.tienda.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.web.tienda.annotations.BitacoraLog;
import com.web.tienda.dto.CategoriaRequestDTO;
import com.web.tienda.dto.CategoriaResponseDTO;
import com.web.tienda.service.CategoriaService;

import java.util.List;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @BitacoraLog(accion = "Listar categorías", modulo = "Categorías")
    @GetMapping
    public String listarCategorias(Model model) {
        List<CategoriaResponseDTO> listaCategorias = categoriaService.listar();
        model.addAttribute("categorias", listaCategorias);
        return "categoria/lista"; 
    }

    @BitacoraLog(accion = "Mostrar formulario de nueva categoría", modulo = "Categorías")
    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("categoria", new CategoriaRequestDTO());
        return "categoria/nuevo"; 
    }

    @BitacoraLog(accion = "Guardar nueva categoría", modulo = "Categorías")
    @PostMapping("/guardar")
    public String guardarCategoria(@Valid @ModelAttribute("categoria") CategoriaRequestDTO dto,BindingResult result,RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "categoria/nuevo";
        }
        categoriaService.guardar(dto);
        redirectAttributes.addFlashAttribute("mensaje", "¡Categoría registrada correctamente!");
        return "redirect:/categorias";
    }

    @BitacoraLog(accion = "Mostrar formulario de edición", modulo = "Categorías")
    @GetMapping("/editar/{id}")
    public String editarCategoria(@PathVariable Integer id, Model model) {
        CategoriaResponseDTO dto = categoriaService.obtenerPorId(id);
        CategoriaRequestDTO categoria = new CategoriaRequestDTO();
        categoria.setNombre(dto.getNombre());
        categoria.setIdCategoria(dto.getIdCategoria());
        model.addAttribute("categoria", categoria);

        return "categoria/modificar";
    }

    @BitacoraLog(accion = "Actualizar categoría", modulo = "Categorías")
    @PostMapping("/actualizar")
    public String actualizarCategoria(@Valid @ModelAttribute("categoria") CategoriaRequestDTO dto, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            return "categoria/modificar";
        }

        categoriaService.actualizar(dto);
        redirectAttributes.addFlashAttribute("mensaje", "¡Categoría actualizada con éxito!");
        return "redirect:/categorias";
    }

    @BitacoraLog(accion = "Eliminar categoría", modulo = "Categorías")
    @GetMapping("/eliminar/{id}")
    public String eliminarCategoria(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        categoriaService.eliminar(id);
        redirectAttributes.addFlashAttribute("mensaje", "Categoría eliminada correctamente");
        return "redirect:/categorias";
    }
}