package co.edu.unicauca.competencias.proyectoweb.CompetenciasPrograma_module.CompetenciasPrograma_infraestructure.persistence.mapper;

import co.edu.unicauca.competencias.proyectoweb.CAsignatura_module.CA_core.entities.CompetenciaAsignatura;
import co.edu.unicauca.competencias.proyectoweb.CAsignatura_module.CA_infrastructure.persistence.DTO.CompetenciaAsignaturaDTO;
import co.edu.unicauca.competencias.proyectoweb.CompetenciasPrograma_module.CompetenciasPrograma_core.entities.CompetenciaPrograma;
import co.edu.unicauca.competencias.proyectoweb.CompetenciasPrograma_module.CompetenciasPrograma_infraestructure.persistence.DTO.CompetenciaProgramaDTO;
import co.edu.unicauca.competencias.proyectoweb.RAPrograma_module.RAPrograma_core.entities.RAPrograma;
import co.edu.unicauca.competencias.proyectoweb.RAPrograma_module.RAPrograma_infraestructure.persistence.DTO.RAProgramaDTO;
import org.mapstruct.Mapper;
import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.stream.Collectors;

@Configuration
@Mapper(componentModel = "spring")
public interface CompetenciaProgramaMapper {
    @Bean(name = "cp_mapper")
    @Qualifier("cp_mapper")
     default ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.createTypeMap(CompetenciaPrograma.class, CompetenciaProgramaDTO.class)
                .addMappings(mapper -> {
                    mapper.map(CompetenciaPrograma::getId, CompetenciaProgramaDTO::setId);
                    mapper.map(CompetenciaPrograma::getDescripcion, CompetenciaProgramaDTO::setDescripcion);
                    mapper.map(src -> src.getNivel() != null ? src.getNivel().name() : null,
                            CompetenciaProgramaDTO::setNivel);
                    mapper.map(CompetenciaPrograma::getEstado, CompetenciaProgramaDTO::setEstado);

                    mapper.skip(CompetenciaProgramaDTO::setCompetenciasAsignaturas);
                    mapper.skip(CompetenciaProgramaDTO::setListResultadosAprendizaje);
                });

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
                dto.setNivel(String.valueOf(asignatura.getNivelCompetencia()));
                dto.setStatus(String.valueOf(asignatura.getStatus()));

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

    static RAProgramaDTO getRaProgramaDTO(RAPrograma raPrograma) {
        RAProgramaDTO dto = new RAProgramaDTO();
        dto.setId(raPrograma.getId());
        dto.setDescripcion(raPrograma.getDescripcion());
        dto.setEstado(raPrograma.getEstado());

        if (raPrograma.getIdCompetenciaPrograma() != null) {
            dto.setIdCompetenciaPrograma(raPrograma.getIdCompetenciaPrograma().getId());
        }

        return dto;
    }
}
