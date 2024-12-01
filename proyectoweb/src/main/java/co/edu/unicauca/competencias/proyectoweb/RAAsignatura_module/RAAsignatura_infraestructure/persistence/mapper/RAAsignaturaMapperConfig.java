package co.edu.unicauca.competencias.proyectoweb.RAAsignatura_module.RAAsignatura_infraestructure.persistence.mapper;

import co.edu.unicauca.competencias.proyectoweb.CAsignatura_module.CA_core.entities.CompetenciaAsignatura;
import co.edu.unicauca.competencias.proyectoweb.RAAsignatura_module.RAAsignatura_core.entities.RAAsignatura;
import co.edu.unicauca.competencias.proyectoweb.RAAsignatura_module.RAAsignatura_infraestructure.persistence.DTO.RAAsignaturaDTO;
import org.mapstruct.Mapper;
import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Mapper(componentModel = "spring")
public interface RAAsignaturaMapperConfig {
    @Bean(name = "raAsignaturaModelMapper")
    @Qualifier("raAsignaturaModelMapper")
    default ModelMapper raAsignaturaModelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.addConverter(new AbstractConverter<RAAsignatura, RAAsignaturaDTO>() {
            @Override
            protected RAAsignaturaDTO convert(RAAsignatura source) {
                if (source == null) {
                    return null;
                }

                RAAsignaturaDTO dto = new RAAsignaturaDTO();
                dto.setId(source.getId());
                dto.setDescripcion(source.getDescripcion());
                dto.setEstado(String.valueOf(source.getEstado()));

                if (source.getCompetenciaAsignatura() != null) {
                    dto.setIdCompetenciaAsignatura(source.getCompetenciaAsignatura().getIdCompetenciaAsignatura());
                }

                return dto;
            }
        });

        modelMapper.createTypeMap(RAAsignaturaDTO.class, RAAsignatura.class)
                .addMappings(mapper -> {
                    mapper.map(RAAsignaturaDTO::getId, RAAsignatura::setId);
                    mapper.map(RAAsignaturaDTO::getDescripcion, RAAsignatura::setDescripcion);
                    mapper.map(RAAsignaturaDTO::getEstado, RAAsignatura::setEstado);
                    mapper.map(
                            src -> {
                                if (src.getIdCompetenciaAsignatura() != null) {
                                    CompetenciaAsignatura competenciaAsignatura = new CompetenciaAsignatura();
                                    competenciaAsignatura.setIdCompetenciaAsignatura(src.getIdCompetenciaAsignatura());
                                    return competenciaAsignatura;
                                }
                                return null;
                            },
                            RAAsignatura::setCompetenciaAsignatura
                    );
                });

        return modelMapper;
    }
}
