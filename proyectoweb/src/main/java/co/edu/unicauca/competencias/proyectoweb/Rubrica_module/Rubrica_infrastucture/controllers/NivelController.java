package co.edu.unicauca.competencias.proyectoweb.Rubrica_module.Rubrica_infrastucture.controllers;

import co.edu.unicauca.competencias.proyectoweb.Rubrica_module.Rubrica_infrastucture.persistence.DTO.NivelDTO;
import co.edu.unicauca.competencias.proyectoweb.Rubrica_module.Rubrica_service.repository.INivelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/nivel")
public class NivelController {
    private final INivelService nivelService;

    public NivelController(INivelService nivelService) {
        this.nivelService = nivelService;
    }

    @GetMapping()
    public ResponseEntity<List<NivelDTO>> findAll() {
        return ResponseEntity.ok(nivelService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NivelDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(nivelService.findById(id));
    }

    @GetMapping("/criterio/{id}")
    public ResponseEntity<List<NivelDTO>> findByCriterioId(@PathVariable Integer id) {
        return ResponseEntity.ok(nivelService.findByCriterioId(id));
    }

    @PostMapping()
    public ResponseEntity<NivelDTO> save(@RequestBody NivelDTO nivel) {
        return ResponseEntity.ok(nivelService.save(nivel));
    }

    @PutMapping()
    public ResponseEntity<NivelDTO> update(@RequestBody NivelDTO nivel) {
        return ResponseEntity.ok(nivelService.update(nivel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Integer id) {
        return ResponseEntity.ok(nivelService.delete(id));
    }
}
