package co.edu.unicauca.competencias.proyectoweb.RAAsignatura_module.RAAsignatura_infraestructure.persistence.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RAAsignaturaDTO {
    private Integer id;
    private String descripcion;
    private Integer idCompetenciaAsignatura;
    private String estado;
}