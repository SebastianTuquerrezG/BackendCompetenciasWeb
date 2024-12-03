package co.edu.unicauca.competencias.proyectoweb.Rubrica_module.Rubrica_core.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "criterio_evaluacion")
@Entity
public class CriterioEvaluacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, updatable = false)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String descripcion;

    @Column(nullable = false)
    private Integer ponderacion;

    @ManyToOne
    @JoinColumn(name = "id_rubrica")
    private Rubrica rubrica;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private status estado;

    public enum status {
        ACTIVO,
        INACTIVO
    }
}
