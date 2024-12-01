package co.edu.unicauca.competencias.proyectoweb.RAAsignatura_module.RAAsignatura_service.implement;

import co.edu.unicauca.competencias.proyectoweb.CAsignatura_module.CA_core.entities.CompetenciaAsignatura;
import co.edu.unicauca.competencias.proyectoweb.RAAsignatura_module.RAAsignatura_core.entities.RAAsignatura;
import co.edu.unicauca.competencias.proyectoweb.RAAsignatura_module.RAAsignatura_core.repositories.IRAAsignaturaRepository;
import co.edu.unicauca.competencias.proyectoweb.RAAsignatura_module.RAAsignatura_infraestructure.persistence.DTO.RAAsignaturaDTO;
import co.edu.unicauca.competencias.proyectoweb.RAAsignatura_module.RAAsignatura_service.interfaces.RAAServiceInt;
import com.nimbusds.jose.shaded.gson.reflect.TypeToken;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RAAServiceImpl implements RAAServiceInt {
    private final IRAAsignaturaRepository raProgramaRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public RAAServiceImpl(@Qualifier("raAsignaturaModelMapper") ModelMapper modelMapper, IRAAsignaturaRepository raProgramaRepository){
        this.modelMapper = modelMapper;
        this.raProgramaRepository = raProgramaRepository;
    }

    @Override
    public List<RAAsignaturaDTO> findAll(){
        List<RAAsignatura> raAsignatura = raProgramaRepository.findAll();
        return this.modelMapper.map(raAsignatura, new TypeToken<List<RAAsignaturaDTO>>(){}.getType());
    }

    public RAAsignaturaDTO findById(Integer id){
        RAAsignatura raAsignatura = raProgramaRepository.findById(id).orElse(null);
        if(raAsignatura == null){
            return null;
        }
        return this.modelMapper.map(raAsignatura, RAAsignaturaDTO.class);
    }

    public void save(RAAsignaturaDTO raPrograma){
        RAAsignatura raAsignaturaEntity = new RAAsignatura();
        saveUpdate(raPrograma, raAsignaturaEntity);
    }

    public RAAsignaturaDTO update(RAAsignaturaDTO raPrograma, Integer id){
        if(!raProgramaRepository.existsById(id)){
            return null;
        }
        RAAsignatura raAsignaturaEntity = this.modelMapper.map(raPrograma, RAAsignatura.class);
        raAsignaturaEntity.setId(id);
        saveUpdate(raPrograma, raAsignaturaEntity);
        return raPrograma;
    }

    private void saveUpdate(RAAsignaturaDTO raPrograma, RAAsignatura raAsignaturaEntity) {
        raAsignaturaEntity.setDescripcion(raPrograma.getDescripcion());
        raAsignaturaEntity.setEstado(CompetenciaAsignatura.Status.valueOf(raPrograma.getEstado()));

        if (raPrograma.getIdCompetenciaAsignatura() != null) {
            CompetenciaAsignatura competenciaPrograma = new CompetenciaAsignatura();
            competenciaPrograma.setIdCompetenciaAsignatura(raPrograma.getIdCompetenciaAsignatura());
            raAsignaturaEntity.setCompetenciaAsignatura(competenciaPrograma);
        }

        raProgramaRepository.save(raAsignaturaEntity);
    }

    public boolean delete(Integer id){
        if(raProgramaRepository.existsById(id)){
            raProgramaRepository.deleteById(id);
            return true;
        }
        return false;   
    }
}
