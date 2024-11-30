package co.edu.unicauca.competencias.proyectoweb.CAsignatura_module.CA_infrastructure.persistence.mapper;

import co.edu.unicauca.competencias.proyectoweb.CAsignatura_module.CA_core.entities.CompetenciaAsignatura;
import co.edu.unicauca.competencias.proyectoweb.CAsignatura_module.CA_infrastructure.persistence.DTO.CompetenciaAsignaturaDTO;
import co.edu.unicauca.competencias.proyectoweb.CompetenciasPrograma_module.CompetenciasPrograma_core.entities.CompetenciaPrograma;
import org.mapstruct.Mapper;
import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Mapper(componentModel = "spring")
public interface mapper {
    @Bean(name = "ca_mapper")
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
}
