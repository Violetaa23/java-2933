package com.uberits.uberitspring.servicios;

import java.util.List;
import java.util.Optional;

import com.uberits.uberitspring.dto.CrearRestauranteDto;
import com.uberits.uberitspring.dto.RestauranteDto;

public interface RestauranteService {
    List<RestauranteDto> listar();
    Optional<RestauranteDto> buscarPorId(Long id);
    RestauranteDto crear(CrearRestauranteDto datos);
}
