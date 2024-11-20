package co.edu.unicauca.competencias.proyectoweb.Teacher_module.Teacher_infrastructure.controllers;

import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import co.edu.unicauca.competencias.proyectoweb.Teacher_module.Teacher_core.entities.Teacher;
import co.edu.unicauca.competencias.proyectoweb.Teacher_module.Teacher_service.interfaces.ITeacherService;
import org.springframework.http.HttpStatus;


import java.util.List;

@RestController
@RequestMapping("/teachers")
@CrossOrigin(origins = "*",maxAge = 3600)
public class TeacherController {

    @Autowired
    private ITeacherService teacherService;

    @GetMapping("/getAllTeachers")
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        List<Teacher> teachers = teacherService.getAllTeachers();
        return ResponseEntity.ok(teachers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTeacherById(@PathVariable Integer id) {
    Optional<Teacher> teacher = teacherService.getTeacherById(id);
    if (teacher.isPresent()) {
        return ResponseEntity.ok(teacher.get());
    } else {
        return new ResponseEntity<>("Teacher not found", HttpStatus.NOT_FOUND);
    }
    }

    @PostMapping("/createTeacher")
    public ResponseEntity<Teacher> createTeacher(@RequestBody Teacher teacher) {
        Teacher createdTeacher = teacherService.createTeacher(teacher);
        return new ResponseEntity<>(createdTeacher, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTeacher(@PathVariable Integer id, @RequestBody Teacher teacher) {
    Optional<Teacher> updatedTeacher = teacherService.updateTeacher(id, teacher);
    if (updatedTeacher.isPresent()) {
        return ResponseEntity.ok(updatedTeacher.get());
    } else {
        return new ResponseEntity<>("Teacher not found", HttpStatus.NOT_FOUND);
    }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTeacher(@PathVariable Integer id) {
        if (teacherService.deleteTeacher(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>("Teacher not found", HttpStatus.NOT_FOUND);
        }
    }
}