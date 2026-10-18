package com.uberits.uberitspring.repositorios;

import org.springframework.data.repository.CrudRepository;
import com.uberits.entidades.Pedido.Linea;

public interface PedidoLineaRepository extends CrudRepository<Linea, Long> {
}
