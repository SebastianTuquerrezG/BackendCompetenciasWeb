package co.edu.unicauca.competencias.proyectoweb.RAAsignatura_module.RAAsignatura_core.repositories;

import co.edu.unicauca.competencias.proyectoweb.RAAsignatura_module.RAAsignatura_core.entities.RAAsignatura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRAAsignaturaRepository extends JpaRepository<RAAsignatura, Integer> {
}