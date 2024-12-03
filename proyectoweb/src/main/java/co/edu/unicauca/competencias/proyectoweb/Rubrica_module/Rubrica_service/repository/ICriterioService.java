package co.edu.unicauca.competencias.proyectoweb.Rubrica_module.Rubrica_service.repository;

import co.edu.unicauca.competencias.proyectoweb.Rubrica_module.Rubrica_infrastucture.persistence.DTO.CriterioDTO;
import io.swagger.models.auth.In;

import java.util.List;

public interface ICriterioService {
    List<CriterioDTO> findAll();
    CriterioDTO findById(Integer id);
    List<CriterioDTO> findByRubricaId(Integer id);
    CriterioDTO save(CriterioDTO criterio);
    CriterioDTO update(CriterioDTO criterio);
    boolean delete(Integer id);
}
