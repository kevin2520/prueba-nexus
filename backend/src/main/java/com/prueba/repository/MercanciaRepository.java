package com.prueba.repository;

import com.prueba.model.Mercancia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MercanciaRepository extends JpaRepository<Mercancia, Long> {


    List<Mercancia> findAllByNombreProductoContainingIgnoreCase(String nombreProducto);
}
