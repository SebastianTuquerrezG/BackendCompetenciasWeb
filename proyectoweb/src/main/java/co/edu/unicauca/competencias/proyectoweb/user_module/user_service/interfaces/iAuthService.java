package co.edu.unicauca.competencias.proyectoweb.user_module.user_service.interfaces;

import co.edu.unicauca.competencias.proyectoweb.user_module.user_infraestructure.authDTO.AuthRequestDTO;
import co.edu.unicauca.competencias.proyectoweb.user_module.user_infraestructure.authDTO.AuthResponseDTO;
import co.edu.unicauca.competencias.proyectoweb.user_module.user_infraestructure.authDTO.RegisterRequestDTO;

import java.util.Optional;

public interface iAuthService {
    AuthResponseDTO authenticate(AuthRequestDTO request);
    AuthResponseDTO register(RegisterRequestDTO request);
    Optional<Boolean> verifyCredentials(String token);
}
