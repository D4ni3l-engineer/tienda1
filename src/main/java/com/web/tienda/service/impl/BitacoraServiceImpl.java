package com.web.tienda.service.impl;

import com.web.tienda.model.Bitacora;
import com.web.tienda.repository.BitacoraRepository;
import com.web.tienda.service.BitacoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BitacoraServiceImpl implements BitacoraService {

    @Autowired
    private BitacoraRepository bitacoraRepository;

    @Override
    public List<Bitacora> listar() {
        return bitacoraRepository.findAll();
    }
}
