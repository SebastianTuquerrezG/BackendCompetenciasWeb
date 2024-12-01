package co.edu.unicauca.competencias.proyectoweb.RAPrograma_module.RAPrograma_service.implement;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import co.edu.unicauca.competencias.proyectoweb.CompetenciasPrograma_module.CompetenciasPrograma_core.entities.CompetenciaPrograma;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.nimbusds.jose.shaded.gson.reflect.TypeToken;

import co.edu.unicauca.competencias.proyectoweb.RAPrograma_module.RAPrograma_core.entities.RAPrograma;
import co.edu.unicauca.competencias.proyectoweb.RAPrograma_module.RAPrograma_core.repositories.IRAProgramaRepository;
import co.edu.unicauca.competencias.proyectoweb.RAPrograma_module.RAPrograma_infraestructure.persistence.DTO.RAProgramaDTO;
import co.edu.unicauca.competencias.proyectoweb.RAPrograma_module.RAPrograma_service.interfaces.RAServiceInt;

@Service
public class RAServiceImpl implements RAServiceInt {
    private final IRAProgramaRepository raProgramaRepository;

    private final ModelMapper modelMapper;

    public RAServiceImpl(@Qualifier("raProgramaModelMapper") ModelMapper modelMapper, IRAProgramaRepository raProgramaRepository){
        this.modelMapper = modelMapper;
        this.raProgramaRepository = raProgramaRepository;
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
        RAPrograma raPrograma = raProgramaRepository.findById(id).orElse(null);
        if(raPrograma == null){
            return null;
        }
        return this.modelMapper.map(raPrograma, RAProgramaDTO.class);
    }

    public void save(RAProgramaDTO raPrograma){
        RAPrograma raProgramaEntity = new RAPrograma();
        saveUpdate(raPrograma, raProgramaEntity);
    }

    public RAProgramaDTO update(RAProgramaDTO raPrograma, Integer id){
        if(!raProgramaRepository.existsById(id)){
            return null;
        }
        RAPrograma raProgramaEntity = this.modelMapper.map(raPrograma, RAPrograma.class);
        raProgramaEntity.setId(id);
        saveUpdate(raPrograma, raProgramaEntity);
        return raPrograma;
    }

    private void saveUpdate(RAProgramaDTO raPrograma, RAPrograma raProgramaEntity) {
        raProgramaEntity.setDescripcion(raPrograma.getDescripcion());
        raProgramaEntity.setEstado(raPrograma.getEstado());

        if (raPrograma.getIdCompetenciaPrograma() != null) {
            CompetenciaPrograma competenciaPrograma = new CompetenciaPrograma();
            competenciaPrograma.setId(raPrograma.getIdCompetenciaPrograma());
            raProgramaEntity.setIdCompetenciaPrograma(competenciaPrograma);
        }

        raProgramaRepository.save(raProgramaEntity);
    }

    public boolean delete(Integer id){
        if(raProgramaRepository.existsById(id)){
            raProgramaRepository.deleteById(id);
            return true;
        }
        return false;   
    }
}
