package co.edu.unicauca.competencias.proyectoweb.Asignatura_module.Asignatura_infraestructure.persistence.mapper;

import co.edu.unicauca.competencias.proyectoweb.Asignatura_module.Asignatura_core.entities.Asignatura;
import co.edu.unicauca.competencias.proyectoweb.Asignatura_module.Asignatura_infraestructure.persistence.model.AsignaturaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AsignaturaMapper {
    @Mapping(source = "status", target = "status", qualifiedByName = "stringToStatus")
    Asignatura toAsignatura(AsignaturaEntity asignaturaEntity);

    @Mapping(source = "status", target = "status", qualifiedByName = "statusToString")
    AsignaturaEntity toAsignaturaEntity(Asignatura asignatura);

    List<Asignatura> toAsignaturas(List<AsignaturaEntity> asignaturaEntities);
    List<AsignaturaEntity> toAsignaturasEntities(List<Asignatura> asignaturas);

    @Named("statusToString")
    default String statusToString(Asignatura.Status status) {
        return status != null ? status.name() : null;
    }

    @Named("stringToStatus")
    default Asignatura.Status stringToStatus(String status) {
        return status != null ? Asignatura.Status.valueOf(status) : null;
    }
}
