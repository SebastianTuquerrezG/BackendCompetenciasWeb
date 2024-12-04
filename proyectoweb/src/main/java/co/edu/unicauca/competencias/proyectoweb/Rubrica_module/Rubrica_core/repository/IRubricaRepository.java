package co.edu.unicauca.competencias.proyectoweb.Rubrica_module.Rubrica_core.repository;

import co.edu.unicauca.competencias.proyectoweb.Rubrica_module.Rubrica_core.entities.Rubrica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IRubricaRepository extends JpaRepository<Rubrica, Integer> {
    List<Rubrica> findRubricaByRaAsignaturaId(Integer id);
}
