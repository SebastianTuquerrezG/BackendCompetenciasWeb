package co.edu.unicauca.competencias.proyectoweb.Asignatura_module.Asignatura_core.repositories;

import co.edu.unicauca.competencias.proyectoweb.Asignatura_module.Asignatura_core.entities.Asignatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAsignaturaRepository extends JpaRepository<Asignatura, Integer> {
    Asignatura findByNombre(String nombre);
    boolean existsByNombre(String nombre);
}
