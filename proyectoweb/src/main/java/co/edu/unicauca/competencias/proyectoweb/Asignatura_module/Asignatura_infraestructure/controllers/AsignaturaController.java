package co.edu.unicauca.competencias.proyectoweb.Asignatura_module.Asignatura_infraestructure.controllers;

import co.edu.unicauca.competencias.proyectoweb.Asignatura_module.Asignatura_infraestructure.persistence.DTO.AsignaturaDTO;
import co.edu.unicauca.competencias.proyectoweb.Asignatura_module.Asignatura_service.interfaces.IAsignaturaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/asignatura")
public class AsignaturaController {
    private final IAsignaturaService asignaturaService;

    public AsignaturaController(IAsignaturaService asignaturaService){
        this.asignaturaService = asignaturaService;
    }

    @GetMapping
    public List<AsignaturaDTO> findAllAsignatura(){
        return asignaturaService.findAllAsignatura();
    }

    @GetMapping("/activos")
    public List<AsignaturaDTO> findActives(){
        return asignaturaService.findActives();
    }

    @GetMapping("/{id}")
    public Optional<AsignaturaDTO> findById(@PathVariable Integer id){
        return asignaturaService.findById(id);
    }

    @GetMapping("/nombre/{nombre}")
    public Optional<AsignaturaDTO> findByNombre(@PathVariable String nombre){
        return asignaturaService.findByNombre(nombre);
    }

    @PostMapping
    public Optional<AsignaturaDTO> saveAsignatura(@RequestBody AsignaturaDTO asignatura){
        return asignaturaService.saveAsignatura(asignatura);
    }

    @PutMapping
    public Optional<AsignaturaDTO> updateAsignatura(@RequestBody AsignaturaDTO asignatura){
        return asignaturaService.updateAsignatura(AsignaturaDTO.builder().id(asignatura.getId()).build().getId(), asignatura);
    }

    @PutMapping("/status/{id}/{status}")
    public int changeStatus(@PathVariable Integer id, @PathVariable String status){
        return asignaturaService.changeStatus(id, status);
    }
}
