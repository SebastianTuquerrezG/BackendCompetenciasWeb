package co.edu.unicauca.competencias.proyectoweb.RAAsignatura_module.RAAsignatura_service.implement;

import co.edu.unicauca.competencias.proyectoweb.CAsignatura_module.CA_core.entities.CompetenciaAsignatura;
import co.edu.unicauca.competencias.proyectoweb.CAsignatura_module.CA_core.repositories.ICompetenciaAsignaturaRepository;
import co.edu.unicauca.competencias.proyectoweb.RAAsignatura_module.RAAsignatura_core.entities.RAAsignatura;
import co.edu.unicauca.competencias.proyectoweb.RAAsignatura_module.RAAsignatura_core.repositories.IRAAsignaturaRepository;
import co.edu.unicauca.competencias.proyectoweb.RAAsignatura_module.RAAsignatura_infraestructure.persistence.DTO.RAAsignaturaDTO;
import co.edu.unicauca.competencias.proyectoweb.RAAsignatura_module.RAAsignatura_service.interfaces.RAAServiceInt;
import com.nimbusds.jose.shaded.gson.reflect.TypeToken;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RAAServiceImpl implements RAAServiceInt {
    private final IRAAsignaturaRepository raProgramaRepository;

    private final ModelMapper modelMapper;

    private final ICompetenciaAsignaturaRepository competenciaAsignaturaRepository;

    @Autowired
    public RAAServiceImpl(@Qualifier("raAsignaturaModelMapper") ModelMapper modelMapper, IRAAsignaturaRepository raProgramaRepository, ICompetenciaAsignaturaRepository competenciaAsignaturaRepository) {
        this.modelMapper = modelMapper;
        this.raProgramaRepository = raProgramaRepository;
        this.competenciaAsignaturaRepository = competenciaAsignaturaRepository;
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

    public RAAsignaturaDTO save(RAAsignaturaDTO raPrograma){
        RAAsignatura raAsignaturaEntity = new RAAsignatura();
        return saveUpdate(raPrograma, raAsignaturaEntity);
    }

    public RAAsignaturaDTO update(RAAsignaturaDTO raPrograma, Integer id){
        if(!raProgramaRepository.existsById(id)){
            return null;
        }

        RAAsignatura raAsignaturaEntity = raProgramaRepository.findById(id).orElse(null);
        if (raAsignaturaEntity != null) {
            raAsignaturaEntity.setDescripcion(raPrograma.getDescripcion());
            raAsignaturaEntity.setEstado(CompetenciaAsignatura.Status.valueOf(raPrograma.getEstado()));

            if (raPrograma.getIdCompetenciaAsignatura() != null) {
                CompetenciaAsignatura competenciaPrograma = competenciaAsignaturaRepository.findById(raPrograma.getIdCompetenciaAsignatura())
                        .orElseThrow(() -> new EntityNotFoundException("Competencia Asignatura no encontrada"));
                raAsignaturaEntity.setCompetenciaAsignatura(competenciaPrograma);
            }

            raAsignaturaEntity = raProgramaRepository.save(raAsignaturaEntity);

            RAAsignaturaDTO resultDTO = new RAAsignaturaDTO();
            resultDTO.setId(raAsignaturaEntity.getId());
            resultDTO.setDescripcion(raAsignaturaEntity.getDescripcion());
            resultDTO.setEstado(String.valueOf(raAsignaturaEntity.getEstado()));
            resultDTO.setIdCompetenciaAsignatura(
                    raAsignaturaEntity.getCompetenciaAsignatura() != null
                            ? raAsignaturaEntity.getCompetenciaAsignatura().getIdCompetenciaAsignatura()
                            : null
            );

            return resultDTO;
        }
        return null;
    }

    private RAAsignaturaDTO saveUpdate(RAAsignaturaDTO raPrograma, RAAsignatura raAsignaturaEntity) {
        raAsignaturaEntity.setDescripcion(raPrograma.getDescripcion());
        raAsignaturaEntity.setEstado(CompetenciaAsignatura.Status.valueOf(raPrograma.getEstado()));

        if (raPrograma.getIdCompetenciaAsignatura() != null) {
            CompetenciaAsignatura competenciaPrograma = new CompetenciaAsignatura();
            competenciaPrograma.setIdCompetenciaAsignatura(raPrograma.getIdCompetenciaAsignatura());
            raAsignaturaEntity.setCompetenciaAsignatura(competenciaPrograma);
        }

        RAAsignatura entity = raProgramaRepository.save(raAsignaturaEntity);
        return getRAAAsignaturaDTO(entity);
    }

    public boolean delete(Integer id){
        RAAsignatura raAsignatura = raProgramaRepository.findById(id).orElse(null);
        if(raAsignatura != null){
            String estado = String.valueOf(raAsignatura.getEstado());
            if(estado.equals("INACTIVO")){
                raAsignatura.setEstado(CompetenciaAsignatura.Status.ACTIVO);
            } else if (estado.equals("ACTIVO")){
                raAsignatura.setEstado(CompetenciaAsignatura.Status.INACTIVO);
            }
            raProgramaRepository.save(raAsignatura);
            return true;
        }
        return false;   
    }

    public RAAsignaturaDTO getRAAAsignaturaDTO(RAAsignatura entity){
        RAAsignaturaDTO raAsignaturaDTO = new RAAsignaturaDTO();
        raAsignaturaDTO.setId(entity.getId());
        raAsignaturaDTO.setDescripcion(entity.getDescripcion());
        raAsignaturaDTO.setIdCompetenciaAsignatura(entity.getCompetenciaAsignatura().getIdCompetenciaAsignatura());
        raAsignaturaDTO.setEstado(entity.getEstado().toString());
        return raAsignaturaDTO;
    }
}
