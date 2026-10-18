package com.uberits.uberitspring.repositorios;

import com.uberits.entidades.Restaurante;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "restaurantes-data")
public interface RestauranteRepository
        extends CrudRepository<Restaurante, Long> {
}
