package com.web.tienda.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.web.tienda.dto.UsuarioRequestDTO;
import com.web.tienda.dto.UsuarioResponseDTO;
import com.web.tienda.exception.ResourceNotFoundException;
import com.web.tienda.model.Rol;
import com.web.tienda.model.Usuario;
import com.web.tienda.repository.RolRepository;
import com.web.tienda.repository.UsuarioRepository;
import com.web.tienda.service.UsuarioService;


@Service
public class UsuarioServiceImpl implements UsuarioService {

        @Autowired
        private UsuarioRepository usuarioRepository;

        @Autowired
        private RolRepository rolRepository;

        @Autowired
        private PasswordEncoder passwordEncoder;

        @Override
        public UsuarioResponseDTO guardar(UsuarioRequestDTO dto) {
        Rol rol = rolRepository.findById(dto.getIdRol())
                .orElseThrow(() -> new ResourceNotFoundException("Rol no encontrado"));

                Usuario usuario = new Usuario();
                usuario.setRol(rol);
                usuario.setNombre(dto.getNombre());
                usuario.setApellidoP(dto.getApellidoP());
                usuario.setApellidoM(dto.getApellidoM());
                usuario.setCorreo(dto.getCorreo());
                String contraseniaEncriptada = passwordEncoder.encode(dto.getContrasenia());
                usuario.setContrasenia(contraseniaEncriptada);

                usuarioRepository.save(usuario);

                return new UsuarioResponseDTO(
                        usuario.getIdUsuario(),
                        usuario.getNombre(),
                        usuario.getApellidoP(),
                        usuario.getApellidoM(),
                        usuario.getCorreo(),
                        usuario.getRol().getNombre()
                );
        }

        @Override
        public UsuarioResponseDTO actualizar(UsuarioRequestDTO dto) {
                Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                        .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

                Rol rol = rolRepository.findById(dto.getIdRol())
                        .orElseThrow(() -> new ResourceNotFoundException("Rol no encontrado"));

                usuario.setRol(rol);
                usuario.setNombre(dto.getNombre());
                usuario.setApellidoP(dto.getApellidoP());
                usuario.setApellidoM(dto.getApellidoM());
                usuario.setCorreo(dto.getCorreo());
                if (dto.getContrasenia() != null && !dto.getContrasenia().trim().isEmpty()) {
                        usuario.setContrasenia(dto.getContrasenia());
                }

                usuarioRepository.save(usuario);

                return new UsuarioResponseDTO(
                        usuario.getIdUsuario(),
                        usuario.getNombre(),
                        usuario.getApellidoP(),
                        usuario.getApellidoM(),
                        usuario.getCorreo(),
                        usuario.getRol().getNombre()
                );
        }

        @Override
        public List<UsuarioResponseDTO> listar() {
        return usuarioRepository.findAll().stream()
                .map(u -> new UsuarioResponseDTO(
                        u.getIdUsuario(),
                        u.getNombre(),
                        u.getApellidoP(),
                        u.getApellidoM(),
                        u.getCorreo(),
                        u.getRol().getNombre()))
                .collect(Collectors.toList());
        }

        @Override
        public UsuarioResponseDTO obtenerPorId(Integer id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        return new UsuarioResponseDTO(
                usuario.getIdUsuario(),
                usuario.getNombre(),
                usuario.getApellidoP(),
                usuario.getApellidoM(),
                usuario.getCorreo(),
                usuario.getRol().getIdRol()
        );
        }

        @Override
        public void eliminar(Integer id) {
                usuarioRepository.deleteById(id);
        }
}       
