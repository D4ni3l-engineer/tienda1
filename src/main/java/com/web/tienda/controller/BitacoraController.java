package com.web.tienda.controller;

import com.web.tienda.model.Bitacora;
import com.web.tienda.service.BitacoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/bitacoras")
public class BitacoraController {

    @Autowired
    private BitacoraService bitacoraService;

    @GetMapping
    public String listarBitacoras(Model model) {
        List<Bitacora> bitacoras = bitacoraService.listar();
        model.addAttribute("bitacoras", bitacoras);
        return "bitacora/lista"; 
    }
}
