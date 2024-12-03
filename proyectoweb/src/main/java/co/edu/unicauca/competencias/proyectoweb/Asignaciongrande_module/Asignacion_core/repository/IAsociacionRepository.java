package co.edu.unicauca.competencias.proyectoweb.Asignaciongrande_module.Asignacion_core.repository;

import co.edu.unicauca.competencias.proyectoweb.Asignaciongrande_module.Asignacion_core.entities.Asociar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAsociacionRepository extends JpaRepository<Asociar, Integer> {
    List<Asociar> findByAsignaturaId(Integer id_asignatura);
    List<Asociar> findByCompetenciaAsignaturaIdCompetenciaAsignatura(Integer id_competencia_asignatura);
    List<Asociar> findByTeacherId(Integer id_teacher);
}
