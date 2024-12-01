package co.edu.unicauca.competencias.proyectoweb.CAsignatura_module.CA_infrastructure.controllers;

import co.edu.unicauca.competencias.proyectoweb.CAsignatura_module.CA_infrastructure.persistence.DTO.CompetenciaAsignaturaDTO;
import co.edu.unicauca.competencias.proyectoweb.CAsignatura_module.CA_service.interfaces.ICompetenciasAsignaturaServiceInt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/competenciasasignatura")
public class CompetenciaAsignaturaController {
    private final ICompetenciasAsignaturaServiceInt competenciasAsignaturaService;

    public CompetenciaAsignaturaController(ICompetenciasAsignaturaServiceInt competenciasAsignaturaService) {
        this.competenciasAsignaturaService = competenciasAsignaturaService;
    }

    @GetMapping
    public List<CompetenciaAsignaturaDTO> findAllCA(){
        return competenciasAsignaturaService.findAll();
    }

    @GetMapping("/activos")
    public List<CompetenciaAsignaturaDTO> findActives(){
        return competenciasAsignaturaService.findActives("ACTIVO");
    }

    @GetMapping("/{id}")
    public Optional<CompetenciaAsignaturaDTO> findById(@PathVariable Integer id){
        return Optional.ofNullable(competenciasAsignaturaService.findById(id));
    }

    @PostMapping
    public Optional<CompetenciaAsignaturaDTO> saveAsignatura(@RequestBody CompetenciaAsignaturaDTO asignatura){
        return Optional.ofNullable(competenciasAsignaturaService.save(asignatura));
    }

    @PutMapping
    public Optional<CompetenciaAsignaturaDTO> updateAsignatura(@RequestBody CompetenciaAsignaturaDTO asignatura){
        return Optional.ofNullable(competenciasAsignaturaService.update(asignatura.getId(), asignatura));
    }

    @PutMapping("/status/{id}/{status}")
    public int changeStatus(@PathVariable Integer id, @PathVariable String status){
        return competenciasAsignaturaService.updateByStatus(id, status);
    }
}
