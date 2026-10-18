package com.uberits.uberitspring.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record PedidoDto(
    Long id,
    LocalDateTime fechaHora,
    Long clienteId,
    List<LineaPedidoDto> lineas
) {
    public record LineaPedidoDto(
        Long id,
        Long platoId,
        String nombrePlato,
        Integer cantidad,
        BigDecimal precioUnitario
    ) {
    }
}
