package co.edu.unicauca.competencias.proyectoweb.RAPrograma_module.RAPrograma_service.implement;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import co.edu.unicauca.competencias.proyectoweb.CompetenciasPrograma_module.CompetenciasPrograma_core.entities.CompetenciaPrograma;
import co.edu.unicauca.competencias.proyectoweb.CompetenciasPrograma_module.CompetenciasPrograma_core.repositories.ICompetenciasProgramaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import co.edu.unicauca.competencias.proyectoweb.RAPrograma_module.RAPrograma_core.entities.RAPrograma;
import co.edu.unicauca.competencias.proyectoweb.RAPrograma_module.RAPrograma_core.repositories.IRAProgramaRepository;
import co.edu.unicauca.competencias.proyectoweb.RAPrograma_module.RAPrograma_infraestructure.persistence.DTO.RAProgramaDTO;
import co.edu.unicauca.competencias.proyectoweb.RAPrograma_module.RAPrograma_service.interfaces.RAServiceInt;

@Service
public class RAServiceImpl implements RAServiceInt {
    private final IRAProgramaRepository raProgramaRepository;

    private final ICompetenciasProgramaRepository competenciaProgramaRepository;

    private final ModelMapper modelMapper;

    public RAServiceImpl(@Qualifier("raProgramaModelMapper") ModelMapper modelMapper, IRAProgramaRepository raProgramaRepository, ICompetenciasProgramaRepository competenciaProgramaRepository) {
        this.modelMapper = modelMapper;
        this.raProgramaRepository = raProgramaRepository;
        this.competenciaProgramaRepository = competenciaProgramaRepository;
    }

    @Override
    public List<RAProgramaDTO> findAll() {
        List<RAPrograma> raPrograma = raProgramaRepository.findAll();
        return raPrograma.stream()
                .map(programa -> {
                    RAProgramaDTO dto = new RAProgramaDTO();
                    dto.setId(programa.getId());
                    dto.setDescripcion(programa.getDescripcion());
                    dto.setEstado(programa.getEstado());
                    dto.setIdCompetenciaPrograma(
                            programa.getIdCompetenciaPrograma() != null
                                    ? programa.getIdCompetenciaPrograma().getId()
                                    : null
                    );
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public RAProgramaDTO findById(Integer id){
        Optional<RAPrograma> raProgramaList = raProgramaRepository.findById(id);
        if(raProgramaList.isPresent()){
            RAPrograma raPrograma = raProgramaList.get();
            RAProgramaDTO raProgramaDTO = new RAProgramaDTO();
            raProgramaDTO.setId(raPrograma.getId());
            raProgramaDTO.setDescripcion(raPrograma.getDescripcion());
            raProgramaDTO.setEstado(raPrograma.getEstado());
            raProgramaDTO.setIdCompetenciaPrograma(
                    raPrograma.getIdCompetenciaPrograma() != null
                            ? raPrograma.getIdCompetenciaPrograma().getId()
                            : null
            );
            return raProgramaDTO;
        }
        return null;
    }

    public void save(RAProgramaDTO raPrograma){
        RAPrograma raProgramaEntity = new RAPrograma();
        saveUpdate(raPrograma, raProgramaEntity);
    }

    public RAProgramaDTO update(RAProgramaDTO raProgramaDTO, Integer id) {
        if (!raProgramaRepository.existsById(id)) {
            return null;
        }

        RAPrograma raProgramaEntity = raProgramaRepository.findById(id).orElse(null);
        if (raProgramaEntity != null) {
            raProgramaEntity.setDescripcion(raProgramaDTO.getDescripcion());
            raProgramaEntity.setEstado(raProgramaDTO.getEstado());

            if (raProgramaDTO.getIdCompetenciaPrograma() != null) {
                CompetenciaPrograma competenciaPrograma = competenciaProgramaRepository.findById(raProgramaDTO.getIdCompetenciaPrograma())
                        .orElseThrow(() -> new EntityNotFoundException("Competencia Programa no encontrada"));
                raProgramaEntity.setIdCompetenciaPrograma(competenciaPrograma);
            }

            raProgramaEntity = raProgramaRepository.save(raProgramaEntity);

            RAProgramaDTO resultDTO = new RAProgramaDTO();
            resultDTO.setId(raProgramaEntity.getId());
            resultDTO.setDescripcion(raProgramaEntity.getDescripcion());
            resultDTO.setEstado(raProgramaEntity.getEstado());
            resultDTO.setIdCompetenciaPrograma(
                    raProgramaEntity.getIdCompetenciaPrograma() != null
                            ? raProgramaEntity.getIdCompetenciaPrograma().getId()
                            : null
            );

            return resultDTO;
        }
        return null;
    }

    private void saveUpdate(RAProgramaDTO raPrograma, RAPrograma raProgramaEntity) {
        raProgramaEntity.setDescripcion(raPrograma.getDescripcion());
        raProgramaEntity.setEstado(raPrograma.getEstado());

        if (raPrograma.getIdCompetenciaPrograma() != null) {
            CompetenciaPrograma competenciaPrograma = new CompetenciaPrograma();
            competenciaPrograma.setId(raPrograma.getIdCompetenciaPrograma());
            raProgramaEntity.setIdCompetenciaPrograma(competenciaPrograma);
        } else {
            raProgramaEntity.setIdCompetenciaPrograma(null);
        }

        raProgramaRepository.save(raProgramaEntity);
    }

    public boolean delete(Integer id){
        RAPrograma raPrograma = raProgramaRepository.findById(id).orElse(null);
        if(raPrograma != null){
            if (raPrograma.getEstado() == 1) {
                raPrograma.setEstado(0);
            } else if (raPrograma.getEstado() == 0) {
                raPrograma.setEstado(1);
            }
            raProgramaRepository.save(raPrograma);
            return true;
        }
        return false;   
    }
}
