package co.edu.unicauca.competencias.proyectoweb.Teacher_module.Teacher_infrastructure.persistence.mapper;


import co.edu.unicauca.competencias.proyectoweb.Teacher_module.Teacher_core.entities.Teacher;
import co.edu.unicauca.competencias.proyectoweb.Teacher_module.Teacher_infrastructure.persistence.DTO.TeacherDTO;
import org.mapstruct.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Mapper(componentModel = "spring")
public interface TeacherMapperConfig {
    @Bean(name = "teacherModelMapper")
    default ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.typeMap(Teacher.class, TeacherDTO.class).addMappings(mapper -> {
            mapper.map(Teacher::getCreated_at, TeacherDTO::setCreated_at);
            mapper.map(Teacher::getUpdated_at, TeacherDTO::setUpdated_at);
        });

        return modelMapper;
    }
}
