package co.edu.unicauca.competencias.proyectoweb.Rubrica_module.Rubrica_core.repository;

import co.edu.unicauca.competencias.proyectoweb.Rubrica_module.Rubrica_core.entities.CriterioEvaluacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICriterioEvaluacionRepository extends JpaRepository<CriterioEvaluacion, Integer> {
    List<CriterioEvaluacion> findByRubricaId(Integer id);
}
