package com.uberits.uberitspring.servicios;

import com.uberits.entidades.Usuario;
import com.uberits.uberitspring.dto.UsuarioDto;
import com.uberits.uberitspring.repositorios.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<UsuarioDto> listar() {
        return StreamSupport.stream(
                usuarioRepository.findAll().spliterator(), false)
                .map(this::convertirDto)
                .toList();
    }

    @Override
    public Optional<UsuarioDto> buscarPorId(Long id) {
        return usuarioRepository.findById(id).map(this::convertirDto);
    }

    private UsuarioDto convertirDto(Usuario usuario) {
        return new UsuarioDto(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail()
        );
    }
}
