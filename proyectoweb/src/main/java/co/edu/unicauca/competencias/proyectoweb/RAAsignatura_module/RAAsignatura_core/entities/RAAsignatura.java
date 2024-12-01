package co.edu.unicauca.competencias.proyectoweb.RAAsignatura_module.RAAsignatura_core.entities;

import co.edu.unicauca.competencias.proyectoweb.CAsignatura_module.CA_core.entities.CompetenciaAsignatura;
import co.edu.unicauca.competencias.proyectoweb.CAsignatura_module.CA_core.entities.CompetenciaAsignatura.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "RA_ASIGNATURA")
public class RAAsignatura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false, unique = true)
    private Integer id;

    @Column(length = 200)
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "competencia_asignatura", foreignKey = @ForeignKey(name = "fk_competencia_asignatura"))
    private CompetenciaAsignatura CompetenciaAsignatura;

    @Column(nullable = false)
    private Status estado;
}
