package com.web.tienda.service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class LoginIntentoMemoriaService {

    private static final int MAX_INTENTOS = 3;
    private static final int BLOQUEO_MINUTOS = 5;

    private final Map<String, InfoIntento> intentos = new ConcurrentHashMap<>();

    public void loginFallido(String username) {
        InfoIntento info = intentos.getOrDefault(username, new InfoIntento(0, null));

        info.intentos++;
        info.ultimoIntento = LocalDateTime.now();

        intentos.put(username, info);
    }

    public void loginExitoso(String username) {
        intentos.remove(username);
    }

    public boolean estaBloqueado(String username) {
        InfoIntento info = intentos.get(username);

        if (info == null) return false;

        if (info.intentos >= MAX_INTENTOS) {
            LocalDateTime desbloqueo = info.ultimoIntento.plusMinutes(BLOQUEO_MINUTOS);
            return LocalDateTime.now().isBefore(desbloqueo);
        }

        return false;
    }

    private static class InfoIntento {
        int intentos;
        LocalDateTime ultimoIntento;

        InfoIntento(int intentos, LocalDateTime ultimoIntento) {
            this.intentos = intentos;
            this.ultimoIntento = ultimoIntento;
        }
    }
}