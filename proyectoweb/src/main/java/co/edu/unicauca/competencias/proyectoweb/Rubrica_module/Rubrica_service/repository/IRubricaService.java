package co.edu.unicauca.competencias.proyectoweb.Rubrica_module.Rubrica_service.repository;

import co.edu.unicauca.competencias.proyectoweb.RAAsignatura_module.RAAsignatura_core.entities.RAAsignatura;
import co.edu.unicauca.competencias.proyectoweb.RAAsignatura_module.RAAsignatura_infraestructure.persistence.DTO.RAAsignaturaDTO;
import co.edu.unicauca.competencias.proyectoweb.Rubrica_module.Rubrica_infrastucture.persistence.DTO.RubricaDTO;
import io.swagger.models.auth.In;

import java.util.List;

public interface IRubricaService {
    List<RubricaDTO> findAll();
    List<RubricaDTO> findByRaAsignaturaId(Integer raAsignaturaId);
    RubricaDTO findById(Integer id);
    RubricaDTO save(RubricaDTO rubricaDTO);
    RubricaDTO update(RubricaDTO rubricaDTO);
    boolean delete(Integer id);
}
