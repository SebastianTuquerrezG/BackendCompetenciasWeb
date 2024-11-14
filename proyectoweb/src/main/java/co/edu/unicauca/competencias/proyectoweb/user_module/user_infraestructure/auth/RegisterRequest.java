package co.edu.unicauca.competencias.proyectoweb.user_module.user_infraestructure.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String names;
    private String lastnames;
    private String email;
    private String password;
}
