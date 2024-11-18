package co.edu.unicauca.competencias.proyectoweb.RAPrograma_module.RAPrograma_core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unicauca.competencias.proyectoweb.RAPrograma_module.RAPrograma_core.entities.RAPrograma;

public interface IRAProgramaRepository extends JpaRepository<RAPrograma, Integer> {
}