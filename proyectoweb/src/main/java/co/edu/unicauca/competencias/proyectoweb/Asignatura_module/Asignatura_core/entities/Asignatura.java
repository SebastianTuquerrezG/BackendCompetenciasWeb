package co.edu.unicauca.competencias.proyectoweb.Asignatura_module.Asignatura_core.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor(staticName = "create")
@NoArgsConstructor
@Getter
@Setter
public class Asignatura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private Integer creditos;

    @Column(nullable = false)
    private String objetivos;

    @Column(nullable = false)
    private Integer semestre;
}
