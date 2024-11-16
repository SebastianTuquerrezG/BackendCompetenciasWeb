package co.edu.unicauca.competencias.proyectoweb.Teacher_module.Teacher_service.interfaces;

import co.edu.unicauca.competencias.proyectoweb.Teacher_module.Teacher_core.entities.Teacher;

import java.util.List;
import java.util.Optional;

public interface ITeacherService {
    List<Teacher> getAllTeachers();
    Optional<Teacher> getTeacherById(Integer id);
    Teacher createTeacher(Teacher teacher);
    Optional<Teacher> updateTeacher(Integer id, Teacher teacher);
    boolean deleteTeacher(Integer id);
}
