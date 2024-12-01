package co.edu.unicauca.competencias.proyectoweb.CompetenciasPrograma_module.CompetenciasPrograma_infraestructure.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unicauca.competencias.proyectoweb.CompetenciasPrograma_module.CompetenciasPrograma_infraestructure.persistence.DTO.CompetenciaProgramaDTO;
import co.edu.unicauca.competencias.proyectoweb.CompetenciasPrograma_module.CompetenciasPrograma_service.interfaces.CompetenciasProgramaServiceInt;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/competencias-programa")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CompetenciasProgramaController {
    
    @Autowired
    private CompetenciasProgramaServiceInt service;

    @GetMapping()
    public List<CompetenciaProgramaDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public CompetenciaProgramaDTO findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping()
    public void save(@RequestBody CompetenciaProgramaDTO competenciaPrograma) {
        service.save(competenciaPrograma);
    }

    @PutMapping("/{id}")
    public CompetenciaProgramaDTO update(@RequestBody CompetenciaProgramaDTO competenciaPrograma, @PathVariable Integer id) {
        return service.update(competenciaPrograma, id);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id) {
        return service.delete(id);
    }
}
