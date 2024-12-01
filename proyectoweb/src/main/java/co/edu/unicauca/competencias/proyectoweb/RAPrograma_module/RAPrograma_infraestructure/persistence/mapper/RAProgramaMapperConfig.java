package co.edu.unicauca.competencias.proyectoweb.RAPrograma_module.RAPrograma_infraestructure.persistence.mapper;

import co.edu.unicauca.competencias.proyectoweb.CAsignatura_module.CA_core.entities.CompetenciaAsignatura;
import co.edu.unicauca.competencias.proyectoweb.CAsignatura_module.CA_infrastructure.persistence.DTO.CompetenciaAsignaturaDTO;
import co.edu.unicauca.competencias.proyectoweb.CompetenciasPrograma_module.CompetenciasPrograma_core.entities.CompetenciaPrograma;
import co.edu.unicauca.competencias.proyectoweb.CompetenciasPrograma_module.CompetenciasPrograma_core.entities.NivelCompetencia;
import co.edu.unicauca.competencias.proyectoweb.CompetenciasPrograma_module.CompetenciasPrograma_infraestructure.persistence.DTO.CompetenciaProgramaDTO;
import co.edu.unicauca.competencias.proyectoweb.CompetenciasPrograma_module.CompetenciasPrograma_infraestructure.persistence.mapper.CompetenciaProgramaMapper;
import co.edu.unicauca.competencias.proyectoweb.RAPrograma_module.RAPrograma_core.entities.RAPrograma;
import co.edu.unicauca.competencias.proyectoweb.RAPrograma_module.RAPrograma_infraestructure.persistence.DTO.RAProgramaDTO;
import org.mapstruct.Mapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.modelmapper.config.Configuration.AccessLevel;

import java.util.Collections;

@Configuration
@Mapper(componentModel = "spring")
public interface RAProgramaMapperConfig {
    @Bean(name = "raProgramaModelMapper")
    @Qualifier("raProgramaModelMapper")
    default ModelMapper raProgramaModelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.LOOSE)
                .setPropertyCondition(context -> context.getSourceType() != null);

        TypeMap<RAPrograma, RAProgramaDTO> raProgramaToDto = modelMapper.createTypeMap(RAPrograma.class, RAProgramaDTO.class);
        raProgramaToDto.addMappings(mapper -> {
            mapper.map(RAPrograma::getId, RAProgramaDTO::setId);
            mapper.map(RAPrograma::getDescripcion, RAProgramaDTO::setDescripcion);
            mapper.map(RAPrograma::getEstado, RAProgramaDTO::setEstado);

            mapper.map(src -> src.getIdCompetenciaPrograma() != null
                            ? src.getIdCompetenciaPrograma().getId()
                            : null,
                    RAProgramaDTO::setIdCompetenciaPrograma);
        });

        TypeMap<RAProgramaDTO, RAPrograma> reverseTypeMap = modelMapper.createTypeMap(RAProgramaDTO.class, RAPrograma.class);
        reverseTypeMap.addMappings(mapper -> {
            mapper.map(RAProgramaDTO::getId, RAPrograma::setId);
            mapper.map(RAProgramaDTO::getDescripcion, RAPrograma::setDescripcion);
            mapper.map(RAProgramaDTO::getEstado, RAPrograma::setEstado);

            mapper.map(src -> {
                if (src.getIdCompetenciaPrograma() != null) {
                    CompetenciaPrograma competenciaPrograma = new CompetenciaPrograma();
                    competenciaPrograma.setId(src.getIdCompetenciaPrograma());
                    return competenciaPrograma;
                }
                return null;
            }, RAPrograma::setIdCompetenciaPrograma);
        });
        return modelMapper;
    }
}
