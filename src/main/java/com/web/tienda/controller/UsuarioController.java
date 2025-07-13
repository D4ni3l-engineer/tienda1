package com.web.tienda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.web.tienda.annotations.BitacoraLog;
import com.web.tienda.dto.UsuarioRequestDTO;
import com.web.tienda.dto.UsuarioResponseDTO;
import com.web.tienda.service.RolService;
import com.web.tienda.service.UsuarioService;
import com.web.tienda.validation.OnUpdate;

import jakarta.validation.Valid;


@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RolService rolService;

    @BitacoraLog(accion = "Listar usuarios", modulo = "Usuarios")
    @GetMapping
    public String listarUsuarios(Model model) {
        List<UsuarioResponseDTO> usuarios = usuarioService.listar();
        usuarios.forEach(u -> System.out.println("Usuario: " + u.getNombre() + " - Rol: " + u.getNombreRol()));
        model.addAttribute("usuarios", usuarios);
        return "usuario/lista";
    }

    @BitacoraLog(accion = "Mostrar formulario nuevo usuario", modulo = "Usuarios")
    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("usuario", new UsuarioRequestDTO());
        model.addAttribute("roles", rolService.listar());
        return "usuario/nuevo";
    }

    @BitacoraLog(accion = "Registrar usuario", modulo = "Usuarios")
    @PostMapping("/guardar")
    public String guardarUsuario(@Valid @ModelAttribute("usuario") UsuarioRequestDTO dto, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("roles", rolService.listar());
            return "usuario/nuevo";
        }

        usuarioService.guardar(dto);
        redirectAttributes.addFlashAttribute("mensaje", "¡Usuario registrado correctamente!");
        return "redirect:/usuarios";
    }

    @BitacoraLog(accion = "Mostrar formulario editar usuario", modulo = "Usuarios")
    @GetMapping("/editar/{id}")
    public String editarUsuario(@PathVariable Integer id, Model model) {
        UsuarioResponseDTO dto = usuarioService.obtenerPorId(id);
        UsuarioRequestDTO usuario = new UsuarioRequestDTO();

        usuario.setIdUsuario(dto.getIdUsuario());
        usuario.setNombre(dto.getNombre());
        usuario.setApellidoP(dto.getApellidoP());
        usuario.setApellidoM(dto.getApellidoM());
        usuario.setCorreo(dto.getCorreo());
        usuario.setIdRol(dto.getIdRol());

        model.addAttribute("usuario", usuario);
        model.addAttribute("roles", rolService.listar());

        return "usuario/editar";
    }

    @BitacoraLog(accion = "Actualizar usuario", modulo = "Usuarios")
    @PostMapping("/actualizar")
    public String actualizarUsuario(
            @Validated(OnUpdate.class) @ModelAttribute("usuario") UsuarioRequestDTO dto,
            BindingResult result,
            RedirectAttributes redirectAttributes,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("roles", rolService.listar());
            return "usuario/editar";
        }

        usuarioService.actualizar(dto);
        redirectAttributes.addFlashAttribute("mensaje", "¡Usuario actualizado correctamente!");
        return "redirect:/usuarios";
    }

    @BitacoraLog(accion = "Eliminar usuario", modulo = "Usuarios")
    @GetMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        usuarioService.eliminar(id);
        redirectAttributes.addFlashAttribute("mensaje", "Usuario eliminado correctamente");
        return "redirect:/usuarios";
    }
}
