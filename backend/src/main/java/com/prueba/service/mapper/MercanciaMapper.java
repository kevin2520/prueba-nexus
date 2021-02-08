package com.prueba.service.mapper;


import com.prueba.model.Mercancia;
import com.prueba.service.dto.MercanciaDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
public class MercanciaMapper {



    public List<MercanciaDTO> mercanciasToMercanciaDTOs(List<Mercancia> mercancia) {
        return mercancia.stream()
                .filter(Objects::nonNull)
                .map(this::mercanciasToMercanciaDTO)
                .collect(Collectors.toList());
    }

    public MercanciaDTO mercanciasToMercanciaDTO(Mercancia mercancia) {
        return new MercanciaDTO(mercancia);
    }

    public List<Mercancia> mercanciaDTOsToMercancia(List<MercanciaDTO> mercanciaDTOs) {
        return mercanciaDTOs.stream()
                .filter(Objects::nonNull)
                .map(this::mercanciaDTOToMercancia)
                .collect(Collectors.toList());
    }

    public Mercancia mercanciaDTOToMercancia(MercanciaDTO mercanciaDTO) {
        if (mercanciaDTO == null) {
            return null;
        } else {
            Mercancia mercancia = new Mercancia();
            mercancia.setId(mercanciaDTO.getId());
            mercancia.setCantidad(mercanciaDTO.getCantidad());
            mercancia.setNombreProducto(mercanciaDTO.getNombreProducto());
            mercancia.setFechaIngreso(mercanciaDTO.getFechaIngreso());
            return mercancia;
        }
    }
    
    public Mercancia mercanciasFromId(Long id) {
        if (id == null) {
            return null;
        }
        Mercancia mercancia = new Mercancia();
        mercancia.setId(id);
        return mercancia;
    }
}
