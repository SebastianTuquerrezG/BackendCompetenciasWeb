package co.edu.unicauca.competencias.proyectoweb.Asignaciongrande_module.Asignacion_infraestructure.persistence.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AsociacionDTO {
    private Integer id;
    private Integer id_asignatura;
    private Integer id_competencia_asignatura;
    private Integer id_teacher;
    private String periodo;
}
