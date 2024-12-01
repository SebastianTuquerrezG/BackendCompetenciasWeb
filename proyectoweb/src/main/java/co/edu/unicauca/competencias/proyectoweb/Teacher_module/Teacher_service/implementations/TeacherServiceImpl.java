package co.edu.unicauca.competencias.proyectoweb.Teacher_module.Teacher_service.implementations;

import co.edu.unicauca.competencias.proyectoweb.Teacher_module.Teacher_core.entities.Teacher;
import co.edu.unicauca.competencias.proyectoweb.Teacher_module.Teacher_core.repositories.ITeacherRepository;
import co.edu.unicauca.competencias.proyectoweb.Teacher_module.Teacher_infrastructure.persistence.DTO.TeacherDTO;
import co.edu.unicauca.competencias.proyectoweb.Teacher_module.Teacher_service.interfaces.ITeacherService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl implements ITeacherService {
    private final ITeacherRepository teacherRepository;

    @Qualifier("teacherModelMapper")
    private final ModelMapper modelMapper;

    public TeacherServiceImpl(ITeacherRepository teacherRepository, ModelMapper modelMapper) {
        this.teacherRepository = teacherRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<TeacherDTO> getAllTeachers() {
        List<Teacher> teachers = teacherRepository.findAll();

        return teachers.stream().map(teacher -> modelMapper.map(teacher, TeacherDTO.class)).collect(Collectors.toList());
    }

    @Override
    public Optional<TeacherDTO> getTeacherById(Integer id) {
        Teacher teacher = teacherRepository.findById(id).orElse(null);
        if (teacher == null) {
            return Optional.empty();
        }

        return Optional.of(modelMapper.map(teacher, TeacherDTO.class));
    }

    @Override
    public Optional<TeacherDTO> getTeacherByName(String names) {
        boolean teacher = teacherRepository.existsByNames(names);
        if (!teacher) {
            return Optional.empty();
        } else {
            return Optional.of(modelMapper.map(teacherRepository.findByNames(names), TeacherDTO.class));
        }
    }

    @Override
    public List<TeacherDTO> getAllActiveTeachers() {
        List<Teacher> teachers = teacherRepository.findAll();
        List<Teacher> activeTeachers = new ArrayList<>();
        for (Teacher teacher : teachers) {
            if (teacher.getStatus() == Teacher.status.ACTIVO) {
                activeTeachers.add(teacher);
            }
        }

        return activeTeachers.stream().map(teacher -> modelMapper.map(teacher, TeacherDTO.class)).collect(Collectors.toList());
    }

    @Override
    public Optional<TeacherDTO> createTeacher(TeacherDTO teacher) {
        boolean teacherExists = teacherRepository.existsByIdentification(teacher.getIdentification());
        if (teacherExists) {
            return Optional.empty();
        }

        Teacher newTeacher = modelMapper.map(teacher, Teacher.class);
        newTeacher = teacherRepository.save(newTeacher);
        teacher.setId(newTeacher.getId());
        return Optional.of(teacher);
    }

    @Override
    public Optional<TeacherDTO> updateTeacher(Integer id, TeacherDTO teacher) {
        boolean teacherExists = teacherRepository.existsById(id);
        if (!teacherExists) {
            return Optional.empty();
        }

        Teacher updatedTeacher = modelMapper.map(teacher, Teacher.class);
        updatedTeacher.setId(id);
        updatedTeacher = teacherRepository.save(updatedTeacher);
        teacher.setId(updatedTeacher.getId());
        return Optional.of(teacher);
    }

    @Override
    public int changeStatus(Integer id, String status) {
        Teacher teacher = teacherRepository.findById(id).orElse(null);
        if (teacher == null) {
            return 0;
        }

        teacher.setStatus(Teacher.status.valueOf(status));
        teacherRepository.save(teacher);
        return 1;
    }
}