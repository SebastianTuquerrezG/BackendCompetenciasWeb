package co.edu.unicauca.competencias.proyectoweb.Rubrica_module.Rubrica_infrastucture.persistence.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NivelDTO {
    private Integer id;
    private String nombre;
    private String descripcion;
    private Integer criterioEvaluacionId;
    private Float rangoMin;
    private Float rangoMax;
}