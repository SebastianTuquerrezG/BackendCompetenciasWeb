package co.edu.unicauca.competencias.proyectoweb.RAPrograma_module.RAPrograma_infraestructure.persistence.DTO;

import co.edu.unicauca.competencias.proyectoweb.CompetenciasPrograma_module.CompetenciasPrograma_core.entities.CompetenciaPrograma;
import co.edu.unicauca.competencias.proyectoweb.CompetenciasPrograma_module.CompetenciasPrograma_infraestructure.persistence.DTO.CompetenciaProgramaDTO;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RAProgramaDTO {
    private Integer id;
    private String descripcion;
    private Integer idCompetenciaPrograma;
    private Integer estado;
}