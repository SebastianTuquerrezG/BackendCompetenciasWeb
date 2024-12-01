package co.edu.unicauca.competencias.proyectoweb.Teacher_module.Teacher_core.entities;

import co.edu.unicauca.competencias.proyectoweb.user_module.user_core.complements.TypeId;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;


@Entity
@Table(name = "TEACHERS")
@AllArgsConstructor(staticName = "create")
@NoArgsConstructor
@Data
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, updatable = false)
    private Integer id;

    @Column(name = "identification_type", length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    private TypeId identificationType;

    @Column(name = "teacher_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private type teacherType;

    @Column(name = "names", length = 100, nullable = false)
    private String names;

    @Column(name = "last_names", length = 100, nullable = false)
    private String lastNames;

    @Column(name = "identification", length = 100, nullable = false)
    private String identification;

    @Column(name = "title", length = 100, nullable = false)
    private String title;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private status status;

    @CreationTimestamp
    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;

    @CreationTimestamp
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated_at;

    public enum type{
        CATEDRA,
        TIEMPO_COMPLETO,
        PLANTA
    }

    public enum status{
        ACTIVO,
        INACTIVO
    }
}
