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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Resultados de aprendizaje por programa
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ra_programa")
public class RAPrograma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 200)
    private String descripcion;

    // Llave foranea a competencias_programa
    @ManyToOne
    @JoinColumn(name = "id_competencia_programa", foreignKey = @ForeignKey(name = "fk_competencia_programa"))
    private CompetenciaPrograma idCompetenciaPrograma;
}
