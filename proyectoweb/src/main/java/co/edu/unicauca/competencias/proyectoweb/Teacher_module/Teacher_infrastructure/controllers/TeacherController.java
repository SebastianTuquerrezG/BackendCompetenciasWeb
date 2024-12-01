package co.edu.unicauca.competencias.proyectoweb.Teacher_module.Teacher_infrastructure.controllers;

import java.util.Optional;

import co.edu.unicauca.competencias.proyectoweb.Teacher_module.Teacher_infrastructure.persistence.DTO.TeacherDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.edu.unicauca.competencias.proyectoweb.Teacher_module.Teacher_service.interfaces.ITeacherService;
import org.springframework.http.HttpStatus;


import java.util.List;

@RestController
@RequestMapping("/api/teachers")
@CrossOrigin(origins = "*",maxAge = 3600)
public class TeacherController {
    private final ITeacherService teacherService;

    public TeacherController(ITeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping()
    public ResponseEntity<List<TeacherDTO>> getAllTeachers() {
        return ResponseEntity.ok(teacherService.getAllTeachers());
    }

    @GetMapping("/active")
    public ResponseEntity<List<TeacherDTO>> getAllActiveTeachers() {
        return ResponseEntity.ok(teacherService.getAllActiveTeachers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDTO> getTeacherById(@PathVariable Integer id) {
        return ResponseEntity.of(teacherService.getTeacherById(id));
    }

    @GetMapping("/name/{names}")
    public ResponseEntity<TeacherDTO> getTeacherByName(@PathVariable String names) {
        return ResponseEntity.of(teacherService.getTeacherByName(names));
    }

    @PostMapping()
    public ResponseEntity<Optional<TeacherDTO>> createTeacher(@RequestBody TeacherDTO teacher) {
        return ResponseEntity.ok(teacherService.createTeacher(teacher));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<TeacherDTO>> updateTeacher(@PathVariable Integer id, @RequestBody TeacherDTO teacher) {
            return ResponseEntity.ok(teacherService.updateTeacher(id, teacher));
    }

    @PutMapping("/status/{id}/{status}")
    public ResponseEntity<Integer> changeStatus(@PathVariable Integer id, @PathVariable String status) {
        return ResponseEntity.ok(teacherService.changeStatus(id, status));
    }
}