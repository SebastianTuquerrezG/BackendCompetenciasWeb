package co.edu.unicauca.competencias.proyectoweb.user_module.user_service.interfaces;

public interface iSmsService {
    void sendTokenBySms(String phoneNumber, String token);
}
