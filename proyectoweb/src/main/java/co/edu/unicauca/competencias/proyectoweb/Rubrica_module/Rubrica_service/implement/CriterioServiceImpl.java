package co.edu.unicauca.competencias.proyectoweb.Rubrica_module.Rubrica_service.implement;

import co.edu.unicauca.competencias.proyectoweb.Rubrica_module.Rubrica_core.entities.CriterioEvaluacion;
import co.edu.unicauca.competencias.proyectoweb.Rubrica_module.Rubrica_core.repository.ICriterioEvaluacionRepository;
import co.edu.unicauca.competencias.proyectoweb.Rubrica_module.Rubrica_core.repository.IRubricaRepository;
import co.edu.unicauca.competencias.proyectoweb.Rubrica_module.Rubrica_infrastucture.persistence.DTO.CriterioDTO;
import co.edu.unicauca.competencias.proyectoweb.Rubrica_module.Rubrica_service.repository.ICriterioService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CriterioServiceImpl implements ICriterioService {

    private final ICriterioEvaluacionRepository criterioRepository;

    private final IRubricaRepository rubricaRepository;

    public CriterioServiceImpl(ICriterioEvaluacionRepository criterioRepository, IRubricaRepository rubricaRepository) {
        this.criterioRepository = criterioRepository;
        this.rubricaRepository = rubricaRepository;
    }

    private List<CriterioDTO> getCriterioDTO(List<CriterioEvaluacion> criterioList) {
        List<CriterioDTO> criterioDTOList = new ArrayList<>();
        for (CriterioEvaluacion criterio : criterioList) {
            CriterioDTO criterioDTO = new CriterioDTO();
            criterioDTO.setId(criterio.getId());
            criterioDTO.setDescripcion(criterio.getDescripcion());
            criterioDTO.setPonderacion(criterio.getPonderacion());
            criterioDTO.setEstado(criterio.getEstado().name());
            criterioDTO.setRubricaId(criterio.getRubrica().getId());
            criterioDTOList.add(criterioDTO);
        }
        return criterioDTOList;
    }

    @Override
    public List<CriterioDTO> findAll() {
        List<CriterioEvaluacion> criterioDTOList = criterioRepository.findAll();
        return getCriterioDTO(criterioDTOList);
    }

    @Override
    public CriterioDTO findById(Integer id) {
        CriterioEvaluacion criterio = criterioRepository.findById(id).orElseThrow(() -> new RuntimeException("Criterio not found"));

        List<CriterioEvaluacion> criterioList = new ArrayList<>();
        criterioList.add(criterio);
        return getCriterioDTO(criterioList).get(0);
    }

    @Override
    public List<CriterioDTO> findByRubricaId(Integer id) {
        List<CriterioEvaluacion> criterioList = criterioRepository.findByRubricaId(id);
        return getCriterioDTO(criterioList);
    }

    @Override
    public CriterioDTO save(CriterioDTO criterio) {
        CriterioEvaluacion criterioEvaluacion = new CriterioEvaluacion();
        criterioEvaluacion.setDescripcion(criterio.getDescripcion());
        criterioEvaluacion.setPonderacion(criterio.getPonderacion());
        criterioEvaluacion.setRubrica(rubricaRepository.findById(criterio.getRubricaId()).orElseThrow(() -> new RuntimeException("Rubrica not found")));
        criterioEvaluacion.setEstado(CriterioEvaluacion.status.valueOf(criterio.getEstado()));
        criterioRepository.save(criterioEvaluacion);
        return getCriterioDTO(List.of(criterioEvaluacion)).get(0);
    }

    @Override
    public CriterioDTO update(CriterioDTO criterio) {
        boolean exists = criterioRepository.existsById(criterio.getId());
        if (exists) {
            CriterioEvaluacion criterioEvaluacion = new CriterioEvaluacion();
            criterioEvaluacion.setId(criterio.getId());
            criterioEvaluacion.setDescripcion(criterio.getDescripcion());
            criterioEvaluacion.setPonderacion(criterio.getPonderacion());
            criterioEvaluacion.setRubrica(rubricaRepository.findById(criterio.getRubricaId()).orElseThrow(() -> new RuntimeException("Rubrica not found")));
            criterioEvaluacion.setEstado(CriterioEvaluacion.status.valueOf(criterio.getEstado()));
            criterioRepository.save(criterioEvaluacion);
            return getCriterioDTO(List.of(criterioEvaluacion)).get(0);
        } else {
            return null;
        }
    }

    @Override
    public boolean delete(Integer id) {
        boolean exists = criterioRepository.existsById(id);
        if (exists) {
            CriterioEvaluacion criterioEvaluacion = criterioRepository.findById(id).orElseThrow(() -> new RuntimeException("Criterio not found"));
            if (criterioEvaluacion.getEstado() == CriterioEvaluacion.status.ACTIVO) {
                criterioEvaluacion.setEstado(CriterioEvaluacion.status.INACTIVO);
            } else if (criterioEvaluacion.getEstado() == CriterioEvaluacion.status.INACTIVO) {
                criterioEvaluacion.setEstado(CriterioEvaluacion.status.ACTIVO);
            }
            criterioRepository.save(criterioEvaluacion);
            return true;
        } else {
            return false;
        }
    }
}
