package co.edu.unicauca.competencias.proyectoweb.CompetenciasPrograma_module.CompetenciasPrograma_service.interfaces;

import java.util.List;
import co.edu.unicauca.competencias.proyectoweb.CompetenciasPrograma_module.CompetenciasPrograma_infraestructure.persistence.DTO.CompetenciaProgramaDTO;

public interface CompetenciasProgramaServiceInt {
    List<CompetenciaProgramaDTO> findAll();

    CompetenciaProgramaDTO findById(Integer id);

    CompetenciaProgramaDTO save(CompetenciaProgramaDTO competenciaPrograma);

    CompetenciaProgramaDTO update(CompetenciaProgramaDTO competenciaPrograma, Integer id);

    boolean delete(Integer id);
} 
