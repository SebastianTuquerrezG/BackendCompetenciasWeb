package co.edu.unicauca.competencias.proyectoweb.Teacher_module.Teacher_service.implementations;

import co.edu.unicauca.competencias.proyectoweb.Teacher_module.Teacher_core.entities.Teacher;
import co.edu.unicauca.competencias.proyectoweb.Teacher_module.Teacher_core.repositories.ITeacherRepository;
import co.edu.unicauca.competencias.proyectoweb.Teacher_module.Teacher_service.interfaces.ITeacherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements ITeacherService {

    @Autowired
    private ITeacherRepository teacherRepository;

    @Override
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    @Override
    public Optional<Teacher> getTeacherById(Integer id) {
        return teacherRepository.findById(id);
    }

    @Override
    public Teacher createTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public Optional<Teacher> updateTeacher(Integer id, Teacher teacher) {
        return teacherRepository.findById(id).map(existingTeacher -> {
            existingTeacher.setIdentificationType(teacher.getIdentificationType());
            existingTeacher.setTeacherType(teacher.getTeacherType());
            existingTeacher.setNames(teacher.getNames());
            existingTeacher.setLastNames(teacher.getLastNames());
            existingTeacher.setIdentification(teacher.getIdentification());
            existingTeacher.setTitle(teacher.getTitle());
            return teacherRepository.save(existingTeacher);
        });
    }

    @Override
    public boolean deleteTeacher(Integer id) {
        if (teacherRepository.existsById(id)) {
            teacherRepository.deleteById(id);
            return true;
        }
        return false;
    }
}