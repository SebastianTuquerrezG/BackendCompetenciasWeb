package co.edu.unicauca.competencias.proyectoweb.Asignaciongrande_module.Asignacion_core.entities;

import co.edu.unicauca.competencias.proyectoweb.Asignatura_module.Asignatura_core.entities.Asignatura;
import co.edu.unicauca.competencias.proyectoweb.CAsignatura_module.CA_core.entities.CompetenciaAsignatura;
import co.edu.unicauca.competencias.proyectoweb.Teacher_module.Teacher_core.entities.Teacher;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "asociar_periodo")
public class Asociar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asociar", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_asignatura", nullable = false)
    private Asignatura asignatura;

    @ManyToOne
    @JoinColumn(name = "id_competencia_asignatura", nullable = false)
    private CompetenciaAsignatura competenciaAsignatura;

    @ManyToOne
    @JoinColumn(name = "id_teacher", nullable = false)
    private Teacher teacher;

    @Column(name = "periodo", nullable = false)
    private String periodo;

}
