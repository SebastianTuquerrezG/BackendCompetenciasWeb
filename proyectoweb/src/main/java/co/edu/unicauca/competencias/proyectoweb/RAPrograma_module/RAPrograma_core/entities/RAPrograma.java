package co.edu.unicauca.competencias.proyectoweb.RAPrograma_module.RAPrograma_core.entities;

import co.edu.unicauca.competencias.proyectoweb.CompetenciasPrograma_module.CompetenciasPrograma_core.entities.CompetenciaPrograma;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "RA_PROGRAMA")
public class RAPrograma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false, unique = true)
    private Integer id;

    @Column(length = 200)
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "id_competencia_programa", foreignKey = @ForeignKey(name = "fk_competencia_programa"))
    private CompetenciaPrograma idCompetenciaPrograma;

    @Column(nullable = false)
    private Integer estado;
}
