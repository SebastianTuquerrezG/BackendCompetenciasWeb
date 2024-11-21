package co.edu.unicauca.competencias.proyectoweb.RAPrograma_module.RAPrograma_infraestructure.persistence.DTO;

import co.edu.unicauca.competencias.proyectoweb.CompetenciasPrograma_module.CompetenciasPrograma_core.entities.CompetenciaPrograma;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RAProgramaDTO {
    private Integer id;
    private String descripcion;
    private CompetenciaPrograma idCompetenciaPrograma;
    private Integer estado;
}