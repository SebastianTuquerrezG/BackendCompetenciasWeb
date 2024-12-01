package co.edu.unicauca.competencias.proyectoweb.Asignatura_module.Asignatura_service.implement;

import co.edu.unicauca.competencias.proyectoweb.Asignatura_module.Asignatura_core.entities.Asignatura;
import co.edu.unicauca.competencias.proyectoweb.Asignatura_module.Asignatura_core.repositories.IAsignaturaRepository;
import co.edu.unicauca.competencias.proyectoweb.Asignatura_module.Asignatura_infraestructure.persistence.DTO.AsignaturaDTO;
import co.edu.unicauca.competencias.proyectoweb.Asignatura_module.Asignatura_service.interfaces.IAsignaturaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AsignaturaServiceImpl implements IAsignaturaService {
    private final IAsignaturaRepository asignaturaRepository;

    private final ModelMapper modelMapper;

    public AsignaturaServiceImpl(IAsignaturaRepository asignaturaRepository,
                                 @Qualifier("asignaturaModelMapper") ModelMapper modelMapper) {
        this.asignaturaRepository = asignaturaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<AsignaturaDTO> findAllAsignatura() {
        List<Asignatura> asignaturaList = asignaturaRepository.findAll();

        return asignaturaList.stream().map(asignatura -> this.modelMapper.map(asignatura, AsignaturaDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<AsignaturaDTO> findActives() {
        List<Asignatura> asignaturaList = asignaturaRepository.findAll();
        List<Asignatura> activeAsignaturas = new ArrayList<>();
        for (Asignatura asignatura : asignaturaList) {
            if (asignatura.getStatus() == Asignatura.Status.ACTIVO) {
                activeAsignaturas.add(asignatura);
            }
        }

        return activeAsignaturas.stream().map(asignatura -> this.modelMapper.map(asignatura, AsignaturaDTO.class)).collect(Collectors.toList());
    }

    @Override
    public Optional<AsignaturaDTO> findById(Integer id) {
        Asignatura asignatura = asignaturaRepository.findById(id).orElse(null);
        if (asignatura == null) {
            return Optional.empty();
        }

        AsignaturaDTO asignaturaDTO = this.modelMapper.map(asignatura, AsignaturaDTO.class);
        return Optional.of(asignaturaDTO);
    }

    @Override
    public Optional<AsignaturaDTO> findByNombre(String nombre) {
        Asignatura asignatura = asignaturaRepository.findByNombre(nombre);
        if (asignatura == null) {
            return Optional.empty();
        }

        AsignaturaDTO asignaturaDTO = this.modelMapper.map(asignatura, AsignaturaDTO.class);
        return Optional.of(asignaturaDTO);
    }

    @Override
    public Optional<AsignaturaDTO> saveAsignatura(AsignaturaDTO asignatura) {
        boolean exists = asignaturaRepository.existsByNombre(asignatura.getNombre());
        if (exists) {
            return Optional.empty();
        }
        Asignatura asignaturaEntity = this.modelMapper.map(asignatura, Asignatura.class);
        asignaturaEntity = asignaturaRepository.save(asignaturaEntity);
        asignatura.setId(asignaturaEntity.getId());
        return Optional.of(asignatura);
    }

    @Override
    public Optional<AsignaturaDTO> updateAsignatura(Integer id, AsignaturaDTO asignatura) {
        boolean exists = asignaturaRepository.existsById(id);
        if (!exists) {
            return Optional.empty();
        }
        Asignatura asignaturaEntity = this.modelMapper.map(asignatura, Asignatura.class);
        asignaturaEntity.setId(id);
        asignaturaEntity = asignaturaRepository.save(asignaturaEntity);
        asignatura.setId(asignaturaEntity.getId());
        return Optional.of(asignatura);
    }

    @Override
    public int changeStatus(Integer id, String status) {
        Asignatura asignatura = asignaturaRepository.findById(id).orElse(null);
        if (asignatura == null) {
            return 0;
        }
        asignatura.setStatus(Asignatura.Status.valueOf(status));
        asignaturaRepository.save(asignatura);
        return 1;
    }
}
