package co.edu.unicauca.competencias.proyectoweb.CompetenciasPrograma_module.CompetenciasPrograma_core.entities;

import co.edu.unicauca.competencias.proyectoweb.CAsignatura_module.CA_core.entities.CompetenciaAsignatura;
import co.edu.unicauca.competencias.proyectoweb.RAPrograma_module.RAPrograma_core.entities.RAPrograma;
import co.edu.unicauca.competencias.proyectoweb.RAPrograma_module.RAPrograma_infraestructure.persistence.DTO.RAProgramaDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "COMPETENCIAS_PROGRAMA")
public class CompetenciaPrograma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 200)
    private String descripcion;

    @Enumerated(EnumType.STRING)
    private NivelCompetencia nivel; 

    @Column(nullable = false)
    private Integer estado;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "CompetenciaPrograma")
    @JsonManagedReference
    @ToString.Exclude
    private List<CompetenciaAsignatura> competenciaAsignaturaList;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "idCompetenciaPrograma")
    private List<RAPrograma> listResultadosAprendizaje;

    @Override
    public String toString() {
        return "CompetenciaPrograma{" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                ", nivel=" + nivel +
                ", estado=" + estado +
                "}";
    }
}
