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
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ResultadosAprendizajeController {
    
    @Autowired
    private RAServiceInt service;

    @GetMapping("/ra-programa")
    public List<RAProgramaDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/ra-programa/{id}")
    public RAProgramaDTO findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping("/ra-programa")
    public void save(@RequestBody RAProgramaDTO raPrograma) {
        service.save(raPrograma);
    }

    @PutMapping("ra-programa/{id}")
    public RAProgramaDTO update(@RequestBody RAProgramaDTO raPrograma, @PathVariable Integer id) {
        return service.update(raPrograma, id);
    }

    @DeleteMapping("/ra-programa/{id}")
    public boolean delete(@PathVariable Integer id) {
        return service.delete(id);
    }
    
}
