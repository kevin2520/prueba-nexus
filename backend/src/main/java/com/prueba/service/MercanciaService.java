package com.prueba.service;

import com.prueba.service.dto.MercanciaDTO;

import java.util.List;
import java.util.Optional;

public interface MercanciaService {

    /**
     * Save a Mercancia.
     *
     * @param MercanciaDTO the entity to save.
     * @return the persisted entity.
     */
    MercanciaDTO save(MercanciaDTO MercanciaDTO);

    /**
     * Get all the Mercancias.
     *
     * @return the list of entities.
     */
    List<MercanciaDTO> findAll();


    /**
     * Get the "id" Mercancia.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MercanciaDTO> findOne(Long id);

    /**
     * Delete the "id" Mercancia.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
    
}
