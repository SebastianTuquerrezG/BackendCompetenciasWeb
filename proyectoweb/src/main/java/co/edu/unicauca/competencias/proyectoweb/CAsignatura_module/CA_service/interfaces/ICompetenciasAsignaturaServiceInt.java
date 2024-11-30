package co.edu.unicauca.competencias.proyectoweb.CAsignatura_module.CA_service.interfaces;

import co.edu.unicauca.competencias.proyectoweb.CAsignatura_module.CA_infrastructure.persistence.DTO.CompetenciaAsignaturaDTO;

import java.util.List;

public interface ICompetenciasAsignaturaServiceInt {
    List<CompetenciaAsignaturaDTO> findAll();
    CompetenciaAsignaturaDTO findById(Integer id);
    List<CompetenciaAsignaturaDTO> findActives(String status);
    CompetenciaAsignaturaDTO save(CompetenciaAsignaturaDTO competenciaAsignaturaDTO);
    CompetenciaAsignaturaDTO update(Integer id, CompetenciaAsignaturaDTO competenciaAsignaturaDTO);
    int updateByStatus(Integer id, String status);
}
