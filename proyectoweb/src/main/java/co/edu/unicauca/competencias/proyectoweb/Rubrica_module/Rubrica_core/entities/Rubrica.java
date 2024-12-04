package co.edu.unicauca.competencias.proyectoweb.Rubrica_module.Rubrica_core.entities;

import co.edu.unicauca.competencias.proyectoweb.RAAsignatura_module.RAAsignatura_core.entities.RAAsignatura;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "RUBRICA")
@Entity
public class Rubrica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, updatable = false)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @OneToOne
    @JoinColumn(name = "ra_asignatura_id")
    private RAAsignatura raAsignatura;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private status estado;

    @Column(nullable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @Column(nullable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateAt;

    public enum status {
        ACTIVO,
        INACTIVO
    }
}
