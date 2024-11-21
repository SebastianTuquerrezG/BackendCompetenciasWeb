package co.edu.unicauca.competencias.proyectoweb.RAPrograma_module.RAPrograma_service.implement;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nimbusds.jose.shaded.gson.reflect.TypeToken;

import co.edu.unicauca.competencias.proyectoweb.RAPrograma_module.RAPrograma_core.entities.RAPrograma;
import co.edu.unicauca.competencias.proyectoweb.RAPrograma_module.RAPrograma_core.repositories.IRAProgramaRepository;
import co.edu.unicauca.competencias.proyectoweb.RAPrograma_module.RAPrograma_infraestructure.persistence.DTO.RAProgramaDTO;
import co.edu.unicauca.competencias.proyectoweb.RAPrograma_module.RAPrograma_service.interfaces.RAServiceInt;

@Service
public class RAServiceImpl implements RAServiceInt {

    @Autowired
    private IRAProgramaRepository raProgramaRepository;
    private ModelMapper modelMapper;

    public RAServiceImpl(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    /**
     * @Brief Metodo que retorna una lista con todos los resultados de aprendizaje de programa
     */
    @Override
    public List<RAProgramaDTO> findAll(){
        List<RAPrograma> raPrograma = raProgramaRepository.findAll();
        List<RAProgramaDTO> raProgramaDTO = this.modelMapper.map(raPrograma, new TypeToken<List<RAProgramaDTO>>(){}.getType());
        return raProgramaDTO;
    }

    /**
     * @Brief Metodo que retorna un resultado de aprendizaje de programa dado su id
     * @param id
     */
    public RAProgramaDTO findById(Integer id){
        RAPrograma raPrograma = raProgramaRepository.findById(id).orElse(null);
        if(raPrograma == null){
            return null;
        }
        RAProgramaDTO raProgramaDTO = this.modelMapper.map(raPrograma, RAProgramaDTO.class);
        return raProgramaDTO;
    }

    /**
     * @Brief Metodo que crea un resultado de aprendizaje de programa
     * @param raPrograma
     */
    public void save(RAProgramaDTO raPrograma){
        RAPrograma raProgramaEntity = this.modelMapper.map(raPrograma, RAPrograma.class);
        raProgramaRepository.save(raProgramaEntity);
    }

    /**
     * @Brief Metodo que actualiza un resultado de aprendizaje de programa
     * @param raPrograma
     * @param id
     */
    public RAProgramaDTO update(RAProgramaDTO raPrograma, Integer id){
        if(!raProgramaRepository.existsById(id)){
            return null;
        }
        RAPrograma raProgramaEntity = this.modelMapper.map(raPrograma, RAPrograma.class);
        raProgramaEntity.setId(id);
        raProgramaRepository.save(raProgramaEntity);
        return raPrograma;
    }

    /**
     * @Brief Metodo que elimina un resultado de aprendizaje de programa dado su id
     * @param id
     */
    public boolean delete(Integer id){
        if(raProgramaRepository.existsById(id)){
            raProgramaRepository.deleteById(id);
            return true;
        }
        return false;   
    }

}
