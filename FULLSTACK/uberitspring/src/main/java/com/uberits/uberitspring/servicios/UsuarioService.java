package com.uberits.uberitspring.servicios;

import com.uberits.uberitspring.dto.UsuarioDto;
import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    List<UsuarioDto> listar();
    Optional<UsuarioDto> buscarPorId(Long id);
}
