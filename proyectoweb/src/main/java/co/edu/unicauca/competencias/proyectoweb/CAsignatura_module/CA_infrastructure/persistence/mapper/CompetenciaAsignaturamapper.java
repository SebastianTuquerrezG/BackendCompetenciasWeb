package co.edu.unicauca.competencias.proyectoweb.CAsignatura_module.CA_infrastructure.persistence.mapper;

import co.edu.unicauca.competencias.proyectoweb.CAsignatura_module.CA_core.entities.CompetenciaAsignatura;
import co.edu.unicauca.competencias.proyectoweb.CAsignatura_module.CA_infrastructure.persistence.DTO.CompetenciaAsignaturaDTO;
import co.edu.unicauca.competencias.proyectoweb.CompetenciasPrograma_module.CompetenciasPrograma_core.entities.CompetenciaPrograma;
import co.edu.unicauca.competencias.proyectoweb.RAAsignatura_module.RAAsignatura_core.entities.RAAsignatura;
import co.edu.unicauca.competencias.proyectoweb.RAAsignatura_module.RAAsignatura_infraestructure.persistence.DTO.RAAsignaturaDTO;
import org.mapstruct.Mapper;
import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.stream.Collectors;

@Configuration
@Mapper(componentModel = "spring")
public interface CompetenciaAsignaturamapper {
    @Bean(name = "ca_mapper")
    @Qualifier("ca_mapper")
    default ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.createTypeMap(CompetenciaAsignatura.class, CompetenciaAsignaturaDTO.class)
                .addMappings(mapper -> {
                    mapper.map(CompetenciaAsignatura::getIdCompetenciaAsignatura, CompetenciaAsignaturaDTO::setId);
                    mapper.map(CompetenciaAsignatura::getDescripcion, CompetenciaAsignaturaDTO::setDescripcion);
                    mapper.map(CompetenciaAsignatura::getNivelCompetencia, CompetenciaAsignaturaDTO::setNivel);
                    mapper.map(CompetenciaAsignatura::getStatus, CompetenciaAsignaturaDTO::setStatus);

                    mapper.map(
                            src -> src.getCompetenciaPrograma() != null ?
                                    src.getCompetenciaPrograma().getId() :
                                    null,
                            CompetenciaAsignaturaDTO::setCompetenciaprograma
                    );
                });

        modelMapper.addConverter(new AbstractConverter<CompetenciaAsignatura, CompetenciaAsignaturaDTO>() {
            @Override
            protected CompetenciaAsignaturaDTO convert(CompetenciaAsignatura competenciaAsignatura) {
                CompetenciaAsignaturaDTO competenciaAsignaturaDTO = new CompetenciaAsignaturaDTO();
                competenciaAsignaturaDTO.setId(competenciaAsignatura.getIdCompetenciaAsignatura());
                competenciaAsignaturaDTO.setDescripcion(competenciaAsignatura.getDescripcion());
                competenciaAsignaturaDTO.setNivel(String.valueOf(competenciaAsignatura.getNivelCompetencia()));
                competenciaAsignaturaDTO.setStatus(String.valueOf(competenciaAsignatura.getStatus()));
                competenciaAsignaturaDTO.setCompetenciaprograma(
                        competenciaAsignatura.getCompetenciaPrograma() != null
                                ? competenciaAsignatura.getCompetenciaPrograma().getId()
                                : null
                );

                if (competenciaAsignatura.getRaAsignaturas() != null) {
                    competenciaAsignaturaDTO.setRaAsignaturas(
                            competenciaAsignatura.getRaAsignaturas().stream()
                                    .map(this::mapResultadoAprendizaje)
                                    .collect(Collectors.toList())
                    );
                }

                return competenciaAsignaturaDTO;
            }

            private RAAsignaturaDTO mapResultadoAprendizaje(RAAsignatura raAsignatura) {
                return getRAAsignaturaDTO(raAsignatura);
            }
        });

        modelMapper.createTypeMap(CompetenciaAsignaturaDTO.class, CompetenciaAsignatura.class)
                .addMappings(mapper -> {
                    mapper.map(CompetenciaAsignaturaDTO::getId, CompetenciaAsignatura::setIdCompetenciaAsignatura);
                    mapper.map(CompetenciaAsignaturaDTO::getDescripcion, CompetenciaAsignatura::setDescripcion);
                    mapper.map(CompetenciaAsignaturaDTO::getNivel, CompetenciaAsignatura::setNivelCompetencia);
                    mapper.map(CompetenciaAsignaturaDTO::getStatus, CompetenciaAsignatura::setStatus);

                    mapper.using(context -> {
                        Integer programaId = (Integer) context.getSource();
                        if (programaId != null) {
                            CompetenciaPrograma programa = new CompetenciaPrograma();
                            programa.setId(programaId);
                            return programa;
                        }
                        return null;
                    }).map(CompetenciaAsignaturaDTO::getCompetenciaprograma, CompetenciaAsignatura::setCompetenciaPrograma);
                });

        modelMapper.addConverter(new AbstractConverter<CompetenciaPrograma, Integer>() {
            @Override
            protected Integer convert(CompetenciaPrograma source) {
                return source != null ? source.getId() : null;
            }
        });

        return modelMapper;
    }

    static RAAsignaturaDTO getRAAsignaturaDTO(RAAsignatura raAsignatura) {
        RAAsignaturaDTO raAsignaturaDTO = new RAAsignaturaDTO();
        raAsignaturaDTO.setId(raAsignatura.getId());
        raAsignaturaDTO.setDescripcion(raAsignatura.getDescripcion());
        raAsignaturaDTO.setEstado(String.valueOf(raAsignatura.getEstado()));

        if (raAsignatura.getCompetenciaAsignatura() != null) {
            raAsignaturaDTO.setIdCompetenciaAsignatura(raAsignatura.getCompetenciaAsignatura().getIdCompetenciaAsignatura());
        }

        return raAsignaturaDTO;
    }
}
