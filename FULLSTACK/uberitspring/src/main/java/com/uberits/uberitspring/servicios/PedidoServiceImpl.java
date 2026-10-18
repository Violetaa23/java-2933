package com.uberits.uberitspring.servicios;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.uberits.entidades.Cliente;
import com.uberits.entidades.Pedido;
import com.uberits.entidades.Plato;
import com.uberits.uberitspring.dto.CrearPedidoDto;
import com.uberits.uberitspring.dto.PedidoDto;
import com.uberits.uberitspring.repositorios.ClienteRepository;
import com.uberits.uberitspring.repositorios.PedidoRepository;
import com.uberits.uberitspring.repositorios.PlatoRepository;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final PlatoRepository platoRepository;

    public PedidoServiceImpl(
            PedidoRepository pedidoRepository,
            ClienteRepository clienteRepository,
            PlatoRepository platoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.platoRepository = platoRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PedidoDto> listar() {
        return StreamSupport.stream(
                pedidoRepository.findAll().spliterator(), false)
            .map(this::convertirDto)
            .toList();
    }

    @Override
    @Transactional
    public PedidoDto crear(CrearPedidoDto datos) {
        Cliente cliente = clienteRepository.findById(datos.clienteId())
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "No existe el cliente indicado"));

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setFechaHora(LocalDateTime.now());

        for (CrearPedidoDto.CrearLineaPedidoDto datosLinea : datos.lineas()) {
            Plato plato = platoRepository.findById(datosLinea.platoId())
                .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No existe el plato con identificador " + datosLinea.platoId()));

            Pedido.Linea linea = new Pedido.Linea();
            linea.setPedido(pedido);
            linea.setPlato(plato);
            linea.setCantidad(datosLinea.cantidad());
            pedido.getLineas().add(linea);
        }

        Pedido guardado = pedidoRepository.save(pedido);
        return convertirDto(guardado);
    }

    private PedidoDto convertirDto(Pedido pedido) {
        List<PedidoDto.LineaPedidoDto> lineas = pedido.getLineas()
            .stream()
            .map(linea -> new PedidoDto.LineaPedidoDto(
                linea.getId(),
                linea.getPlato().getId(),
                linea.getPlato().getNombre(),
                linea.getCantidad(),
                linea.getPlato().getPrecio()
            ))
            .toList();

        return new PedidoDto(
            pedido.getId(),
            pedido.getFechaHora(),
            pedido.getCliente().getId(),
            lineas
        );
    }
}
