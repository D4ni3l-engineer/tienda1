package com.web.tienda.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Path;

public class Metodo {
    public static String guardarImagen(MultipartFile archivo) throws IOException {
        String nombreArchivo = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename();

        String ruta = "uploads/producto/";
        Path rutaCompleta = Paths.get(ruta + nombreArchivo);

        Files.createDirectories(rutaCompleta.getParent());
        Files.copy(archivo.getInputStream(), rutaCompleta, StandardCopyOption.REPLACE_EXISTING);

        return nombreArchivo;
    }
}
