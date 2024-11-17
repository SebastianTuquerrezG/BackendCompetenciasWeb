package co.edu.unicauca.competencias.proyectoweb.user_module.user_infraestructure.controller;

import co.edu.unicauca.competencias.proyectoweb.user_module.user_infraestructure.authDTO.AuthRequestDTO;
import co.edu.unicauca.competencias.proyectoweb.user_module.user_infraestructure.authDTO.AuthResponseDTO;
import co.edu.unicauca.competencias.proyectoweb.user_module.user_infraestructure.authDTO.RegisterRequestDTO;
import co.edu.unicauca.competencias.proyectoweb.user_module.user_service.implementations.AuthenticationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationServiceImpl authenticationServiceImpl;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@RequestBody RegisterRequestDTO request){
        return ResponseEntity.ok(authenticationServiceImpl.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> authenticate(@RequestBody AuthRequestDTO request){
       return ResponseEntity.ok(authenticationServiceImpl.authenticate(request));
    }
}
