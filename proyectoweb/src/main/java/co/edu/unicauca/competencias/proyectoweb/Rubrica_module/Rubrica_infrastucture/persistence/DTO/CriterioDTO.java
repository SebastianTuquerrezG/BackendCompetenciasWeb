package co.edu.unicauca.competencias.proyectoweb.Rubrica_module.Rubrica_infrastucture.persistence.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CriterioDTO {
    private Integer id;
    private String descripcion;
    private Integer ponderacion;
    private Integer rubricaId;
    private String estado;
}
