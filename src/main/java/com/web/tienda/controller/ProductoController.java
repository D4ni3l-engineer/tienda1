package com.web.tienda.controller;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.web.tienda.annotations.BitacoraLog;
import com.web.tienda.dto.ProductoRequestDTO;
import com.web.tienda.dto.ProductoResponseDTO;
import com.web.tienda.service.CategoriaService;
import com.web.tienda.service.ProductoService;
import com.web.tienda.service.ProveedorService;
import com.web.tienda.util.Metodo;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private ProveedorService proveedorService;

    @BitacoraLog(accion = "Listar productos", modulo = "Productos")
    @GetMapping
    public String listarProductos(Model model) {
        model.addAttribute("productos", productoService.listar());
        return "producto/lista";
    }

    @BitacoraLog(accion = "Mostrar formulario de nuevo producto", modulo = "Productos")
    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        ProductoRequestDTO dto = new ProductoRequestDTO();
        model.addAttribute("producto", dto);
        model.addAttribute("categorias", categoriaService.listar());
        model.addAttribute("proveedores", proveedorService.listar());
        return "producto/nuevo";
    }

    @BitacoraLog(accion = "Guardar nuevo producto", modulo = "Productos")
    @PostMapping("/guardar")
    public String guardarProducto(@Valid @ModelAttribute("producto") ProductoRequestDTO dto,
                                BindingResult result,
                                @RequestParam("imagenFile") MultipartFile imagenFile,
                                RedirectAttributes redirectAttributes,
                                Model model) {

        System.out.println("Imagen file name: " + imagenFile.getOriginalFilename());
        System.out.println("Imagen file size: " + imagenFile.getSize());


        if (result.hasErrors()) {
            System.out.println(">>> Errores de validación:");
            result.getFieldErrors().forEach(error -> {
                System.out.println("Campo: " + error.getField() + " | Error: " + error.getDefaultMessage());
            });
            model.addAttribute("categorias", categoriaService.listar());
            model.addAttribute("proveedores", proveedorService.listar());
            return "producto/nuevo";
        }

        try {
            if (!imagenFile.isEmpty()) {
                String nombreImagen = Metodo.guardarImagen(imagenFile);
                dto.setImagen(nombreImagen);
                
            }
            System.out.println("Guardado");
        } catch (IOException e) {
            System.out.println("Erro:"+e.getMessage());
            result.rejectValue("imagen", null, "Error al guardar la imagen");
            return "producto/nuevo";
        }

        productoService.guardar(dto);
        redirectAttributes.addFlashAttribute("mensaje", "Producto registrado correctamente");
        return "redirect:/productos";
    }


    @BitacoraLog(accion = "Buscar producto por ID", modulo = "Productos")
    @ResponseBody
    @GetMapping("/buscar/{id}")
    public ProductoResponseDTO buscarProducto(@PathVariable Integer id) {
        return productoService.obtenerPorId(id);
    }

    @BitacoraLog(accion = "Mostrar formulario para editar producto", modulo = "Productos")
    @GetMapping("/editar/{id}")
    public String editarProducto(@PathVariable Integer id, Model model) {
        ProductoResponseDTO dto = productoService.obtenerPorId(id);
        ProductoRequestDTO producto = new ProductoRequestDTO();
        producto.setIdProducto(dto.getIdProducto());
        producto.setNombre(dto.getNombre());
        producto.setPrecioVenta(dto.getPrecioVenta());
        producto.setPrecioCompra(dto.getPrecioCompra());
        producto.setStock(dto.getStock());
        producto.setImagen(dto.getImagen());
        producto.setIdCategoria(dto.getIdCategoria());
        producto.setIdProveedor(dto.getIdProveedor());

        model.addAttribute("producto", producto);
        model.addAttribute("categorias", categoriaService.listar());
        model.addAttribute("proveedores", proveedorService.listar());

        return "producto/modificar";
    }

    @BitacoraLog(accion = "Actualizar producto", modulo = "Productos")
    @PostMapping("/actualizar")
    public String actualizarProducto(@Valid @ModelAttribute("producto") ProductoRequestDTO dto,
                                    BindingResult result,
                                    @RequestParam("imagenFile") MultipartFile imagenFile,
                                    RedirectAttributes redirectAttributes,
                                    Model model) throws InterruptedException {
        
        if (result.hasErrors()) {
            model.addAttribute("categorias", categoriaService.listar());
            model.addAttribute("proveedores", proveedorService.listar());
            return "producto/modificar"; 
        }

        try {
            if (!imagenFile.isEmpty()) {
                String nuevaImagen = Metodo.guardarImagen(imagenFile);
                dto.setImagen(nuevaImagen);
            }
        } catch (IOException e) {
            result.rejectValue("imagen", null, "Error al guardar la nueva imagen");
            model.addAttribute("categorias", categoriaService.listar());
            model.addAttribute("proveedores", proveedorService.listar());
            return "producto/modificar";
        }

        productoService.actualizar(dto);
        redirectAttributes.addFlashAttribute("mensaje", "Producto actualizado correctamente");
        return "redirect:/productos";
    }

    @BitacoraLog(accion = "Eliminar producto", modulo = "Productos")
    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        productoService.eliminar(id);
        redirectAttributes.addFlashAttribute("mensaje", "Producto eliminado correctamente");
        return "redirect:/productos";
    }
}