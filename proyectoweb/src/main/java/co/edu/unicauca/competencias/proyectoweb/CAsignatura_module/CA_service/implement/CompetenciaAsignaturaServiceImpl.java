package co.edu.unicauca.competencias.proyectoweb.CAsignatura_module.CA_service.implement;

import co.edu.unicauca.competencias.proyectoweb.CAsignatura_module.CA_core.entities.CompetenciaAsignatura;
import co.edu.unicauca.competencias.proyectoweb.CAsignatura_module.CA_core.repositories.ICompetenciaAsignaturaRepository;
import co.edu.unicauca.competencias.proyectoweb.CAsignatura_module.CA_infrastructure.persistence.DTO.CompetenciaAsignaturaDTO;
import co.edu.unicauca.competencias.proyectoweb.CAsignatura_module.CA_service.interfaces.ICompetenciasAsignaturaServiceInt;
import co.edu.unicauca.competencias.proyectoweb.CompetenciasPrograma_module.CompetenciasPrograma_core.entities.CompetenciaPrograma;
import co.edu.unicauca.competencias.proyectoweb.CompetenciasPrograma_module.CompetenciasPrograma_core.repositories.ICompetenciasProgramaRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompetenciaAsignaturaServiceImpl implements ICompetenciasAsignaturaServiceInt {

    @Autowired
    private ICompetenciaAsignaturaRepository competenciaAsignaturaRepository;

    @Autowired
    private ICompetenciasProgramaRepository competenciasProgramaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CompetenciaAsignaturaDTO> findAll() {
        List<CompetenciaAsignatura> competenciaAsignaturaList = competenciaAsignaturaRepository.findAll();

        return competenciaAsignaturaList.stream().map(entity -> {
            CompetenciaAsignaturaDTO dto = new CompetenciaAsignaturaDTO();
            dto.setId(entity.getIdCompetenciaAsignatura());
            dto.setCompetenciaprograma_id(entity.getIdCompetenciaPrograma().getId());
            dto.setDescripcion(entity.getDescripcion());
            dto.setNivel(entity.getNivelCompetencia());
            dto.setStatus(entity.getStatus());
            return dto;
        }).collect(Collectors.toList());
    }


    @Override
    public CompetenciaAsignaturaDTO findById(Integer id) {
        CompetenciaAsignatura competenciaAsignatura = competenciaAsignaturaRepository.findById(id).orElse(null);
        if (competenciaAsignatura == null) {
            return null;
        }

        CompetenciaAsignaturaDTO competenciaAsignaturaDTO = this.modelMapper.map(competenciaAsignatura, CompetenciaAsignaturaDTO.class);
        competenciaAsignaturaDTO.setId(competenciaAsignatura.getIdCompetenciaAsignatura());
        competenciaAsignaturaDTO.setCompetenciaprograma_id(competenciaAsignatura.getIdCompetenciaPrograma().getId());

        return competenciaAsignaturaDTO;
    }


    @Override
    public List<CompetenciaAsignaturaDTO> findActives(String status) {
        List<CompetenciaAsignatura> competenciaAsignaturaList = competenciaAsignaturaRepository.findCompetenciaAsignaturasByStatus(CompetenciaAsignatura.Status.valueOf(status));

        return competenciaAsignaturaList.stream().map(entity -> {
            CompetenciaAsignaturaDTO dto = modelMapper.map(entity, CompetenciaAsignaturaDTO.class);
            dto.setId(entity.getIdCompetenciaAsignatura());
            dto.setCompetenciaprograma_id(entity.getIdCompetenciaPrograma().getId());
            return dto;
        }).collect(Collectors.toList());
    }


    @Override
    public CompetenciaAsignaturaDTO save(CompetenciaAsignaturaDTO competenciaAsignaturaDTO) {
        CompetenciaPrograma competenciaPrograma = competenciasProgramaRepository.findById(competenciaAsignaturaDTO.getCompetenciaprograma_id()).orElse(null);
        if (competenciaPrograma == null) {
            return null;
        }

        CompetenciaAsignatura entity = modelMapper.map(competenciaAsignaturaDTO, CompetenciaAsignatura.class);
        entity.setIdCompetenciaPrograma(competenciaPrograma);
        entity.setDescripcion(competenciaAsignaturaDTO.getDescripcion());
        entity.setNivelCompetencia(CompetenciaAsignatura.NivelCompetencia.valueOf(String.valueOf(competenciaAsignaturaDTO.getNivel())));
        entity.setStatus(CompetenciaAsignatura.Status.valueOf(String.valueOf(competenciaAsignaturaDTO.getStatus())));

        CompetenciaAsignatura savedEntity = competenciaAsignaturaRepository.save(entity);

        CompetenciaAsignaturaDTO savedDTO = modelMapper.map(savedEntity, CompetenciaAsignaturaDTO.class);
        savedDTO.setCompetenciaprograma_id(savedEntity.getIdCompetenciaPrograma().getId());

        return savedDTO;
    }


    @Override
    public CompetenciaAsignaturaDTO update(Integer id, CompetenciaAsignaturaDTO competenciaAsignaturaDTO) {
        CompetenciaAsignatura competenciaAsignatura = competenciaAsignaturaRepository.findById(id).orElse(null);
        if (competenciaAsignatura == null) {
            return null;
        }

        CompetenciaPrograma competenciaPrograma = competenciasProgramaRepository.findById(competenciaAsignaturaDTO.getCompetenciaprograma_id()).orElse(null);
        if (competenciaPrograma == null) {
            return null;
        }

        competenciaAsignatura.setIdCompetenciaPrograma(competenciaPrograma);
        competenciaAsignatura.setDescripcion(competenciaAsignaturaDTO.getDescripcion());
        competenciaAsignatura.setNivelCompetencia(CompetenciaAsignatura.NivelCompetencia.valueOf(String.valueOf(competenciaAsignaturaDTO.getNivel())));
        competenciaAsignatura.setStatus(CompetenciaAsignatura.Status.valueOf(String.valueOf(competenciaAsignaturaDTO.getStatus())));

        CompetenciaAsignatura updatedEntity = competenciaAsignaturaRepository.save(competenciaAsignatura);

        CompetenciaAsignaturaDTO updatedDTO = modelMapper.map(updatedEntity, CompetenciaAsignaturaDTO.class);
        updatedDTO.setCompetenciaprograma_id(updatedEntity.getIdCompetenciaPrograma().getId());

        return updatedDTO;
    }


    @Override
    public int updateByStatus(Integer id, String status) {
        CompetenciaAsignatura competenciaAsignatura = competenciaAsignaturaRepository.findById(id).orElse(null);
        if (competenciaAsignatura == null) {
            return 0;
        }
        competenciaAsignatura.setStatus(CompetenciaAsignatura.Status.valueOf(status));
        competenciaAsignaturaRepository.save(competenciaAsignatura);
        return 1;
    }
}
