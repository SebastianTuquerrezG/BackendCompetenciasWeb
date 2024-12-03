package co.edu.unicauca.competencias.proyectoweb.Rubrica_module.Rubrica_infrastucture.controllers;

import co.edu.unicauca.competencias.proyectoweb.Rubrica_module.Rubrica_infrastucture.persistence.DTO.CriterioDTO;
import co.edu.unicauca.competencias.proyectoweb.Rubrica_module.Rubrica_service.repository.ICriterioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/criterio")
public class CriterioController {
    private final ICriterioService criterioEvaluacionService;

    public CriterioController(ICriterioService criterioEvaluacionService) {
        this.criterioEvaluacionService = criterioEvaluacionService;
    }

    @GetMapping()
    public ResponseEntity<List<CriterioDTO>> findAll() {
        return ResponseEntity.ok(criterioEvaluacionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CriterioDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(criterioEvaluacionService.findById(id));
    }

    @GetMapping("/rubrica/{id}")
    public ResponseEntity<List<CriterioDTO>> findByRubricaId(@PathVariable Integer id) {
        return ResponseEntity.ok(criterioEvaluacionService.findByRubricaId(id));
    }

    @PostMapping()
    public ResponseEntity<CriterioDTO> save(@RequestBody CriterioDTO criterio) {
        return ResponseEntity.ok(criterioEvaluacionService.save(criterio));
    }

    @PutMapping()
    public ResponseEntity<CriterioDTO> update(@RequestBody CriterioDTO criterio) {
        return ResponseEntity.ok(criterioEvaluacionService.update(criterio));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Integer id) {
        return ResponseEntity.ok(criterioEvaluacionService.delete(id));
    }
}
