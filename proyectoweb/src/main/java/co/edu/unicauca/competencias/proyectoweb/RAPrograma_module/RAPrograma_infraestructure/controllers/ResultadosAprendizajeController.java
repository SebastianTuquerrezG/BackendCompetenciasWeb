package co.edu.unicauca.competencias.proyectoweb.RAPrograma_module.RAPrograma_infraestructure.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unicauca.competencias.proyectoweb.RAPrograma_module.RAPrograma_infraestructure.persistence.DTO.RAProgramaDTO;
import co.edu.unicauca.competencias.proyectoweb.RAPrograma_module.RAPrograma_service.interfaces.RAServiceInt;

@RestController
@RequestMapping("/api/ra-programa")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ResultadosAprendizajeController {
    private final RAServiceInt service;

    public ResultadosAprendizajeController(RAServiceInt service) {
        this.service = service;
    }

    @GetMapping()
    public List<RAProgramaDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public RAProgramaDTO findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping()
    public void save(@RequestBody RAProgramaDTO raPrograma) {
        service.save(raPrograma);
    }

    @PutMapping("/{id}")
    public RAProgramaDTO update(@RequestBody RAProgramaDTO raPrograma, @PathVariable Integer id) {
        return service.update(raPrograma, id);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id) {
        return service.delete(id);
    }
    
}
