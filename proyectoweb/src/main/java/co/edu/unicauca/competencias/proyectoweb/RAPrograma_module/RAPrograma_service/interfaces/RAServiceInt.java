package co.edu.unicauca.competencias.proyectoweb.RAPrograma_module.RAPrograma_service.interfaces;

import java.util.List;
import co.edu.unicauca.competencias.proyectoweb.RAPrograma_module.RAPrograma_infraestructure.persistence.DTO.RAProgramaDTO;

public interface RAServiceInt {
    public List<RAProgramaDTO> findAll();

    public RAProgramaDTO findById(Integer id);

    public void save(RAProgramaDTO raPrograma);

    public RAProgramaDTO update(RAProgramaDTO raPrograma, Integer id);

    public boolean delete(Integer id);
    
} 
