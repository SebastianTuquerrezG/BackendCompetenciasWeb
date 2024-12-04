package co.edu.unicauca.competencias.proyectoweb.Rubrica_module.Rubrica_infrastucture.controllers;

import co.edu.unicauca.competencias.proyectoweb.Rubrica_module.Rubrica_infrastucture.persistence.DTO.RubricaDTO;
import co.edu.unicauca.competencias.proyectoweb.Rubrica_module.Rubrica_service.repository.IRubricaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/rubrica")
public class RubricaController {
    private final IRubricaService rubricaService;

    public RubricaController(IRubricaService rubricaService) {
        this.rubricaService = rubricaService;
    }

    @GetMapping()
    public ResponseEntity<List<RubricaDTO>> findAll() {
        return ResponseEntity.ok(rubricaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RubricaDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(rubricaService.findById(id));
    }

    @GetMapping("/raAsignatura/{id}")
    public ResponseEntity<List<RubricaDTO>> findByRaAsignaturaId(@PathVariable Integer id) {
        return ResponseEntity.ok(rubricaService.findByRaAsignaturaId(id));
    }

    @PostMapping()
    public ResponseEntity<RubricaDTO> save(@RequestBody RubricaDTO rubricaDTO) {
        return ResponseEntity.ok(rubricaService.save(rubricaDTO));
    }

    @PutMapping()
    public ResponseEntity<RubricaDTO> update(@RequestBody RubricaDTO rubricaDTO) {
        return ResponseEntity.ok(rubricaService.update(rubricaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Integer id) {
        return ResponseEntity.ok(rubricaService.delete(id));
    }
}
