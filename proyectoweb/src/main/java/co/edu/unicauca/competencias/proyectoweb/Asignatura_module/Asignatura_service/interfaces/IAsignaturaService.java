package co.edu.unicauca.competencias.proyectoweb.Asignatura_module.Asignatura_service.interfaces;

import co.edu.unicauca.competencias.proyectoweb.Asignatura_module.Asignatura_core.entities.Asignatura;

import java.util.List;
import java.util.Optional;

public interface IAsignaturaService {
    public List<Asignatura> findAllAsignatura();
    public List<Asignatura> findActives();
    public Optional<Asignatura> findById(Integer id);
    public Optional<Asignatura> findByNombre(String nombre);
    public Optional<Asignatura> saveAsignatura(Asignatura asignatura);
    public Optional<Asignatura> updateAsignatura(Asignatura asignatura);
    public int changeStatus(Integer id, Asignatura.Status status);
}
