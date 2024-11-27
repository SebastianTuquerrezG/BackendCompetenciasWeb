package co.edu.unicauca.competencias.proyectoweb.Asignatura_module.Asignatura_service.interfaces;

import co.edu.unicauca.competencias.proyectoweb.Asignatura_module.Asignatura_infraestructure.persistence.DTO.AsignaturaDTO;

import java.util.List;
import java.util.Optional;

public interface IAsignaturaService {
    public List<AsignaturaDTO> findAllAsignatura();
    public List<AsignaturaDTO> findActives();
    public Optional<AsignaturaDTO> findById(Integer id);
    public Optional<AsignaturaDTO> findByNombre(String nombre);
    public Optional<AsignaturaDTO> saveAsignatura(AsignaturaDTO asignatura);
    public Optional<AsignaturaDTO> updateAsignatura(Integer id,AsignaturaDTO asignatura);
    public int changeStatus(Integer id, String status);
}
