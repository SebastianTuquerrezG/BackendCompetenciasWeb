package co.edu.unicauca.competencias.proyectoweb.Rubrica_module.Rubrica_service.repository;

import co.edu.unicauca.competencias.proyectoweb.Rubrica_module.Rubrica_infrastucture.persistence.DTO.NivelDTO;

import java.util.List;

public interface INivelService {
    List<NivelDTO> findAll();
    List<NivelDTO> findByCriterioId(Integer rubricaId);
    NivelDTO findById(Integer id);
    NivelDTO save(NivelDTO nivel);
    NivelDTO update(NivelDTO nivel);
    boolean delete(Integer id);
}
