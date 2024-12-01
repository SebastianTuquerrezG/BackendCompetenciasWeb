package co.edu.unicauca.competencias.proyectoweb.user_module.user_service.interfaces;

public interface iEmailService {
    void connectWithDomain();
    void sendTokenByEmail(String email, String token);
}
