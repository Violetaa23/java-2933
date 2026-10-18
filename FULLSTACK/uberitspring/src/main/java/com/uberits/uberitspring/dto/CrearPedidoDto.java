package com.uberits.uberitspring.dto;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CrearPedidoDto(
    @NotNull(message = "El cliente es obligatorio")
    @Positive(message = "El identificador del cliente debe ser positivo")
    Long clienteId,

    @NotEmpty(message = "El pedido debe contener al menos una línea")
    List<@Valid CrearLineaPedidoDto> lineas
) {
    public record CrearLineaPedidoDto(
        @NotNull(message = "El plato es obligatorio")
        @Positive(message = "El identificador del plato debe ser positivo")
        Long platoId,

        @NotNull(message = "La cantidad es obligatoria")
        @Positive(message = "La cantidad debe ser mayor que cero")
        Integer cantidad
    ) {
    }
}
