package co.edu.unicauca.competencias.proyectoweb.Rubrica_module.Rubrica_infrastucture.persistence.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RubricaDTO {
    private Integer id;
    private String nombre;
    private Integer raAsignaturaId;
    private String estado;
    private Date createAt;
    private Date updateAt;
}
