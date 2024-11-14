package co.edu.unicauca.competencias.proyectoweb.user_module.user_infraestructure.controller;

import co.edu.unicauca.competencias.proyectoweb.user_module.user_configuration.JwtService;
import co.edu.unicauca.competencias.proyectoweb.user_module.user_core.entities.User;
import co.edu.unicauca.competencias.proyectoweb.user_module.user_infraestructure.auth.AuthenticationRequest;
import co.edu.unicauca.competencias.proyectoweb.user_module.user_infraestructure.auth.AuthenticationResponse;
import co.edu.unicauca.competencias.proyectoweb.user_module.user_infraestructure.auth.RegisterRequest;
import co.edu.unicauca.competencias.proyectoweb.user_module.user_service.auth.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final JwtService jwtService;
    private final AuthenticationResponse authenticationResponse;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<User> authenticate(@RequestBody AuthenticationRequest request){
        User authenticatedUser = authenticationService.authenticate(request);
        String jwtToken = jwtService.generateToken(authenticatedUser);
        authenticationResponse.setToken(jwtToken);
        authenticationResponse.setExpiresIn(jwtService.getExpirationTime());
        return ResponseEntity.ok(authenticatedUser);
    }
}
