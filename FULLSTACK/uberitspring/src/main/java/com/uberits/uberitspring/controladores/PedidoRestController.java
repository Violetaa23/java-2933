package com.uberits.uberitspring.controladores;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.uberits.uberitspring.dto.CrearPedidoDto;
import com.uberits.uberitspring.dto.PedidoDto;
import com.uberits.uberitspring.servicios.PedidoService;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoRestController {

    private final PedidoService pedidoService;

    public PedidoRestController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public List<PedidoDto> listar() {
        return pedidoService.listar();
    }

    @PostMapping
    public ResponseEntity<PedidoDto> crear(
            @Valid @RequestBody CrearPedidoDto datos) {
        PedidoDto creado = pedidoService.crear(datos);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }
}
