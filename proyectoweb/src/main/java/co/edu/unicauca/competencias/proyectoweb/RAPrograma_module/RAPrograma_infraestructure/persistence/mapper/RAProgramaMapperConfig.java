package co.edu.unicauca.competencias.proyectoweb.RAPrograma_module.RAPrograma_infraestructure.persistence.mapper;

import co.edu.unicauca.competencias.proyectoweb.CAsignatura_module.CA_core.entities.CompetenciaAsignatura;
import co.edu.unicauca.competencias.proyectoweb.CAsignatura_module.CA_infrastructure.persistence.DTO.CompetenciaAsignaturaDTO;
import co.edu.unicauca.competencias.proyectoweb.CompetenciasPrograma_module.CompetenciasPrograma_core.entities.CompetenciaPrograma;
import co.edu.unicauca.competencias.proyectoweb.CompetenciasPrograma_module.CompetenciasPrograma_core.entities.NivelCompetencia;
import co.edu.unicauca.competencias.proyectoweb.CompetenciasPrograma_module.CompetenciasPrograma_infraestructure.persistence.DTO.CompetenciaProgramaDTO;
import co.edu.unicauca.competencias.proyectoweb.CompetenciasPrograma_module.CompetenciasPrograma_infraestructure.persistence.mapper.mapper;
import co.edu.unicauca.competencias.proyectoweb.RAPrograma_module.RAPrograma_core.entities.RAPrograma;
import co.edu.unicauca.competencias.proyectoweb.RAPrograma_module.RAPrograma_infraestructure.persistence.DTO.RAProgramaDTO;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.stream.Collectors;

@Configuration
public class RAProgramaMapperConfig {
    @Bean
    public ModelMapper raProgramaModelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        mapper.mapperRAP(modelMapper);

        modelMapper.createTypeMap(RAProgramaDTO.class, RAPrograma.class)
                .addMappings(mapper -> {
                    mapper.map(RAProgramaDTO::getId, RAPrograma::setId);
                    mapper.map(RAProgramaDTO::getDescripcion, RAPrograma::setDescripcion);
                    mapper.map(
                            src -> {
                                if (src.getIdCompetenciaPrograma() != null) {
                                    CompetenciaPrograma competenciaPrograma = new CompetenciaPrograma();
                                    competenciaPrograma.setId(src.getIdCompetenciaPrograma());
                                    return competenciaPrograma;
                                }
                                return null;
                            },
                            RAPrograma::setIdCompetenciaPrograma
                    );
                    mapper.map(RAProgramaDTO::getEstado, RAPrograma::setEstado);
                });

        return modelMapper;
    }

    private CompetenciaProgramaDTO mapCompetenciaPrograma(CompetenciaPrograma competenciaPrograma) {
        if (competenciaPrograma == null) return null;

        CompetenciaProgramaDTO dto = new CompetenciaProgramaDTO();
        dto.setId(competenciaPrograma.getId());
        dto.setDescripcion(competenciaPrograma.getDescripcion());
        dto.setNivel(competenciaPrograma.getNivel() != null ?
                competenciaPrograma.getNivel().name() : null);
        dto.setEstado(competenciaPrograma.getEstado());

        dto.setCompetenciasAsignaturas(Collections.emptyList());

        return dto;
    }

    private CompetenciaPrograma mapCompetenciaProgramaInverso(CompetenciaProgramaDTO dto) {
        if (dto == null) return null;

        CompetenciaPrograma competenciaPrograma = new CompetenciaPrograma();
        competenciaPrograma.setId(dto.getId());
        competenciaPrograma.setDescripcion(dto.getDescripcion());
        competenciaPrograma.setNivel(dto.getNivel() != null ?
                NivelCompetencia.valueOf(dto.getNivel()) : null);
        competenciaPrograma.setEstado(dto.getEstado());

        return competenciaPrograma;
    }

    private CompetenciaAsignaturaDTO mapCompetenciaAsignatura(CompetenciaAsignatura competenciaAsignatura) {
        if (competenciaAsignatura == null) return null;

        CompetenciaAsignaturaDTO dto = new CompetenciaAsignaturaDTO();
        dto.setId(competenciaAsignatura.getIdCompetenciaAsignatura());
        dto.setDescripcion(competenciaAsignatura.getDescripcion());
        dto.setNivel(competenciaAsignatura.getNivelCompetencia());
        dto.setStatus(competenciaAsignatura.getStatus());

        return dto;
    }
}
