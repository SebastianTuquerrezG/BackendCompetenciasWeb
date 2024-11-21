package co.edu.unicauca.competencias.proyectoweb.Asignatura_module.Asignatura_service.implement;

import co.edu.unicauca.competencias.proyectoweb.Asignatura_module.Asignatura_core.entities.Asignatura;
import co.edu.unicauca.competencias.proyectoweb.Asignatura_module.Asignatura_core.repositories.IAsignaturaRepository;
import co.edu.unicauca.competencias.proyectoweb.Asignatura_module.Asignatura_service.interfaces.IAsignaturaService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AsignaturaServiceImpl implements IAsignaturaService {
    private final IAsignaturaRepository asignaturaRepository;

    @Autowired
    public AsignaturaServiceImpl(@Qualifier("ORMAsignaturaPersistence") IAsignaturaRepository asignaturaRepository) {
        this.asignaturaRepository = asignaturaRepository;
    }

    @Override
    public List<Asignatura> findAllAsignatura() {
        return asignaturaRepository.findAll();
    }

    @Override
    public List<Asignatura> findActives() {
        return asignaturaRepository.findAsignaturaByStatus(Asignatura.Status.ACTIVO);
    }

    @Override
    public Optional<Asignatura> findById(Integer id) {
        return Optional.of(asignaturaRepository.findById(id));
    }

    @Override
    public Optional<Asignatura> findByNombre(String nombre) {
        return asignaturaRepository.findByNombre(nombre).stream().findFirst();
    }

    @Override
    @Transactional
    public Optional<Asignatura> saveAsignatura(Asignatura asignatura) {
        return Optional.of(asignaturaRepository.save(asignatura));
    }

    @Override
    @Transactional
    public Optional<Asignatura> updateAsignatura(Asignatura asignatura) {
        return Optional.of(asignaturaRepository.update(asignatura));
    }

    @Override
    public int changeStatus(Integer id, Asignatura.Status status) {
        if (status == Asignatura.Status.ACTIVO) {
            return asignaturaRepository.activate(id) ? 1 : 0;
        } else {
            return asignaturaRepository.desactivate(id) ? 1 : 0;
        }
    }
}
