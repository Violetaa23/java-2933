package com.uberits.uberitspring.controladores;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.uberits.uberitspring.dto.CrearRestauranteDto;
import com.uberits.uberitspring.dto.RestauranteDto;
import com.uberits.uberitspring.servicios.RestauranteService;

@RestController
@RequestMapping("/api/restaurantes")
public class RestauranteRestController {

    private final RestauranteService restauranteService;

    public RestauranteRestController(RestauranteService restauranteService) {
        this.restauranteService = restauranteService;
    }

    @GetMapping
    public List<RestauranteDto> listar() {
        return restauranteService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestauranteDto> buscarPorId(@PathVariable Long id) {
        return restauranteService.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RestauranteDto> crear(
            @Valid @RequestBody CrearRestauranteDto datos) {
        RestauranteDto creado = restauranteService.crear(datos);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }
}
