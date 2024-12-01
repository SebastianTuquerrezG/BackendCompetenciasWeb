package co.edu.unicauca.competencias.proyectoweb.CompetenciasPrograma_module.CompetenciasPrograma_infraestructure.persistence.DTO;

import co.edu.unicauca.competencias.proyectoweb.CAsignatura_module.CA_infrastructure.persistence.DTO.CompetenciaAsignaturaDTO;
import co.edu.unicauca.competencias.proyectoweb.RAPrograma_module.RAPrograma_infraestructure.persistence.DTO.RAProgramaDTO;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompetenciaProgramaDTO {
    private Integer id;
    private String descripcion;
    private String nivel;
    private Integer estado;
    private List<CompetenciaAsignaturaDTO> competenciasAsignaturas = new ArrayList<>();
    private List<RAProgramaDTO> listResultadosAprendizaje = new ArrayList<>();
}
