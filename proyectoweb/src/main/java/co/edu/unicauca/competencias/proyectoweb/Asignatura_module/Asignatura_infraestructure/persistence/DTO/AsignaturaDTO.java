package co.edu.unicauca.competencias.proyectoweb.Asignatura_module.Asignatura_infraestructure.persistence.DTO;

import co.edu.unicauca.competencias.proyectoweb.Asignatura_module.Asignatura_core.entities.Asignatura;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AsignaturaDTO {
    private Integer id;
    private String nombre;
    private Integer creditos;
    private String objetivos;
    private Integer semestre;
    @Enumerated(EnumType.STRING)
    private Asignatura.Status status;
    private Date created_at;
    private Date updated_at;
}
