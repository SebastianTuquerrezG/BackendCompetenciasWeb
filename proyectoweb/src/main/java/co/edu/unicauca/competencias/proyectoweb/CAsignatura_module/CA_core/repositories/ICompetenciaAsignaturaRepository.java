package co.edu.unicauca.competencias.proyectoweb.CAsignatura_module.CA_core.repositories;

import co.edu.unicauca.competencias.proyectoweb.CAsignatura_module.CA_core.entities.CompetenciaAsignatura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICompetenciaAsignaturaRepository extends JpaRepository<CompetenciaAsignatura, Integer> {
    public List<CompetenciaAsignatura> findCompetenciaAsignaturasByStatus(CompetenciaAsignatura.Status status);
}
