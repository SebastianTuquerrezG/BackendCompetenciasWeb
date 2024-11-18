package co.edu.unicauca.competencias.proyectoweb.Asignatura_module.Asignatura_infraestructure.controllers;

import co.edu.unicauca.competencias.proyectoweb.Asignatura_module.Asignatura_core.entities.Asignatura;
import co.edu.unicauca.competencias.proyectoweb.Asignatura_module.Asignatura_service.interfaces.IAsignaturaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//TODO: Implementar validacion de rol
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/asignatura")
@RequiredArgsConstructor
public class AsignaturaController {
    @Autowired
    private IAsignaturaService asignaturaService;

    @GetMapping
    public List<Asignatura> findAllAsignatura(){
        return asignaturaService.findAllAsignatura();
    }

    @GetMapping("/activos")
    public List<Asignatura> findActives(){
        return asignaturaService.findActives();
    }

    @GetMapping("/{id}")
    public Optional<Asignatura> findById(@PathVariable Integer id){
        return asignaturaService.findById(id);
    }

    @GetMapping("/nombre/{nombre}")
    public Optional<Asignatura> findByNombre(@PathVariable String nombre){
        return asignaturaService.findByNombre(nombre);
    }

    @PostMapping
    public Optional<Asignatura> saveAsignatura(@RequestBody Asignatura asignatura){
        return asignaturaService.saveAsignatura(asignatura);
    }

    @PutMapping
    public Optional<Asignatura> updateAsignatura(@RequestBody Asignatura asignatura){
        return asignaturaService.updateAsignatura(asignatura);
    }

    @PutMapping("/status/{id}/{status}")
    public int changeStatus(@PathVariable Integer id, @PathVariable Asignatura.Status status){
        return asignaturaService.changeStatus(id, status);
    }
}
