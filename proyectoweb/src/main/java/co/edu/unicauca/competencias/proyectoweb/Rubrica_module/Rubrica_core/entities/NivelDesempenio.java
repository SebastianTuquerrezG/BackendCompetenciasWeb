package co.edu.unicauca.competencias.proyectoweb.Rubrica_module.Rubrica_core.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Table(name = "nivel_desempenio")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class NivelDesempenio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, updatable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "criterio_evaluacion_id", nullable = false)
    private CriterioEvaluacion criterioEvaluacion;

    @Column(nullable = false, length = 100)
    @Enumerated(EnumType.STRING)
    private categoria nombre;

    @Column(nullable = false, length = 100)
    private String descripcion;

    @Column(nullable = false, length = 10)
    private String rangoNota;

    public enum categoria{
        EXCELENTE,
        BUENO,
        REGULAR,
        DEFICIENTE
    }
}
