package com.prueba.controller;

import com.prueba.exception.RemoveMercanciaException;
import com.prueba.exception.ResourceNotFoundException;
import com.prueba.exception.UnauthorizedException;
import com.prueba.security.util.SecurityUtils;
import com.prueba.service.dto.MercanciaDTO;
import com.prueba.service.impl.MercanciaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = 3600)
public class MercanciaResource {

    @Autowired
    MercanciaServiceImpl mercanciaServiceImpl;

    @GetMapping("/mercancias")
    public List<MercanciaDTO> getAll(@RequestParam(value = "nombreProducto",required = false) String nombreProducto) {

        System.out.println(SecurityUtils.getCurrentUserLogin().get());
        return mercanciaServiceImpl.findAllByNombreProductoLike(nombreProducto);
    }

    @PostMapping("/mercancias")
    public MercanciaDTO create(@Valid @RequestBody MercanciaDTO t) {
        return mercanciaServiceImpl.save(t);
    }

    @GetMapping("/mercancias/{id}")
    public MercanciaDTO getById(@PathVariable(value = "id") Long id) {
        return mercanciaServiceImpl.findOne(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mercancia", "id", id));
    }

    @PutMapping("/mercancias/{id}")
    public MercanciaDTO update(@PathVariable(value = "id") Long id,
                            @Valid @RequestBody MercanciaDTO t) {

        MercanciaDTO mercancia = mercanciaServiceImpl.findOne(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mercancia", "id", id));

        MercanciaDTO updatedMercancia = mercanciaServiceImpl.save(t);
        return updatedMercancia;
    }

    @DeleteMapping("/mercancias/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        MercanciaDTO mercancia = mercanciaServiceImpl.findOne(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mercancia", "id", id));


        if (SecurityUtils.getCurrentUserLogin().isPresent()){
             String usuarioActual = SecurityUtils.getCurrentUserLogin().get();
            if (mercancia.getCreadoPor().equals(usuarioActual)){
                mercanciaServiceImpl.delete(mercancia.getId());
            }  else{
                 throw new RemoveMercanciaException();
            }
        }   else{
            throw new UnauthorizedException();
        }


        return ResponseEntity.ok().build();
    }
}
