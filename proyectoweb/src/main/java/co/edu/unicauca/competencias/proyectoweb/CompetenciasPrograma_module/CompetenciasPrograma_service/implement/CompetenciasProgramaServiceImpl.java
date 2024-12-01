package co.edu.unicauca.competencias.proyectoweb.CompetenciasPrograma_module.CompetenciasPrograma_service.implement;

import java.util.List;
import java.util.stream.Collectors;

import co.edu.unicauca.competencias.proyectoweb.CAsignatura_module.CA_core.entities.CompetenciaAsignatura;
import co.edu.unicauca.competencias.proyectoweb.CAsignatura_module.CA_infrastructure.persistence.DTO.CompetenciaAsignaturaDTO;
import co.edu.unicauca.competencias.proyectoweb.RAPrograma_module.RAPrograma_core.entities.RAPrograma;
import co.edu.unicauca.competencias.proyectoweb.RAPrograma_module.RAPrograma_infraestructure.persistence.DTO.RAProgramaDTO;
import io.swagger.models.auth.In;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import co.edu.unicauca.competencias.proyectoweb.CompetenciasPrograma_module.CompetenciasPrograma_core.entities.CompetenciaPrograma;
import co.edu.unicauca.competencias.proyectoweb.CompetenciasPrograma_module.CompetenciasPrograma_core.repositories.ICompetenciasProgramaRepository;
import co.edu.unicauca.competencias.proyectoweb.CompetenciasPrograma_module.CompetenciasPrograma_infraestructure.persistence.DTO.CompetenciaProgramaDTO;
import co.edu.unicauca.competencias.proyectoweb.CompetenciasPrograma_module.CompetenciasPrograma_service.interfaces.CompetenciasProgramaServiceInt;

import static co.edu.unicauca.competencias.proyectoweb.CompetenciasPrograma_module.CompetenciasPrograma_infraestructure.persistence.mapper.CompetenciaProgramaMapper.getRaProgramaDTO;

@Service
public class CompetenciasProgramaServiceImpl implements CompetenciasProgramaServiceInt{
    private final ICompetenciasProgramaRepository competenciasProgramaRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CompetenciasProgramaServiceImpl(@Qualifier("cp_mapper") ModelMapper modelMapper, ICompetenciasProgramaRepository competenciasProgramaRepository){
        this.modelMapper = modelMapper;
        this.competenciasProgramaRepository = competenciasProgramaRepository;
    }

