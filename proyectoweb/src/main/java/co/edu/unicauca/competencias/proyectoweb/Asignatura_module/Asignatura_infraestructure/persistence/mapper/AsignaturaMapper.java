package co.edu.unicauca.competencias.proyectoweb.Asignatura_module.Asignatura_infraestructure.persistence.mapper;

import co.edu.unicauca.competencias.proyectoweb.Asignatura_module.Asignatura_core.entities.Asignatura;
import co.edu.unicauca.competencias.proyectoweb.Asignatura_module.Asignatura_infraestructure.persistence.DTO.AsignaturaDTO;
import org.mapstruct.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Mapper(componentModel = "spring")
public interface AsignaturaMapper {
    @Bean(name = "asignaturaModelMapper")
    default ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.typeMap(Asignatura.class, AsignaturaDTO.class).addMappings(mapper -> {
            mapper.map(Asignatura::getCreated_at, AsignaturaDTO::setCreated_at);
            mapper.map(Asignatura::getUpdated_at, AsignaturaDTO::setUpdated_at);
        });

        return modelMapper;
    }
}
