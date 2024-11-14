package co.edu.unicauca.competencias.proyectoweb.user_module.user_service.auth;

import co.edu.unicauca.competencias.proyectoweb.user_module.user_core.entities.User;
import co.edu.unicauca.competencias.proyectoweb.user_module.user_infraestructure.auth.AuthenticationRequest;
import co.edu.unicauca.competencias.proyectoweb.user_module.user_infraestructure.auth.AuthenticationResponse;
import co.edu.unicauca.competencias.proyectoweb.user_module.user_infraestructure.auth.RegisterRequest;
import co.edu.unicauca.competencias.proyectoweb.user_module.user_service.implementations.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserServiceImpl userService;
    private final AuthenticationManager authenticationManager;

    public User authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        return userService.getByEmail(request.getEmail())
                .orElseThrow();
    }

    // Maybe this register we wouldn't need it
    public AuthenticationResponse register(RegisterRequest request) {
        return null;
    }
}
