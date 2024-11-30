package co.edu.unicauca.competencias.proyectoweb.CompetenciasPrograma_module.CompetenciasPrograma_infraestructure.persistence.mapper;

import co.edu.unicauca.competencias.proyectoweb.CAsignatura_module.CA_core.entities.CompetenciaAsignatura;
import co.edu.unicauca.competencias.proyectoweb.CAsignatura_module.CA_infrastructure.persistence.DTO.CompetenciaAsignaturaDTO;
import co.edu.unicauca.competencias.proyectoweb.CompetenciasPrograma_module.CompetenciasPrograma_core.entities.CompetenciaPrograma;
import co.edu.unicauca.competencias.proyectoweb.CompetenciasPrograma_module.CompetenciasPrograma_infraestructure.persistence.DTO.CompetenciaProgramaDTO;
import co.edu.unicauca.competencias.proyectoweb.RAPrograma_module.RAPrograma_core.entities.RAPrograma;
import co.edu.unicauca.competencias.proyectoweb.RAPrograma_module.RAPrograma_infraestructure.persistence.DTO.RAProgramaDTO;
import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.stream.Collectors;

@Configuration
public class mapper {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.createTypeMap(CompetenciaPrograma.class, CompetenciaProgramaDTO.class)
                .addMappings(mapper -> {
                    mapper.map(CompetenciaPrograma::getId, CompetenciaProgramaDTO::setId);
                    mapper.map(CompetenciaPrograma::getDescripcion, CompetenciaProgramaDTO::setDescripcion);
                    mapper.map(src -> src.getNivel() != null ? src.getNivel().name() : null,
                            CompetenciaProgramaDTO::setNivel);
                    mapper.map(CompetenciaPrograma::getEstado, CompetenciaProgramaDTO::setEstado);

                    // Explicitly skip these collections to prevent automatic conversion
                    mapper.skip(CompetenciaProgramaDTO::setCompetenciasAsignaturas);
                    mapper.skip(CompetenciaProgramaDTO::setListResultadosAprendizaje);
                });

        // Use custom converter for collections
        modelMapper.addConverter(new AbstractConverter<CompetenciaPrograma, CompetenciaProgramaDTO>() {
            @Override
            protected CompetenciaProgramaDTO convert(CompetenciaPrograma source) {
                CompetenciaProgramaDTO dto = new CompetenciaProgramaDTO();
                dto.setId(source.getId());
                dto.setDescripcion(source.getDescripcion());
                dto.setNivel(source.getNivel() != null ? source.getNivel().name() : null);
                dto.setEstado(source.getEstado());

                if (source.getCompetenciaAsignaturaList() != null) {
                    dto.setCompetenciasAsignaturas(source.getCompetenciaAsignaturaList().stream()
                            .map(this::mapCompetenciaAsignatura)
                            .collect(Collectors.toList()));
                }

                if (source.getListResultadosAprendizaje() != null) {
                    dto.setListResultadosAprendizaje(source.getListResultadosAprendizaje().stream()
                            .map(this::mapResultadoAprendizaje)
                            .collect(Collectors.toList()));
                }

                return dto;
            }

            private CompetenciaAsignaturaDTO mapCompetenciaAsignatura(CompetenciaAsignatura asignatura) {
                CompetenciaAsignaturaDTO dto = new CompetenciaAsignaturaDTO();
                dto.setId(asignatura.getIdCompetenciaAsignatura());
                dto.setDescripcion(asignatura.getDescripcion());
                dto.setNivel(asignatura.getNivelCompetencia());
                dto.setStatus(asignatura.getStatus());

                if (asignatura.getCompetenciaPrograma() != null) {
                    dto.setCompetenciaprograma(asignatura.getCompetenciaPrograma().getId());
                }

                return dto;
            }

            private RAProgramaDTO mapResultadoAprendizaje(RAPrograma raPrograma) {
                return getRaProgramaDTO(raPrograma);
            }
        });

        return modelMapper;
    }

    public static RAProgramaDTO getRaProgramaDTO(RAPrograma raPrograma) {
        RAProgramaDTO dto = new RAProgramaDTO();
        dto.setId(raPrograma.getId());
        dto.setDescripcion(raPrograma.getDescripcion());
        dto.setEstado(raPrograma.getEstado());

        if (raPrograma.getIdCompetenciaPrograma() != null) {
            dto.setIdCompetenciaPrograma(raPrograma.getIdCompetenciaPrograma().getId());
        }

        return dto;
    }

    public static void mapperRAP(ModelMapper modelMapper) {
        modelMapper.createTypeMap(RAPrograma.class, RAProgramaDTO.class)
                .addMappings(mapper -> {
                    mapper.map(RAPrograma::getId, RAProgramaDTO::setId);
                    mapper.map(RAPrograma::getDescripcion, RAProgramaDTO::setDescripcion);
                    mapper.map(src -> src.getIdCompetenciaPrograma() != null ?
                                    src.getIdCompetenciaPrograma().getId() :
                                    null,
                            RAProgramaDTO::setIdCompetenciaPrograma);
                    mapper.map(RAPrograma::getEstado, RAProgramaDTO::setEstado);
                });
    }
}
