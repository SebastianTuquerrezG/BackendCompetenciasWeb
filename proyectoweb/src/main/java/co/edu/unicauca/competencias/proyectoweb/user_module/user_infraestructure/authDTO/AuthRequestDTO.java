package co.edu.unicauca.competencias.proyectoweb.user_module.user_infraestructure.authDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequestDTO {

    private String email;
    private String password;
}
