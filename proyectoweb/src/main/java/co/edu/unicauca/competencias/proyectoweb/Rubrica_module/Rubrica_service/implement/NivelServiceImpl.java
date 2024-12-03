package co.edu.unicauca.competencias.proyectoweb.Rubrica_module.Rubrica_service.implement;

import co.edu.unicauca.competencias.proyectoweb.Rubrica_module.Rubrica_core.entities.CriterioEvaluacion;
import co.edu.unicauca.competencias.proyectoweb.Rubrica_module.Rubrica_core.entities.NivelDesempenio;
import co.edu.unicauca.competencias.proyectoweb.Rubrica_module.Rubrica_core.repository.ICriterioEvaluacionRepository;
import co.edu.unicauca.competencias.proyectoweb.Rubrica_module.Rubrica_core.repository.INivelDesempenioRepository;
import co.edu.unicauca.competencias.proyectoweb.Rubrica_module.Rubrica_infrastucture.persistence.DTO.CriterioDTO;
import co.edu.unicauca.competencias.proyectoweb.Rubrica_module.Rubrica_infrastucture.persistence.DTO.NivelDTO;
import co.edu.unicauca.competencias.proyectoweb.Rubrica_module.Rubrica_service.repository.INivelService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NivelServiceImpl implements INivelService {

    private final INivelDesempenioRepository nivelDesempenioRepository;

    private final ICriterioEvaluacionRepository criterioEvaluacionRepository;

    public NivelServiceImpl(INivelDesempenioRepository nivelDesempenioRepository, ICriterioEvaluacionRepository criterioEvaluacionRepository) {
        this.nivelDesempenioRepository = nivelDesempenioRepository;
        this.criterioEvaluacionRepository = criterioEvaluacionRepository;
    }

    private List<NivelDTO> getNivelDTO(List<NivelDesempenio> nivelDesempenioList) {
        List<NivelDTO> nivelDTOList = new ArrayList<>();
        for (NivelDesempenio nivelDesempenio : nivelDesempenioList) {
            NivelDTO nivelDTO = new NivelDTO();
            nivelDTO.setId(nivelDesempenio.getId());
            nivelDTO.setNombre(String.valueOf(nivelDesempenio.getNombre()));
            nivelDTO.setDescripcion(nivelDesempenio.getDescripcion());
            nivelDTO.setCriterioEvaluacionId(nivelDesempenio.getCriterioEvaluacion().getId());
            String[] rangoNota = nivelDesempenio.getRangoNota().split("-");
            nivelDTO.setRangoMin(Float.parseFloat(rangoNota[0]));
            nivelDTO.setRangoMax(Float.parseFloat(rangoNota[1]));
            nivelDTOList.add(nivelDTO);
        }
        return nivelDTOList;
    }

    @Override
    public List<NivelDTO> findAll() {
        List<NivelDesempenio> nivelDesempenioList = nivelDesempenioRepository.findAll();
        return getNivelDTO(nivelDesempenioList);
    }

    @Override
    public NivelDTO findById(Integer id) {
        NivelDesempenio nivelDesempenio = nivelDesempenioRepository.findById(id).orElseThrow(() -> new RuntimeException("Nivel not found"));

        List<NivelDesempenio> nivelDesempenioList = new ArrayList<>();
        nivelDesempenioList.add(nivelDesempenio);
        return getNivelDTO(nivelDesempenioList).get(0);
    }

    @Override
    public List<NivelDTO> findByCriterioId(Integer criterioDTO) {
        List<NivelDesempenio> nivelDesempenioList = nivelDesempenioRepository.findByCriterioEvaluacionId(criterioDTO);
        if (nivelDesempenioList.isEmpty()) {
            return null;
        }
        return getNivelDTO(nivelDesempenioList);
    }

    @Override
    public NivelDTO save(NivelDTO nivel) {
        boolean exists = criterioEvaluacionRepository.existsById(nivel.getCriterioEvaluacionId());
        if (exists) {
            NivelDesempenio nivelDesempenio = NivelDesempenio.builder()
                    .nombre(NivelDesempenio.categoria.valueOf(nivel.getNombre()))
                    .descripcion(nivel.getDescripcion())
                    .rangoNota(nivel.getRangoMin() + "-" + nivel.getRangoMax())
                    .criterioEvaluacion(criterioEvaluacionRepository.findById(nivel.getCriterioEvaluacionId()).orElseThrow(() -> new RuntimeException("CriterioEvaluacion not found")))
                    .build();
            nivelDesempenioRepository.save(nivelDesempenio);
            return getNivelDTO(List.of(nivelDesempenio)).get(0);
        }
        return null;
    }

    @Override
    public NivelDTO update(NivelDTO nivel) {
        boolean exists = nivelDesempenioRepository.existsById(nivel.getId());
        if (exists) {
            NivelDesempenio nivelDesempenio = NivelDesempenio.builder()
                    .id(nivel.getId())
                    .nombre(NivelDesempenio.categoria.valueOf(nivel.getNombre()))
                    .descripcion(nivel.getDescripcion())
                    .rangoNota(nivel.getRangoMin() + "-" + nivel.getRangoMax())
                    .criterioEvaluacion(criterioEvaluacionRepository.findById(nivel.getCriterioEvaluacionId()).orElseThrow(() -> new RuntimeException("CriterioEvaluacion not found")))
                    .build();
            nivelDesempenioRepository.save(nivelDesempenio);
            return getNivelDTO(List.of(nivelDesempenio)).get(0);
        }
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        boolean exists = nivelDesempenioRepository.existsById(id);
        if (exists) {
            nivelDesempenioRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
