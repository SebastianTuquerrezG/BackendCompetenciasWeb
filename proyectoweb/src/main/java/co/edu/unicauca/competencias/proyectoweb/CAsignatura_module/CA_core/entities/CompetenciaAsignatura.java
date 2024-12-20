package co.edu.unicauca.competencias.proyectoweb.CAsignatura_module.CA_core.entities;

import co.edu.unicauca.competencias.proyectoweb.CompetenciasPrograma_module.CompetenciasPrograma_core.entities.CompetenciaPrograma;
import co.edu.unicauca.competencias.proyectoweb.RAAsignatura_module.RAAsignatura_core.entities.RAAsignatura;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

@Builder
@Data
@AllArgsConstructor(staticName = "create")
@NoArgsConstructor
@Entity
@Table(name = "COMPETENCIA_ASIGNATURA")
public class CompetenciaAsignatura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCompetenciaAsignatura;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "competencia_programa", nullable = false, foreignKey = @ForeignKey(name = "fk_competencia_programa"))
    @JsonBackReference
    private CompetenciaPrograma CompetenciaPrograma;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "CompetenciaAsignatura")
    private List<RAAsignatura> raAsignaturas;

    @Column(length = 300, nullable = false)
    private String descripcion;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private NivelCompetencia nivelCompetencia;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column(updatable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;

    @Column(updatable = true)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date updated_at;

    public enum NivelCompetencia {
        BASICO,
        INTERMEDIO,
        AVANZADO
    }

    public enum Status {
        ACTIVO,
        INACTIVO
    }
}
