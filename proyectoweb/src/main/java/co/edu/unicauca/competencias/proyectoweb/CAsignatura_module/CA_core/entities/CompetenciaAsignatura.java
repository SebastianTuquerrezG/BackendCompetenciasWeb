package co.edu.unicauca.competencias.proyectoweb.CAsignatura_module.CA_core.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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

    //@OneToOne
    //@JoinColumn(name = "id_asignatura")
    //private  IdCompetenciaPrograma;
    private NivelCompetencia nivelCompetencia;
    private Status status;
    private Date created_at;
    private Date updated_at;

    public enum NivelCompetencia {
        BAJO,
        INTERMEDIO,
        AVANZADO
    }

    public enum Status {
        ACTIVO,
        INACTIVO
    }
}
