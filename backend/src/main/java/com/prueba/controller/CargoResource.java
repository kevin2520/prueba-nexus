package com.prueba.controller;

import com.prueba.exception.RemoveCargoException;
import com.prueba.exception.ResourceNotFoundException;
import com.prueba.model.Cargo;
import com.prueba.model.Usuario;
import com.prueba.repository.CargoRepository;
import com.prueba.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CargoResource {

    @Autowired
    CargoRepository cargoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping("/cargos")
    public List<Cargo> getAll() {
        return cargoRepository.findAll();
    }

    @PostMapping("/cargos")
    public Cargo create(@Valid @RequestBody Cargo r) {
        return cargoRepository.save(r);
    }

    @GetMapping("/cargos/{id}")
    public Cargo getById(@PathVariable(value = "id") Long id) {
        return cargoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cargo", "id", id));
    }

    @PutMapping("/cargos/{id}")
    public Cargo update(@PathVariable(value = "id") Long id,
                        @Valid @RequestBody Cargo r) {

        Cargo cargo = cargoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cargo", "id", id));

        Cargo updatedrol = cargoRepository.save(r);
        return updatedrol;
    }

    @DeleteMapping("/cargos/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        Cargo cargo = cargoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cargo", "id", id));

      List<Usuario>  usuarios =  usuarioRepository.findAllByCargoId(cargo.getId()) ;
        System.out.println(usuarios.size());
        if (usuarios.size() > 0){

            throw new RemoveCargoException();

        } else{
            
        cargoRepository.delete(cargo);

        }


        return ResponseEntity.ok().build();
    }
}
