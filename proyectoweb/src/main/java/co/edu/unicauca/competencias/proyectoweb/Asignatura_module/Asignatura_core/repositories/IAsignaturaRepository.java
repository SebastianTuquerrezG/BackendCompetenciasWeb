package co.edu.unicauca.competencias.proyectoweb.Asignatura_module.Asignatura_core.repositories;

import co.edu.unicauca.competencias.proyectoweb.Asignatura_module.Asignatura_core.entities.Asignatura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAsignaturaRepository extends JpaRepository<Asignatura, Integer> {
    List<Asignatura> findAll();

    Asignatura findById(int id);

    Asignatura findByNombre(String nombre);

    Asignatura create(Asignatura asignatura);

    Asignatura update(Asignatura asignatura);

    boolean delete(int id);
}
