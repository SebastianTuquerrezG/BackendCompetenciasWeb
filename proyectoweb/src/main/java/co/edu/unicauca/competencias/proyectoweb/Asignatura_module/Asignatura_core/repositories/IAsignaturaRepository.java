package co.edu.unicauca.competencias.proyectoweb.Asignatura_module.Asignatura_core.repositories;

import co.edu.unicauca.competencias.proyectoweb.Asignatura_module.Asignatura_core.entities.Asignatura;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IAsignaturaRepository {
    List<Asignatura> findAll();
    Asignatura findById(Integer id);
    List<Asignatura> findByNombre(String nombre);
    List<Asignatura> findAsignaturaByStatus(Asignatura.Status status);
    Asignatura save(Asignatura asignatura);
    Asignatura update(Asignatura asignatura);
    boolean desactivate(Integer id);
    boolean activate(Integer id);
}
