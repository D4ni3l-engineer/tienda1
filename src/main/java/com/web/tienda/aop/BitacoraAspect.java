package com.web.tienda.aop;

import java.time.LocalDateTime;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.web.tienda.annotations.BitacoraLog;
import com.web.tienda.model.Bitacora;
import com.web.tienda.repository.BitacoraRepository;

import jakarta.servlet.http.HttpServletRequest;

@Aspect
@Component
public class BitacoraAspect {

    @Autowired
    private BitacoraRepository bitacoraRepository;

    @Around("@annotation(bitacoraLog)")
    public Object registrarBitacora(ProceedingJoinPoint joinPoint, BitacoraLog bitacoraLog) throws Throwable {
        LocalDateTime inicio = LocalDateTime.now();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        String ip = request.getRemoteAddr();
        String usuario = obtenerUsuarioDesdeContexto();

        Bitacora bitacora = new Bitacora();
        bitacora.setFecha(inicio);
        bitacora.setIp(ip);
        bitacora.setUsuario(usuario);
        bitacora.setAccion(bitacoraLog.accion());
        bitacora.setModulo(bitacoraLog.modulo());

        try {
            Object resultado = joinPoint.proceed(); // Ejecuta el método
            bitacora.setResultado("ÉXITO");
            bitacora.setDescripcion("Operación completada exitosamente.");
            return resultado;
        } catch (Exception e) {
            bitacora.setResultado("ERROR");
            bitacora.setDescripcion("Error: " + e.getMessage());
            throw e;
        } finally {
            bitacoraRepository.save(bitacora);
        }
    }

    private String obtenerUsuarioDesdeContexto() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return (auth != null && auth.isAuthenticated()) ? auth.getName() : "ANÓNIMO";
    }
}
