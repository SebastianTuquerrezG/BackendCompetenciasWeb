package co.edu.unicauca.competencias.proyectoweb.CAsignatura_module.CA_infrastructure.persistence.mapper;

import co.edu.unicauca.competencias.proyectoweb.CAsignatura_module.CA_core.entities.CompetenciaAsignatura;
import co.edu.unicauca.competencias.proyectoweb.CAsignatura_module.CA_infrastructure.persistence.DTO.CompetenciaAsignaturaDTO;
import org.mapstruct.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Mapper(componentModel = "spring")
public interface mapper {
    @Bean(name = "ca_mapper")
    public default ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.typeMap(CompetenciaAsignatura.class, CompetenciaAsignaturaDTO.class).addMappings(mapper -> {
            mapper.map(src -> src.getIdCompetenciaPrograma().getId(), CompetenciaAsignaturaDTO::setCompetenciaprograma_id);
        });

        return modelMapper;
    }
}
