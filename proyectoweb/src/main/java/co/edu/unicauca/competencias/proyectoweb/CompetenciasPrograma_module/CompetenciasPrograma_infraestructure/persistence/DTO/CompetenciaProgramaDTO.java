package co.edu.unicauca.competencias.proyectoweb.CompetenciasPrograma_module.CompetenciasPrograma_infraestructure.persistence.DTO;

import co.edu.unicauca.competencias.proyectoweb.CompetenciasPrograma_module.CompetenciasPrograma_core.entities.NivelCompetencia;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompetenciaProgramaDTO {
    private Integer id;
    private String descripcion;
    @Enumerated(EnumType.STRING)
    private NivelCompetencia nivel; // Basico, intermedio, avanzado
    private Integer estado;
}
