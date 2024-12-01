package co.edu.unicauca.competencias.proyectoweb.Asignatura_module.Asignatura_service.interfaces;

import co.edu.unicauca.competencias.proyectoweb.Asignatura_module.Asignatura_infraestructure.persistence.DTO.AsignaturaDTO;

import java.util.List;
import java.util.Optional;

public interface IAsignaturaService {
    List<AsignaturaDTO> findAllAsignatura();
    List<AsignaturaDTO> findActives();
    Optional<AsignaturaDTO> findById(Integer id);
    Optional<AsignaturaDTO> findByNombre(String nombre);
    Optional<AsignaturaDTO> saveAsignatura(AsignaturaDTO asignatura);
    Optional<AsignaturaDTO> updateAsignatura(Integer id,AsignaturaDTO asignatura);
    int changeStatus(Integer id, String status);
}
