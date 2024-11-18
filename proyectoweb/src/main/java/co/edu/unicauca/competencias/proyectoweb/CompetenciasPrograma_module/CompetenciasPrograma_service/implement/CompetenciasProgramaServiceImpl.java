package co.edu.unicauca.competencias.proyectoweb.CompetenciasPrograma_module.CompetenciasPrograma_service.implement;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unicauca.competencias.proyectoweb.CompetenciasPrograma_module.CompetenciasPrograma_core.entities.CompetenciaPrograma;
import co.edu.unicauca.competencias.proyectoweb.CompetenciasPrograma_module.CompetenciasPrograma_core.repositories.ICompetenciasProgramaRepository;
import co.edu.unicauca.competencias.proyectoweb.CompetenciasPrograma_module.CompetenciasPrograma_infraestructure.persistence.DTO.CompetenciaProgramaDTO;
import co.edu.unicauca.competencias.proyectoweb.CompetenciasPrograma_module.CompetenciasPrograma_service.interfaces.CompetenciasProgramaServiceInt;

@Service
public class CompetenciasProgramaServiceImpl implements CompetenciasProgramaServiceInt{

    @Autowired
    private ICompetenciasProgramaRepository competenciasProgramaRepository;
    private ModelMapper modelMapper;

    public CompetenciasProgramaServiceImpl(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    /**
     * @Brief Metodo que retorna una lista con todas las competencias de programa
     */
    @Override
    public List<CompetenciaProgramaDTO> findAll(){
        List<CompetenciaPrograma> competenciasPrograma = competenciasProgramaRepository.findAll();
        List<CompetenciaProgramaDTO> competenciasProgramaDTO = this.modelMapper.map(competenciasPrograma, new TypeToken<List<CompetenciaProgramaDTO>>(){}.getType());
        return competenciasProgramaDTO;
    }

    /**
     * @Brief Metodo que retorna una competencia de programa dado su id
     * @param id
     */
    @Override
    public CompetenciaProgramaDTO findById(Integer id){
        CompetenciaPrograma competenciaPrograma = competenciasProgramaRepository.findById(id).orElse(null);
        if(competenciaPrograma == null){
            return null;
        }
        CompetenciaProgramaDTO competenciaProgramaDTO = this.modelMapper.map(competenciaPrograma, CompetenciaProgramaDTO.class);
        return competenciaProgramaDTO;
    }

    /**
     * @Brief Metodo que crea una competencia de programa
     * @param competenciaPrograma
     */
    @Override
    public void save(CompetenciaProgramaDTO competenciaPrograma){
        CompetenciaPrograma competenciaProgramaEntity = this.modelMapper.map(competenciaPrograma, CompetenciaPrograma.class);
        competenciasProgramaRepository.save(competenciaProgramaEntity);
    }

    /**
     * @Brief Metodo que actualiza una competencia de programa
     * @param competenciaPrograma
     * @param id
     */
    @Override
    public CompetenciaProgramaDTO update(CompetenciaProgramaDTO competenciaPrograma, Integer id){
        CompetenciaPrograma competenciaProgramaEntity = this.modelMapper.map(competenciaPrograma, CompetenciaPrograma.class);
        competenciaProgramaEntity.setId(id);
        competenciasProgramaRepository.save(competenciaProgramaEntity);
        return competenciaPrograma;
    }

    /**
     * @Brief Metodo que elimina una competencia de programa dado su id
     * @param id
     */
    @Override
    public boolean delete(Integer id){
        if(competenciasProgramaRepository.existsById(id)){
            competenciasProgramaRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
}
