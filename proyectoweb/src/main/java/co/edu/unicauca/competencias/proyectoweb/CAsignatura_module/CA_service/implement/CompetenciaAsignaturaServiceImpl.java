package co.edu.unicauca.competencias.proyectoweb.CAsignatura_module.CA_service.implement;

import co.edu.unicauca.competencias.proyectoweb.CAsignatura_module.CA_core.entities.CompetenciaAsignatura;
import co.edu.unicauca.competencias.proyectoweb.CAsignatura_module.CA_core.repositories.ICompetenciaAsignaturaRepository;
import co.edu.unicauca.competencias.proyectoweb.CAsignatura_module.CA_infrastructure.persistence.DTO.CompetenciaAsignaturaDTO;
import co.edu.unicauca.competencias.proyectoweb.CAsignatura_module.CA_service.interfaces.ICompetenciasAsignaturaServiceInt;
import co.edu.unicauca.competencias.proyectoweb.CompetenciasPrograma_module.CompetenciasPrograma_core.entities.CompetenciaPrograma;
import co.edu.unicauca.competencias.proyectoweb.CompetenciasPrograma_module.CompetenciasPrograma_core.repositories.ICompetenciasProgramaRepository;
import co.edu.unicauca.competencias.proyectoweb.RAAsignatura_module.RAAsignatura_core.entities.RAAsignatura;
import co.edu.unicauca.competencias.proyectoweb.RAAsignatura_module.RAAsignatura_infraestructure.persistence.DTO.RAAsignaturaDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompetenciaAsignaturaServiceImpl implements ICompetenciasAsignaturaServiceInt {
    private final ICompetenciaAsignaturaRepository competenciaAsignaturaRepository;

    private final ICompetenciasProgramaRepository competenciasProgramaRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public CompetenciaAsignaturaServiceImpl(ICompetenciaAsignaturaRepository competenciaAsignaturaRepository, ICompetenciasProgramaRepository competenciasProgramaRepository,@Qualifier("ca_mapper") ModelMapper modelMapper) {
        this.competenciaAsignaturaRepository = competenciaAsignaturaRepository;
        this.competenciasProgramaRepository = competenciasProgramaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CompetenciaAsignaturaDTO> findAll() {
        List<CompetenciaAsignatura> competenciaAsignaturaList = competenciaAsignaturaRepository.findAll();

        return getCompetenciaAsignaturaDTOS(competenciaAsignaturaList);
    }

    private RAAsignaturaDTO mapRAAsignatura(RAAsignatura raAsignatura) {
        RAAsignaturaDTO dto = new RAAsignaturaDTO();
        dto.setId(raAsignatura.getId());
        dto.setDescripcion(raAsignatura.getDescripcion());
        dto.setEstado(String.valueOf(raAsignatura.getEstado()));
        dto.setIdCompetenciaAsignatura(
                raAsignatura.getCompetenciaAsignatura() != null
                        ? raAsignatura.getCompetenciaAsignatura().getIdCompetenciaAsignatura()
                        : null
        );
        return dto;
    }

    @Override
    public CompetenciaAsignaturaDTO findById(Integer id) {
        CompetenciaAsignatura competenciaAsignatura = competenciaAsignaturaRepository.findById(id).orElse(null);
        if (competenciaAsignatura == null) {
            return null;
        }

        CompetenciaAsignaturaDTO dto = modelMapper.map(competenciaAsignatura, CompetenciaAsignaturaDTO.class);

        if (competenciaAsignatura.getRaAsignaturas() != null) {
            dto.setRaAsignaturas(
                    competenciaAsignatura.getRaAsignaturas().stream()
                            .map(this::mapRAAsignatura)
                            .collect(Collectors.toList())
            );
        }

        return dto;
    }


    @Override
    public List<CompetenciaAsignaturaDTO> findActives(String status) {
        List<CompetenciaAsignatura> competenciaAsignaturaList =
                competenciaAsignaturaRepository.findCompetenciaAsignaturasByStatus(CompetenciaAsignatura.Status.valueOf(status));

        return getCompetenciaAsignaturaDTOS(competenciaAsignaturaList);
    }

    private List<CompetenciaAsignaturaDTO> getCompetenciaAsignaturaDTOS(List<CompetenciaAsignatura> competenciaAsignaturaList) {
        return competenciaAsignaturaList.stream()
                .map(competenciaAsignatura -> {
                    CompetenciaAsignaturaDTO dto = modelMapper.map(competenciaAsignatura, CompetenciaAsignaturaDTO.class);

                    if (competenciaAsignatura.getRaAsignaturas() != null) {
                        dto.setRaAsignaturas(
                                competenciaAsignatura.getRaAsignaturas().stream()
                                        .map(this::mapRAAsignatura)
                                        .collect(Collectors.toList())
                        );
                    }

                    return dto;
                })
                .collect(Collectors.toList());
    }


    @Override
    public CompetenciaAsignaturaDTO save(CompetenciaAsignaturaDTO competenciaAsignaturaDTO) {
        CompetenciaPrograma competenciaPrograma = competenciasProgramaRepository.findById(competenciaAsignaturaDTO.getCompetenciaprograma()).orElse(null);
        if (competenciaPrograma == null) {
            return null;
        }

        CompetenciaAsignatura entity = modelMapper.map(competenciaAsignaturaDTO, CompetenciaAsignatura.class);
        entity.setCompetenciaPrograma(competenciaPrograma);
        entity.setDescripcion(competenciaAsignaturaDTO.getDescripcion());
        entity.setNivelCompetencia(CompetenciaAsignatura.NivelCompetencia.valueOf(competenciaAsignaturaDTO.getNivel()));
        entity.setStatus(CompetenciaAsignatura.Status.valueOf(competenciaAsignaturaDTO.getStatus()));

        CompetenciaAsignatura savedEntity = competenciaAsignaturaRepository.save(entity);

        return getCompetenciaAsignaturaDTO(savedEntity);
    }

    private CompetenciaAsignaturaDTO getCompetenciaAsignaturaDTO(CompetenciaAsignatura savedEntity) {
        CompetenciaAsignaturaDTO savedDTO = new CompetenciaAsignaturaDTO();
        savedDTO.setId(savedEntity.getIdCompetenciaAsignatura());
        savedDTO.setCompetenciaprograma(savedEntity.getCompetenciaPrograma().getId());
        savedDTO.setDescripcion(savedEntity.getDescripcion());
        savedDTO.setNivel(String.valueOf(savedEntity.getNivelCompetencia()));
        savedDTO.setStatus(String.valueOf(savedEntity.getStatus()));

        return savedDTO;
    }


    @Override
    public CompetenciaAsignaturaDTO update(Integer id, CompetenciaAsignaturaDTO competenciaAsignaturaDTO) {
        CompetenciaAsignatura competenciaAsignatura = competenciaAsignaturaRepository.findById(id).orElse(null);
        if (competenciaAsignatura == null) {
            return null;
        }

        CompetenciaPrograma competenciaPrograma = competenciasProgramaRepository.findById(competenciaAsignaturaDTO.getCompetenciaprograma()).orElse(null);
        if (competenciaPrograma == null) {
            return null;
        }

        return getCompetenciaAsignaturaDTO(competenciaAsignaturaDTO, competenciaAsignatura, competenciaPrograma);
    }

    private CompetenciaAsignaturaDTO getCompetenciaAsignaturaDTO(CompetenciaAsignaturaDTO competenciaAsignaturaDTO, CompetenciaAsignatura competenciaAsignatura, CompetenciaPrograma competenciaPrograma) {
        competenciaAsignatura.setCompetenciaPrograma(competenciaPrograma);
        competenciaAsignatura.setDescripcion(competenciaAsignaturaDTO.getDescripcion());
        competenciaAsignatura.setNivelCompetencia(CompetenciaAsignatura.NivelCompetencia.valueOf(String.valueOf(competenciaAsignaturaDTO.getNivel())));
        competenciaAsignatura.setStatus(CompetenciaAsignatura.Status.valueOf(String.valueOf(competenciaAsignaturaDTO.getStatus())));

        CompetenciaAsignatura updatedEntity = competenciaAsignaturaRepository.save(competenciaAsignatura);

        CompetenciaAsignaturaDTO updatedDTO = modelMapper.map(updatedEntity, CompetenciaAsignaturaDTO.class);
        updatedDTO.setCompetenciaprograma(updatedEntity.getCompetenciaPrograma().getId());

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
