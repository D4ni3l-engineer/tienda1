package com.web.tienda.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.tienda.dto.ClienteRequestDTO;
import com.web.tienda.dto.ClienteResponseDTO;
import com.web.tienda.exception.ResourceNotFoundException;
import com.web.tienda.model.Cliente;
import com.web.tienda.repository.ClienteRepository;
import com.web.tienda.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService  {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public ClienteResponseDTO guardar(ClienteRequestDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setNombre(dto.getNombre());
        cliente.setApellidoP(dto.getApellidoP());
        cliente.setApellidoM(dto.getApellidoM());
        cliente.setDireccion(dto.getDireccion());
        cliente.setTelefono(dto.getTelefono());
        clienteRepository.save(cliente);
        return new ClienteResponseDTO(
                cliente.getIdCliente(), cliente.getNombre(), cliente.getApellidoP(), cliente.getApellidoM(),
                cliente.getDireccion(), cliente.getTelefono()
        );
    }

    @Override
    public ClienteResponseDTO actualizar(ClienteRequestDTO dto) {
        Cliente cliente = clienteRepository.findById(dto.getIdCliente())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + dto.getIdCliente()));
        cliente.setNombre(dto.getNombre());
        cliente.setApellidoP(dto.getApellidoP());
        cliente.setApellidoM(dto.getApellidoM());
        cliente.setDireccion(dto.getDireccion());
        cliente.setTelefono(dto.getTelefono());
        clienteRepository.save(cliente);
        return new ClienteResponseDTO(
                cliente.getIdCliente(), cliente.getNombre(), cliente.getApellidoP(), cliente.getApellidoM(),
                cliente.getDireccion(), cliente.getTelefono()
        );
    }

    @Override
    public List<ClienteResponseDTO> listar() {
        return clienteRepository.findAll().stream()
                .map(c -> new ClienteResponseDTO(c.getIdCliente(), c.getNombre(), c.getApellidoP(), c.getApellidoM(), c.getDireccion(), c.getTelefono()))
                .collect(Collectors.toList());
    }

    @Override
    public ClienteResponseDTO obtenerPorId(Integer id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + id));
        return new ClienteResponseDTO(
                cliente.getIdCliente(), cliente.getNombre(), cliente.getApellidoP(), cliente.getApellidoM(),
                cliente.getDireccion(), cliente.getTelefono()
        );
    }

    @Override
    public void eliminar(Integer id) {
        if (!clienteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cliente no encontrado con ID: " + id);
        }
        clienteRepository.deleteById(id);
    }
}

