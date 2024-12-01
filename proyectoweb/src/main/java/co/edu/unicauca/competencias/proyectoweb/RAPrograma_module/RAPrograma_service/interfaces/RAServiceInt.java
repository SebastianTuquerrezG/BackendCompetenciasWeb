package co.edu.unicauca.competencias.proyectoweb.RAPrograma_module.RAPrograma_service.interfaces;

import java.util.List;
import co.edu.unicauca.competencias.proyectoweb.RAPrograma_module.RAPrograma_infraestructure.persistence.DTO.RAProgramaDTO;

public interface RAServiceInt {
    List<RAProgramaDTO> findAll();

    RAProgramaDTO findById(Integer id);

    void save(RAProgramaDTO raPrograma);

    RAProgramaDTO update(RAProgramaDTO raPrograma, Integer id);

    boolean delete(Integer id);
    
} 
