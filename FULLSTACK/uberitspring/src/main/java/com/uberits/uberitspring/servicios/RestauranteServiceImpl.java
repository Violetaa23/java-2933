package com.uberits.uberitspring.servicios;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.uberits.entidades.Restaurante;
import com.uberits.uberitspring.dto.CrearRestauranteDto;
import com.uberits.uberitspring.dto.RestauranteDto;
import com.uberits.uberitspring.repositorios.RestauranteRepository;

@Service
public class RestauranteServiceImpl implements RestauranteService {

    private final RestauranteRepository restauranteRepository;

    public RestauranteServiceImpl(RestauranteRepository restauranteRepository) {
        this.restauranteRepository = restauranteRepository;
    }

    @Override
    public List<RestauranteDto> listar() {
        return StreamSupport.stream(
                restauranteRepository.findAll().spliterator(), false)
            .map(this::convertirDto)
            .toList();
    }

    @Override
    public Optional<RestauranteDto> buscarPorId(Long id) {
        return restauranteRepository.findById(id).map(this::convertirDto);
    }

    @Override
    public RestauranteDto crear(CrearRestauranteDto datos) {
        Restaurante restaurante = new Restaurante();
        restaurante.setNombre(datos.nombre());

        Restaurante guardado = restauranteRepository.save(restaurante);
        return convertirDto(guardado);
    }

    private RestauranteDto convertirDto(Restaurante restaurante) {
        return new RestauranteDto(restaurante.getId(), restaurante.getNombre());
    }
}
