package com.web.tienda.controller;

import java.util.List;

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
import com.web.tienda.dto.ClienteRequestDTO;
import com.web.tienda.dto.ClienteResponseDTO;
import com.web.tienda.service.ClienteService;

import jakarta.validation.Valid;



@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @BitacoraLog(accion = "Listar clientes", modulo = "Clientes")
    @GetMapping
    public String listarClientes(Model model) {
        List<ClienteResponseDTO> listaClientes = clienteService.listar();
        model.addAttribute("clientes", listaClientes);
        return "cliente/lista";
    }

    @BitacoraLog(accion = "Mostrar formulario de nuevo cliente", modulo = "Clientes")
    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("cliente", new ClienteRequestDTO());
        return "cliente/nuevo";
    }

    @BitacoraLog(accion = "Guardar nuevo cliente", modulo = "Clientes")
    @PostMapping("/guardar")
    public String guardarCliente(@Valid @ModelAttribute("cliente") ClienteRequestDTO dto,BindingResult result,RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "cliente/nuevo";
        }
        clienteService.guardar(dto);
        redirectAttributes.addFlashAttribute("mensaje", "¡Cliente registrado correctamente!");
        return "redirect:/clientes";
    }

    @BitacoraLog(accion = "Mostrar formulario de edición de cliente", modulo = "Clientes")
    @GetMapping("/editar/{id}")
    public String editarCliente(@PathVariable Integer id, Model model) {
        ClienteResponseDTO dto = clienteService.obtenerPorId(id);
        ClienteRequestDTO cliente = new ClienteRequestDTO();
        cliente.setIdCliente(dto.getIdCliente());
        cliente.setNombre(dto.getNombre());
        cliente.setApellidoP(dto.getApellidoP());
        cliente.setApellidoM(dto.getApellidoM());
        cliente.setDireccion(dto.getDireccion());
        cliente.setTelefono(dto.getTelefono());
        model.addAttribute("cliente", cliente);
        return "cliente/modificar";
    }

    @BitacoraLog(accion = "Actualizar cliente", modulo = "Clientes")
    @PostMapping("/actualizar")
    public String actualizarCliente(@Valid @ModelAttribute("cliente") ClienteRequestDTO dto,
                                    BindingResult result,
                                    RedirectAttributes redirectAttributes,
                                    Model model) {
        if (result.hasErrors()) {
            return "cliente/modificar";
        }
        clienteService.actualizar(dto);
        redirectAttributes.addFlashAttribute("mensaje", "¡Cliente actualizado con éxito!");
        return "redirect:/clientes";
    }

    @BitacoraLog(accion = "Eliminar cliente", modulo = "Clientes")
    @GetMapping("/eliminar/{id}")
    public String eliminarCliente(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        clienteService.eliminar(id);
        redirectAttributes.addFlashAttribute("mensaje", "Cliente eliminado correctamente");
        return "redirect:/clientes";
    }
}
