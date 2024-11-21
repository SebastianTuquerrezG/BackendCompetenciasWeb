package co.edu.unicauca.competencias.proyectoweb.CAsignatura_module.CA_service.interfaces;

import co.edu.unicauca.competencias.proyectoweb.CAsignatura_module.CA_infrastructure.persistence.DTO.CompetenciaAsignaturaDTO;

import java.util.List;

public interface ICompetenciasAsignaturaServiceInt {
    public List<CompetenciaAsignaturaDTO> findAll();
    public CompetenciaAsignaturaDTO findById(Integer id);
    public List<CompetenciaAsignaturaDTO> findActives(String status);
    public CompetenciaAsignaturaDTO save(CompetenciaAsignaturaDTO competenciaAsignaturaDTO);
    public CompetenciaAsignaturaDTO update(Integer id, CompetenciaAsignaturaDTO competenciaAsignaturaDTO);
    public int updateByStatus(Integer id, String status);
}
