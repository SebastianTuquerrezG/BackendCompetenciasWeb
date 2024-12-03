package co.edu.unicauca.competencias.proyectoweb.Rubrica_module.Rubrica_service.implement;

import co.edu.unicauca.competencias.proyectoweb.RAAsignatura_module.RAAsignatura_core.entities.RAAsignatura;
import co.edu.unicauca.competencias.proyectoweb.RAAsignatura_module.RAAsignatura_core.repositories.IRAAsignaturaRepository;
import co.edu.unicauca.competencias.proyectoweb.Rubrica_module.Rubrica_core.entities.Rubrica;
import co.edu.unicauca.competencias.proyectoweb.Rubrica_module.Rubrica_core.repository.IRubricaRepository;
import co.edu.unicauca.competencias.proyectoweb.Rubrica_module.Rubrica_infrastucture.persistence.DTO.RubricaDTO;
import co.edu.unicauca.competencias.proyectoweb.Rubrica_module.Rubrica_service.repository.IRubricaService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RubricaServiceImpl implements IRubricaService {

    private final IRubricaRepository rubricaRepository;

    private final IRAAsignaturaRepository raAsignaturaRepository;

    public RubricaServiceImpl(IRubricaRepository rubricaRepository, IRAAsignaturaRepository raAsignaturaRepository) {
        this.rubricaRepository = rubricaRepository;
        this.raAsignaturaRepository = raAsignaturaRepository;
    }

    private List<RubricaDTO> getRubricaDTO(List<Rubrica> rubricaList) {
        List<RubricaDTO> rubricaDTOList = new ArrayList<>();
        for (Rubrica rubrica : rubricaList) {
            RubricaDTO rubricaDTO = new RubricaDTO();
            rubricaDTO.setId(rubrica.getId());
            rubricaDTO.setNombre(rubrica.getNombre());
            rubricaDTO.setRaAsignaturaId(rubrica.getRaAsignatura().getId());
            rubricaDTO.setCreateAt(rubrica.getCreateAt());
            rubricaDTO.setUpdateAt(rubrica.getUpdateAt());
            rubricaDTO.setEstado(rubrica.getEstado().name());
            rubricaDTOList.add(rubricaDTO);
        }
        return rubricaDTOList;
    }

    @Override
    public List<RubricaDTO> findAll() {
        List<Rubrica> rubricaDTOList = rubricaRepository.findAll();
        return getRubricaDTO(rubricaDTOList);
    }

    @Override
    public RubricaDTO findById(Integer id) {
        Rubrica rubrica = rubricaRepository.findById(id).orElseThrow(() -> new RuntimeException("Rubrica not found"));

        List<Rubrica> rubricaList = new ArrayList<>();
        rubricaList.add(rubrica);
        return getRubricaDTO(rubricaList).get(0);
    }

    @Override
    public List<RubricaDTO> findByRaAsignaturaId(Integer raAsignaturaId) {
        RAAsignatura raAsignatura = raAsignaturaRepository.findById(raAsignaturaId).orElseThrow(() -> new RuntimeException("RAAsignatura not found"));
        List<Rubrica> rubricaList = rubricaRepository.findRubricaByRaAsignaturaId(raAsignatura.getId());
        return getRubricaDTO(rubricaList);
    }

    @Override
    public RubricaDTO save(RubricaDTO rubricaDTO) {
        boolean exists = raAsignaturaRepository.existsById(rubricaDTO.getRaAsignaturaId());
        if (exists) {
            Rubrica rubrica = new Rubrica();
            rubrica.setNombre(rubricaDTO.getNombre());
            rubrica.setRaAsignatura(raAsignaturaRepository.findById(rubricaDTO.getRaAsignaturaId()).orElseThrow(() -> new RuntimeException("RAAsignatura not found")));
            rubrica.setEstado(rubricaDTO.getEstado() != null ? Rubrica.status.valueOf(rubricaDTO.getEstado()) : Rubrica.status.valueOf("ACTIVO"));
            return getRubricaDTO(List.of(rubricaRepository.save(rubrica))).get(0);
        } else {
            return null;
        }
    }

    @Override
    public RubricaDTO update(RubricaDTO rubricaDTO) {
        boolean exists = rubricaRepository.existsById(rubricaDTO.getId());
        if (exists) {
            Rubrica rubrica = new Rubrica();
            rubrica.setId(rubricaDTO.getId());
            rubrica.setNombre(rubricaDTO.getNombre());
            rubrica.setRaAsignatura(raAsignaturaRepository.findById(rubricaDTO.getRaAsignaturaId()).orElseThrow(() -> new RuntimeException("RAAsignatura not found")));
            rubrica.setEstado(rubricaDTO.getEstado() != null ? Rubrica.status.valueOf(rubricaDTO.getEstado()) : Rubrica.status.valueOf("ACTIVO"));
            rubrica.setCreateAt(rubricaDTO.getCreateAt());
            rubrica.setUpdateAt(new Date());
            return getRubricaDTO(List.of(rubricaRepository.save(rubrica))).get(0);
        } else {
            return null;
        }
    }

    @Override
    public boolean delete(Integer id) {
        boolean exists = rubricaRepository.existsById(id);
        if (exists) {
            Rubrica rubrica = rubricaRepository.findById(id).orElseThrow(() -> new RuntimeException("Rubrica not found"));
            if (rubrica.getEstado() == Rubrica.status.ACTIVO || rubrica.getEstado() == null) {
                rubrica.setEstado(Rubrica.status.valueOf("ACTIVO"));
            } else if (rubrica.getEstado() == Rubrica.status.INACTIVO) {
                rubrica.setEstado(Rubrica.status.valueOf("ACTIVO"));
            }
            rubrica.setEstado(Rubrica.status.valueOf("INACTIVO"));
            rubricaRepository.save(rubrica);
            return true;
        } else {
            return false;
        }
    }
}
