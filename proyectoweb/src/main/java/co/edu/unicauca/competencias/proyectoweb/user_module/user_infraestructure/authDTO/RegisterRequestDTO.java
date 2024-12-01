package co.edu.unicauca.competencias.proyectoweb.user_module.user_infraestructure.authDTO;

import co.edu.unicauca.competencias.proyectoweb.user_module.user_core.complements.Role;
import co.edu.unicauca.competencias.proyectoweb.user_module.user_core.complements.StatusUser;
import co.edu.unicauca.competencias.proyectoweb.user_module.user_core.complements.TypeId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDTO {
    private long id;
    private String identity;
    private TypeId typeId;
    private String name;
    private String lastname;
    private String degree;
    private String email;
    private String username;
    private String password;
    private String phoneNumber;
    private StatusUser statusUser;
    private Role role;
    private Date createdAt;
    private Date updatedAt;
    private int failedAttempts;
    private boolean accountNonLocked;
}
