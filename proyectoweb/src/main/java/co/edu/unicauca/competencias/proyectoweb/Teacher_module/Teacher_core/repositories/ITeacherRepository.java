package co.edu.unicauca.competencias.proyectoweb.Teacher_module.Teacher_core.repositories;
import co.edu.unicauca.competencias.proyectoweb.Teacher_module.Teacher_core.entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ITeacherRepository extends JpaRepository<Teacher, Integer> {
    Teacher findByNames(String names);
    boolean existsByNames(String names);
    boolean existsByIdentification(String identification);
}