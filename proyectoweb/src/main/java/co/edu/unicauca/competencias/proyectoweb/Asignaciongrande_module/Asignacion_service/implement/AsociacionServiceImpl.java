package co.edu.unicauca.competencias.proyectoweb.Asignaciongrande_module.Asignacion_service.implement;

import co.edu.unicauca.competencias.proyectoweb.Asignaciongrande_module.Asignacion_core.entities.Asociar;
import co.edu.unicauca.competencias.proyectoweb.Asignaciongrande_module.Asignacion_core.repository.IAsociacionRepository;
import co.edu.unicauca.competencias.proyectoweb.Asignaciongrande_module.Asignacion_infraestructure.persistence.DTO.AsociacionDTO;
import co.edu.unicauca.competencias.proyectoweb.Asignaciongrande_module.Asignacion_service.interfaces.IAsociacionService;
import co.edu.unicauca.competencias.proyectoweb.Asignatura_module.Asignatura_core.repositories.IAsignaturaRepository;
import co.edu.unicauca.competencias.proyectoweb.CAsignatura_module.CA_core.repositories.ICompetenciaAsignaturaRepository;
import co.edu.unicauca.competencias.proyectoweb.Teacher_module.Teacher_core.repositories.ITeacherRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AsociacionServiceImpl implements IAsociacionService {
    private final IAsociacionRepository asociacionRepository;

    private final ICompetenciaAsignaturaRepository competenciaAsignaturaRepository;

    private final IAsignaturaRepository asignaturaRepository;

    private final ITeacherRepository teacherRepository;

    public AsociacionServiceImpl(IAsociacionRepository asociacionRepository, ICompetenciaAsignaturaRepository competenciaAsignaturaRepository, IAsignaturaRepository asignaturaRepository, ITeacherRepository teacherRepository) {
        this.asociacionRepository = asociacionRepository;
        this.competenciaAsignaturaRepository = competenciaAsignaturaRepository;
        this.asignaturaRepository = asignaturaRepository;
        this.teacherRepository = teacherRepository;
    }

    private List<AsociacionDTO> convertToDTO(List<Asociar> asociaciones) {
        List<AsociacionDTO> asociacionDTOList = new ArrayList<>();
        for (Asociar asociar : asociaciones) {
            AsociacionDTO asociacionDTO = new AsociacionDTO();
            asociacionDTO.setId(asociar.getId());
            asociacionDTO.setId_asignatura(asociar.getAsignatura().getId());
            asociacionDTO.setId_competencia_asignatura(asociar.getCompetenciaAsignatura().getIdCompetenciaAsignatura());
            asociacionDTO.setId_teacher(asociar.getTeacher().getId());
            asociacionDTO.setPeriodo(asociar.getPeriodo());
            asociacionDTOList.add(asociacionDTO);
        }
        return asociacionDTOList;
    }

    @Override
    public List<AsociacionDTO> findAll() {
        List<Asociar> asociaciones = asociacionRepository.findAll();
        return convertToDTO(asociaciones);
    }

    @Override
    public AsociacionDTO findById(Integer id) {
        Asociar asociar = asociacionRepository.findById(id).orElseThrow(() -> new RuntimeException("Asociacion no encontrada"));
        return convertToDTO(List.of(asociar)).get(0);
    }

    @Override
    public List<AsociacionDTO> findByIdAsignatura(Integer id_asignatura) {
        List<Asociar> asociaciones = asociacionRepository.findByAsignaturaId(id_asignatura);
        if (asociaciones.isEmpty()) {
            throw new RuntimeException("Asociaciones no encontradas");
        }
        return convertToDTO(asociaciones);
    }

    @Override
    public List<AsociacionDTO> findByIdCompetenciaAsignatura(Integer id_competencia_asignatura) {
        List<Asociar> asociaciones = asociacionRepository.findByCompetenciaAsignaturaIdCompetenciaAsignatura(id_competencia_asignatura);
        if (asociaciones.isEmpty()) {
            throw new RuntimeException("Asociaciones no encontradas");
        }
        return convertToDTO(asociaciones);
    }

    @Override
    public List<AsociacionDTO> findByIdTeacher(Integer id_teacher) {
        List<Asociar> asociaciones = asociacionRepository.findByTeacherId(id_teacher);
        if (asociaciones.isEmpty()) {
            throw new RuntimeException("Asociaciones no encontradas");
        }
        return convertToDTO(asociaciones);
    }

    @Override
    public AsociacionDTO save(AsociacionDTO asociacionDTO) {
        Asociar asociar = new Asociar();
        asociar.setId(asociacionDTO.getId());
        asociar.setAsignatura(asignaturaRepository.findById(asociacionDTO.getId_asignatura()).orElseThrow(() -> new RuntimeException("Asignatura no encontrada")));
        asociar.setCompetenciaAsignatura(competenciaAsignaturaRepository.findById(asociacionDTO.getId_competencia_asignatura()).orElseThrow(() -> new RuntimeException("CompetenciaAsignatura no encontrada")));
        asociar.setTeacher(teacherRepository.findById(asociacionDTO.getId_teacher()).orElseThrow(() -> new RuntimeException("Teacher no encontrado")));
        asociar.setPeriodo(asociacionDTO.getPeriodo());
        asociar = asociacionRepository.save(asociar);
        return convertToDTO(List.of(asociar)).get(0);
    }

    @Override
    public AsociacionDTO update(AsociacionDTO asociacionDTO) {
        Asociar asociar = asociacionRepository.findById(asociacionDTO.getId()).orElseThrow(() -> new RuntimeException("Asociacion no encontrada"));
        asociar.setAsignatura(asignaturaRepository.findById(asociacionDTO.getId_asignatura()).orElseThrow(() -> new RuntimeException("Asignatura no encontrada")));
        asociar.setCompetenciaAsignatura(competenciaAsignaturaRepository.findById(asociacionDTO.getId_competencia_asignatura()).orElseThrow(() -> new RuntimeException("CompetenciaAsignatura no encontrada")));
        asociar.setTeacher(teacherRepository.findById(asociacionDTO.getId_teacher()).orElseThrow(() -> new RuntimeException("Teacher no encontrado")));
        asociar.setPeriodo(asociacionDTO.getPeriodo());
        asociar = asociacionRepository.save(asociar);
        return convertToDTO(List.of(asociar)).get(0);
    }

    @Override
    public boolean delete(Integer id) {
        boolean exists = asociacionRepository.existsById(id);
        if (!exists) {
            throw new RuntimeException("Asociacion no encontrada");
        } else {
            asociacionRepository.deleteById(id);
            return true;
        }
    }
}
