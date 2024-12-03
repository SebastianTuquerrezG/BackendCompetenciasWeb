package co.edu.unicauca.competencias.proyectoweb.Rubrica_module.Rubrica_core.repository;

import co.edu.unicauca.competencias.proyectoweb.Rubrica_module.Rubrica_core.entities.CriterioEvaluacion;
import co.edu.unicauca.competencias.proyectoweb.Rubrica_module.Rubrica_core.entities.NivelDesempenio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface INivelDesempenioRepository extends JpaRepository<NivelDesempenio, Integer> {
    List<NivelDesempenio> findByCriterioEvaluacionId(Integer id);
}
