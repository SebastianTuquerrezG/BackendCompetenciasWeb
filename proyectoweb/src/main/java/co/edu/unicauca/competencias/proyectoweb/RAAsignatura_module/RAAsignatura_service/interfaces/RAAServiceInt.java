package co.edu.unicauca.competencias.proyectoweb.RAAsignatura_module.RAAsignatura_service.interfaces;

import co.edu.unicauca.competencias.proyectoweb.RAAsignatura_module.RAAsignatura_infraestructure.persistence.DTO.RAAsignaturaDTO;

import java.util.List;

public interface RAAServiceInt {
    List<RAAsignaturaDTO> findAll();

    RAAsignaturaDTO findById(Integer id);

    RAAsignaturaDTO save(RAAsignaturaDTO raPrograma);

    RAAsignaturaDTO update(RAAsignaturaDTO raPrograma, Integer id);

    boolean delete(Integer id);
    
} 
