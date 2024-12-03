package co.edu.unicauca.competencias.proyectoweb.Asignaciongrande_module.Asignacion_infraestructure.controllers;

import co.edu.unicauca.competencias.proyectoweb.Asignaciongrande_module.Asignacion_infraestructure.persistence.DTO.AsociacionDTO;
import co.edu.unicauca.competencias.proyectoweb.Asignaciongrande_module.Asignacion_service.interfaces.IAsociacionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/asociacion")
public class AsociacionController {
    private final IAsociacionService asociacionService;

    public AsociacionController(IAsociacionService asociacionService) {
        this.asociacionService = asociacionService;
    }

    @GetMapping()
    public ResponseEntity<List<AsociacionDTO>> findAll() {
        return ResponseEntity.ok(asociacionService.findAll());
    }

    @GetMapping("/asignatura/{id_asignatura}")
    public ResponseEntity<List<AsociacionDTO>> findByIdAsignatura(@PathVariable Integer id_asignatura) {
        return ResponseEntity.ok(asociacionService.findByIdAsignatura(id_asignatura));
    }

    @GetMapping("/competencia/{id_competencia_asignatura}")
    public ResponseEntity<List<AsociacionDTO>> findByIdCompetenciaAsignatura(@PathVariable Integer id_competencia_asignatura) {
        return ResponseEntity.ok(asociacionService.findByIdCompetenciaAsignatura(id_competencia_asignatura));
    }

    @GetMapping("/teacher/{id_teacher}")
    public ResponseEntity<List<AsociacionDTO>> findByIdTeacher(@PathVariable Integer id_teacher) {
        return ResponseEntity.ok(asociacionService.findByIdTeacher(id_teacher));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AsociacionDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(asociacionService.findById(id));
    }

    @PostMapping()
    public ResponseEntity<AsociacionDTO> save(@RequestBody AsociacionDTO asociacionDTO) {
        return ResponseEntity.ok(asociacionService.save(asociacionDTO));
    }

    @PutMapping()
    public ResponseEntity<AsociacionDTO> update(@RequestBody AsociacionDTO asociacionDTO) {
        return ResponseEntity.ok(asociacionService.update(asociacionDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Integer id) {
        return ResponseEntity.ok(asociacionService.delete(id));
    }
}