    @Override
    public List<CompetenciaProgramaDTO> findAll() {
        List<CompetenciaPrograma> competenciasPrograma = competenciasProgramaRepository.findAll();
        return competenciasPrograma.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private CompetenciaProgramaDTO convertToDTO(CompetenciaPrograma competenciaPrograma) {
        CompetenciaProgramaDTO dto = new CompetenciaProgramaDTO();
        dto.setId(competenciaPrograma.getId());
        dto.setDescripcion(competenciaPrograma.getDescripcion());
        dto.setNivel(String.valueOf(competenciaPrograma.getNivel()));
        dto.setEstado(competenciaPrograma.getEstado());

        if (competenciaPrograma.getCompetenciaAsignaturaList() != null) {
            dto.setCompetenciasAsignaturas(
                    competenciaPrograma.getCompetenciaAsignaturaList().stream()
                            .filter(this::isCompetenciaAsignaturaValid)
                            .map(this::convertCompetenciaAsignaturaToDTO)
                            .collect(Collectors.toList())
            );
        }

        if (competenciaPrograma.getListResultadosAprendizaje() != null) {
            dto.setListResultadosAprendizaje(
                    competenciaPrograma.getListResultadosAprendizaje().stream()
                            .filter(this::isResultadoAprendizajeValid)
                            .map(this::convertResultadoAprendizajeToDTO)
                            .collect(Collectors.toList())
            );
        }

        return dto;
    }

    private boolean isCompetenciaAsignaturaValid(CompetenciaAsignatura competenciaAsignatura) {
        if (competenciaAsignatura != null &&
                (competenciaAsignatura.getIdCompetenciaAsignatura() != null ||
                        competenciaAsignatura.getDescripcion() != null ||
                        competenciaAsignatura.getNivelCompetencia() != null)) return true;
        assert competenciaAsignatura != null;
        return competenciaAsignatura.getRaAsignaturas() != null;
    }

    private boolean isResultadoAprendizajeValid(RAPrograma raPrograma) {
        return raPrograma != null &&
                (raPrograma.getId() != null ||
                        raPrograma.getDescripcion() != null ||
                        raPrograma.getEstado() != null);
    }

    private CompetenciaAsignaturaDTO convertCompetenciaAsignaturaToDTO(CompetenciaAsignatura competenciaAsignatura) {
        CompetenciaAsignaturaDTO dto = new CompetenciaAsignaturaDTO();
        dto.setId(competenciaAsignatura.getIdCompetenciaAsignatura());
        dto.setDescripcion(competenciaAsignatura.getDescripcion());
        dto.setNivel(String.valueOf(competenciaAsignatura.getNivelCompetencia()));
        dto.setStatus(String.valueOf(competenciaAsignatura.getStatus()));

        if (competenciaAsignatura.getCompetenciaPrograma() != null) {
            CompetenciaProgramaDTO competenciaProgramaDTO = new CompetenciaProgramaDTO();
            competenciaProgramaDTO.setId(competenciaAsignatura.getCompetenciaPrograma().getId());
            dto.setCompetenciaprograma(competenciaProgramaDTO.getId());
        }

        return dto;
    }

    private RAProgramaDTO convertResultadoAprendizajeToDTO(RAPrograma raPrograma) {
        return getRaProgramaDTO(raPrograma);
    }

    @Override
    public CompetenciaProgramaDTO findById(Integer id){
        CompetenciaPrograma competenciaPrograma = competenciasProgramaRepository.findById(id).orElse(null);
        if(competenciaPrograma == null){
            return null;
        }
        return this.modelMapper.map(competenciaPrograma, CompetenciaProgramaDTO.class);
    }

    @Override
    public void save(CompetenciaProgramaDTO competenciaPrograma){
        CompetenciaPrograma competenciaProgramaEntity = this.modelMapper.map(competenciaPrograma, CompetenciaPrograma.class);

        if (competenciaProgramaEntity.getCompetenciaAsignaturaList() != null) {
            competenciaProgramaEntity.getCompetenciaAsignaturaList().forEach(asignatura -> asignatura.setCompetenciaPrograma(competenciaProgramaEntity));
        }

        if (competenciaProgramaEntity.getListResultadosAprendizaje() != null) {
            competenciaProgramaEntity.getListResultadosAprendizaje().forEach(raPrograma ->
                    raPrograma.setIdCompetenciaPrograma(competenciaProgramaEntity)
            );
        }

        competenciasProgramaRepository.save(competenciaProgramaEntity);
    }

    @Override
    public CompetenciaProgramaDTO update(CompetenciaProgramaDTO competenciaPrograma, Integer id){
        if(!competenciasProgramaRepository.existsById(id)){
            return null;
        }
        CompetenciaPrograma competenciaProgramaEntity = this.modelMapper.map(competenciaPrograma, CompetenciaPrograma.class);
        competenciaProgramaEntity.setId(id);
        competenciasProgramaRepository.save(competenciaProgramaEntity);
        return competenciaPrograma;
    }

    @Override
    public boolean delete(Integer id){
        if(competenciasProgramaRepository.existsById(id)){
            CompetenciaPrograma competenciaPrograma = competenciasProgramaRepository.findById(id).orElse(null);
            if (competenciaPrograma != null) {
                Integer state = competenciaPrograma.getEstado();
                if (state == 1) {
                    competenciaPrograma.setEstado(0);
                    competenciasProgramaRepository.save(competenciaPrograma);
                    return true;
                } else if (state == 0) {
                    competenciaPrograma.setEstado(1);
                    competenciasProgramaRepository.save(competenciaPrograma);
                    return true;
                }
                return false;
            }
        }
        return false;
    }

}
