package com.prueba.repository;

import com.prueba.model.Cargo;
import com.prueba.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByNombre(String nombre);

    Optional<Usuario> findByEmail(String nombre);


    @Query("select u from Usuario u join u.cargos c where c.id = :cargoId")
    List<Usuario> findAllByCargoId(@Param("cargoId") Long cargoId);

    Boolean existsByEmail(String email);
}
