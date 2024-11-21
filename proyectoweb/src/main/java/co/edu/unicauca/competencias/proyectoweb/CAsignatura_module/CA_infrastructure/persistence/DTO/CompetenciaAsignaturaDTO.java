package co.edu.unicauca.competencias.proyectoweb.CAsignatura_module.CA_infrastructure.persistence.DTO;

import co.edu.unicauca.competencias.proyectoweb.CAsignatura_module.CA_core.entities.CompetenciaAsignatura.*;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompetenciaAsignaturaDTO {
    private int id;
    private int competenciaprograma_id;
    private String descripcion;
    @Enumerated(EnumType.STRING)
    private NivelCompetencia nivel;
    @Enumerated(EnumType.STRING)
    private Status status;

}
