package co.edu.unicauca.competencias.proyectoweb.Asignatura_module.Asignatura_infraestructure.persistence;

import co.edu.unicauca.competencias.proyectoweb.Asignatura_module.Asignatura_core.entities.Asignatura;
import co.edu.unicauca.competencias.proyectoweb.Asignatura_module.Asignatura_core.repositories.IAsignaturaRepository;
import co.edu.unicauca.competencias.proyectoweb.Asignatura_module.Asignatura_infraestructure.persistence.mapper.AsignaturaMapper;
import co.edu.unicauca.competencias.proyectoweb.Asignatura_module.Asignatura_infraestructure.persistence.model.AsignaturaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ORMAsignaturaPersistence implements IAsignaturaRepository{

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private final AsignaturaMapper asignaturaMapper;

    @Override
    public List<Asignatura> findAll() {
        List<AsignaturaEntity> asignaturas = entityManager.createQuery("SELECT a FROM AsignaturaEntity a", AsignaturaEntity.class).getResultList();
        return asignaturaMapper.toAsignaturas(asignaturas);
    }

    @Override
    public Asignatura findById(Integer id) {
        AsignaturaEntity asignaturaEntity = entityManager.find(AsignaturaEntity.class, id);
        if (asignaturaEntity != null) {
            return asignaturaMapper.toAsignatura(asignaturaEntity);
        }
        return null;
    }

    @Override
    public List<Asignatura> findByNombre(String nombre) {
        List<AsignaturaEntity> asignaturas = entityManager.createQuery("SELECT a FROM AsignaturaEntity a WHERE a.nombre = :nombre", AsignaturaEntity.class)
                .setParameter("nombre", nombre)
                .getResultList();
        return asignaturaMapper.toAsignaturas(asignaturas);
    }

    @Override
    public List<Asignatura> findAsignaturaByStatus(Asignatura.Status status) {
        List<AsignaturaEntity> asignaturas = entityManager.createQuery("SELECT a FROM AsignaturaEntity a WHERE a.status = :status", AsignaturaEntity.class)
                .setParameter("status", status)
                .getResultList();
        return asignaturaMapper.toAsignaturas(asignaturas);
    }

    @Override
    public Asignatura save(Asignatura asignatura) {
        AsignaturaEntity asignaturaEntity = asignaturaMapper.toAsignaturaEntity(asignatura);
        entityManager.persist(asignaturaEntity);
        return asignaturaMapper.toAsignatura(asignaturaEntity);
    }

    @Transactional
    @Override
    public Asignatura update(Asignatura asignatura) {
        AsignaturaEntity asignaturaEntity = entityManager.find(AsignaturaEntity.class, asignatura.getId());
        if (asignaturaEntity != null) {
            asignaturaEntity.setNombre(asignatura.getNombre());
            asignaturaEntity.setCreditos(asignatura.getCreditos());
            asignaturaEntity.setObjetivos(asignatura.getObjetivos());
            asignaturaEntity.setSemestre(asignatura.getSemestre());
            asignaturaEntity.setStatus(asignatura.getStatus().name());
            asignaturaEntity.setUpdated_at(new Date());

            entityManager.merge(asignaturaEntity);
            return asignaturaMapper.toAsignatura(asignaturaEntity);
        } else {
            throw new EntityNotFoundException("Asignatura no encontrada" + asignatura.getId());
        }
    }

    @Override
    public boolean desactivate(Integer id) {
        AsignaturaEntity asignaturaEntity = entityManager.find(AsignaturaEntity.class, id);
        if (asignaturaEntity != null) {
            asignaturaEntity.setStatus(Asignatura.Status.INACTIVO.name());
            entityManager.merge(asignaturaEntity);
            return true;
        }
        return false;
    }

    @Override
    public boolean activate(Integer id) {
        AsignaturaEntity asignaturaEntity = entityManager.find(AsignaturaEntity.class, id);
        if (asignaturaEntity != null) {
            asignaturaEntity.setStatus(Asignatura.Status.ACTIVO.name());
            entityManager.merge(asignaturaEntity);
            return true;
        }
        return false;
    }
}
