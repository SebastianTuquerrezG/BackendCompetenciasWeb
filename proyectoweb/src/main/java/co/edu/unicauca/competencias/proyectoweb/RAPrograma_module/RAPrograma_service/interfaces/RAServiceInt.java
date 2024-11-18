package co.edu.unicauca.competencias.proyectoweb.RAPrograma_module.RAPrograma_service.interfaces;

import java.util.List;
import co.edu.unicauca.competencias.proyectoweb.RAPrograma_module.RAPrograma_infraestructure.persistence.DTO.RAProgramaDTO;

public interface RAServiceInt {
    /**
     * @Brief Metodo que retorna una lista con todos los resultados de aprendizaje de programa
     */
    public List<RAProgramaDTO> findAll();

    /**
     * @Brief Metodo que retorna un resultado de aprendizaje de programa dado su id
     * @param id
     */
    public RAProgramaDTO findById(Integer id);

    /**
     * @Brief Metodo que crea un resultado de aprendizaje de programa
     * @param raPrograma
     */
    public void save(RAProgramaDTO raPrograma);

    /**
     * @Brief Metodo que actualiza un resultado de aprendizaje de programa
     * @param raPrograma
     * @param id
     */
    public RAProgramaDTO update(RAProgramaDTO raPrograma, Integer id);

    /**
     * @Brief Metodo que elimina un resultado de aprendizaje de programa dado su id
     * @param id
     */
    public boolean delete(Integer id);
    
} 
