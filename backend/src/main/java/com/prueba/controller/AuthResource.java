package com.prueba.controller;

import com.prueba.message.request.LoginForm;
import com.prueba.message.request.SignUpForm;
import com.prueba.message.response.JwtResponse;
import com.prueba.model.Cargo;
import com.prueba.model.Usuario;
import com.prueba.repository.CargoRepository;
import com.prueba.repository.UsuarioRepository;
import com.prueba.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthResource {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    CargoRepository rolRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @PostMapping("/registrar")
    public ResponseEntity<String> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
        if (usuarioRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<String>("Fail -> Ya se encuentra registrado el email!",
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        Usuario user = new Usuario(signUpRequest.getNombre(), signUpRequest.getCedula(),
                signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()), signUpRequest.getEdad());

        String cargo = signUpRequest.getCargo();

        if (Objects.nonNull(cargo) && !cargo.equals("")){
            Set<Cargo> cargos = new HashSet<>();

            Cargo adminCargo = rolRepository.findByNombre(cargo)
                    .orElseThrow(() -> new RuntimeException("Fail! -> Causa: cargo no encontrado"));
            cargos.add(adminCargo);


            user.setCargos(cargos);
        }

        usuarioRepository.save(user);

        return ResponseEntity.ok().body("Usuario registrado correctamente!");
    }
}
