package co.edu.unicauca.competencias.proyectoweb.Teacher_module.Teacher_service.interfaces;

import co.edu.unicauca.competencias.proyectoweb.Teacher_module.Teacher_infrastructure.persistence.DTO.TeacherDTO;

import java.util.List;
import java.util.Optional;

public interface ITeacherService {
    List<TeacherDTO> getAllTeachers();
    List<TeacherDTO> getAllActiveTeachers();
    Optional<TeacherDTO> getTeacherById(Integer id);
    Optional<TeacherDTO> getTeacherByName(String identification);
    Optional<TeacherDTO> createTeacher(TeacherDTO teacher);
    Optional<TeacherDTO> updateTeacher(Integer id, TeacherDTO teacher);
    int changeStatus(Integer id, String status);
}
