package com.prueba.service.impl;

import com.prueba.model.Mercancia;
import com.prueba.repository.MercanciaRepository;
import com.prueba.service.MercanciaService;
import com.prueba.service.dto.MercanciaDTO;
import com.prueba.service.mapper.MercanciaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class MercanciaServiceImpl implements MercanciaService {

    private final Logger log = LoggerFactory.getLogger(MercanciaServiceImpl.class);

    private final MercanciaRepository mercanciaRepository;

    private final MercanciaMapper mercanciaMapper;

    public MercanciaServiceImpl(MercanciaRepository mercanciaRepository, MercanciaMapper mercanciaMapper) {
        this.mercanciaRepository = mercanciaRepository;
        this.mercanciaMapper = mercanciaMapper;
    }

    @Override
    public MercanciaDTO save(MercanciaDTO mercanciaDTO) {
        log.debug("Request to save Mercancia : {}", mercanciaDTO);


        Mercancia mercancia = mercanciaMapper.mercanciaDTOToMercancia(mercanciaDTO);
        if (Objects.nonNull(mercanciaDTO.getId())){

            Optional<MercanciaDTO> antesDeActualizar = this.findOne(mercanciaDTO.getId());

            antesDeActualizar.ifPresent(mercanciaDTO1 -> {
                mercancia.setFechaCreacion(antesDeActualizar.get().getFechaCreacion());
                mercancia.setCreadoPor(antesDeActualizar.get().getCreadoPor());
                mercancia.setModificadoPor(antesDeActualizar.get().getModificadoPor());
                mercancia.setFechaModificacion(antesDeActualizar.get().getFechaModificacion());
            });

        }
        Mercancia mercancianew = mercanciaRepository.save(mercancia);
        MercanciaDTO result = mercanciaMapper.mercanciasToMercanciaDTO(mercancianew);
        return result;
    }

    @Override
    public List<MercanciaDTO> findAll() {
        log.debug("Request to get all Mercancias");
        return mercanciaRepository.findAll().stream()
            .map(mercanciaMapper::mercanciasToMercanciaDTO)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    public List<MercanciaDTO> findAllByNombreProductoLike(String nombreProducto) {

        log.debug("Request to get all Mercancias");

        if (Objects.nonNull(nombreProducto) && !nombreProducto.isEmpty()) {
            System.out.println("entra a nombre "+nombreProducto);
            return mercanciaRepository.findAllByNombreProductoContainingIgnoreCase(nombreProducto).stream()
                    .map(mercanciaMapper::mercanciasToMercanciaDTO)
                    .collect(Collectors.toCollection(LinkedList::new));
        } else{
            System.out.println("busca todo");
            return mercanciaRepository.findAll().stream()
                    .map(mercanciaMapper::mercanciasToMercanciaDTO)
                    .collect(Collectors.toCollection(LinkedList::new));
        }

    }


    @Override
    public Optional<MercanciaDTO> findOne(Long id) {
        log.debug("Request to get Mercancia : {}", id);
        return mercanciaRepository.findById(id)
            .map(mercanciaMapper::mercanciasToMercanciaDTO);
    }


    @Override
    public void delete(Long id) {
        log.debug("Request to delete Mercancia : {}", id);
        mercanciaRepository.deleteById(id);
    }

    

}
