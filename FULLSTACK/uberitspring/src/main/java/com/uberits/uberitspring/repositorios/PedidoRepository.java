package com.uberits.uberitspring.repositorios;

import org.springframework.data.repository.CrudRepository;
import com.uberits.entidades.Pedido;

public interface PedidoRepository extends CrudRepository<Pedido, Long> {
}
