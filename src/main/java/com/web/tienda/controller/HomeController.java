package com.web.tienda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.tienda.service.ProductoService;

@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private ProductoService productoService;

    @GetMapping("/")
    public String mostrarInicio(Model model) {
        model.addAttribute("productos", productoService.listar());
        return "index";
    }

    @GetMapping("/dashboard")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_VENDEDOR', 'ROLE_ALMACENERO')")
    public String dashboard() {
        return "dashboard";
    }

}
