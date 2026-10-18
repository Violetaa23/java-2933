package com.uberits.uberitspring.dto;

import jakarta.validation.constraints.NotBlank;

public record CrearRestauranteDto(
    @NotBlank(message = "El nombre es obligatorio")
    String nombre
) {
}
