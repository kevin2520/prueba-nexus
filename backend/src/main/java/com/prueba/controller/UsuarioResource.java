package com.prueba.controller;

import com.prueba.exception.ResourceNotFoundException;
import com.prueba.model.Usuario;
import com.prueba.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UsuarioResource {

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping("/usuarios")
    public List<Usuario> getAll() {

        return usuarioRepository.findAll();
    }

    @PostMapping("/usuarios")
    public Usuario create(@Valid @RequestBody Usuario u) {
        return usuarioRepository.save(u);
    }

    @GetMapping("/usuarios/{id}")
    public Usuario getById(@PathVariable(value = "id") Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", id));
    }

    @PutMapping("/usuarios/{id}")
    public Usuario update(@PathVariable(value = "id") Long id,
                          @Valid @RequestBody Usuario u) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", id));

        Usuario updatedUsuario = usuarioRepository.save(u);
        return updatedUsuario;
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", id));

        usuarioRepository.delete(usuario);

        return ResponseEntity.ok().build();
    }
}
