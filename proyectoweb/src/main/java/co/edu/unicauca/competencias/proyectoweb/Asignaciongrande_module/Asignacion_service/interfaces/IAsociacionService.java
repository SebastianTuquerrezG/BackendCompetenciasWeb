package co.edu.unicauca.competencias.proyectoweb.Asignaciongrande_module.Asignacion_service.interfaces;

import co.edu.unicauca.competencias.proyectoweb.Asignaciongrande_module.Asignacion_infraestructure.persistence.DTO.AsociacionDTO;

import java.util.List;

public interface IAsociacionService {
    List<AsociacionDTO> findAll();
    AsociacionDTO findById(Integer id);
    List<AsociacionDTO> findByIdAsignatura(Integer id_asignatura);
    List<AsociacionDTO> findByIdCompetenciaAsignatura(Integer id_competencia_asignatura);
    List<AsociacionDTO> findByIdTeacher(Integer id_teacher);
    AsociacionDTO save(AsociacionDTO asociacionDTO);
    AsociacionDTO update(AsociacionDTO asociacionDTO);
    boolean delete(Integer id);
}
