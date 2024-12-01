package co.edu.unicauca.competencias.proyectoweb.RAAsignatura_module.RAAsignatura_infraestructure.controllers;

import co.edu.unicauca.competencias.proyectoweb.RAAsignatura_module.RAAsignatura_infraestructure.persistence.DTO.RAAsignaturaDTO;
import co.edu.unicauca.competencias.proyectoweb.RAAsignatura_module.RAAsignatura_service.interfaces.RAAServiceInt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ra-asignatura")
@CrossOrigin(origins = "*", maxAge = 3600)
public class RAAsignaturaController {
    private final RAAServiceInt service;

    public RAAsignaturaController(RAAServiceInt service) {
        this.service = service;
    }

    @GetMapping()
    public List<RAAsignaturaDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public RAAsignaturaDTO findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping()
    public void save(@RequestBody RAAsignaturaDTO raPrograma) {
        service.save(raPrograma);
    }

    @PutMapping("/{id}")
    public RAAsignaturaDTO update(@RequestBody RAAsignaturaDTO raPrograma, @PathVariable Integer id) {
        return service.update(raPrograma, id);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id) {
        return service.delete(id);
    }
    
}
