package co.edu.unicauca.competencias.proyectoweb.CompetenciasPrograma_module.CompetenciasPrograma_service.interfaces;

import java.util.List;
import co.edu.unicauca.competencias.proyectoweb.CompetenciasPrograma_module.CompetenciasPrograma_infraestructure.persistence.DTO.CompetenciaProgramaDTO;

public interface CompetenciasProgramaServiceInt {
    /**
     * @Brief Metodo que retorna una lista con todas las competencias de programa
     */
    public List<CompetenciaProgramaDTO> findAll();

    /**
     * @Brief Metodo que retorna una competencia de programa dado su id
     * @param id
     */
    public CompetenciaProgramaDTO findById(Integer id);

    /**
     * @Brief Metodo que crea una competencia de programa
     * @param competenciaPrograma
     */
    public void save(CompetenciaProgramaDTO competenciaPrograma);

    /**
     * @Brief Metodo que actualiza una competencia de programa
     * @param competenciaPrograma
     * @param id
     */
    public CompetenciaProgramaDTO update(CompetenciaProgramaDTO competenciaPrograma, Integer id);

    /**
     * @Brief Metodo que elimina una competencia de programa dado su id
     * @param id
     */
    public boolean delete(Integer id);
    
} 
