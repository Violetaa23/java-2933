package com.uberits.uberitspring.servicios;

import java.util.List;

import com.uberits.uberitspring.dto.CrearPedidoDto;
import com.uberits.uberitspring.dto.PedidoDto;

public interface PedidoService {
    List<PedidoDto> listar();
    PedidoDto crear(CrearPedidoDto datos);
}
