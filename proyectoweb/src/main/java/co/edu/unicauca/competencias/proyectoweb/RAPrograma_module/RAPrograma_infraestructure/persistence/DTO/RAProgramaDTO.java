package co.edu.unicauca.competencias.proyectoweb.RAPrograma_module.RAPrograma_infraestructure.persistence.DTO;

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