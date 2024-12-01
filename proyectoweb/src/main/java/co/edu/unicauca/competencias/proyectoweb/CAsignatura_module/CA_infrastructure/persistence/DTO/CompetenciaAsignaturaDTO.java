package co.edu.unicauca.competencias.proyectoweb.CAsignatura_module.CA_infrastructure.persistence.DTO;

import co.edu.unicauca.competencias.proyectoweb.RAAsignatura_module.RAAsignatura_infraestructure.persistence.DTO.RAAsignaturaDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompetenciaAsignaturaDTO {
    private Integer id;
    private Integer competenciaprograma;
    private String descripcion;
    private String nivel;
    private String status;
    private List<RAAsignaturaDTO> raAsignaturas = new ArrayList<>();
}
