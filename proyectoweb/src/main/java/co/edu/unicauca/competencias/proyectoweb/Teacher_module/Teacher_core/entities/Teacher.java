package co.edu.unicauca.competencias.proyectoweb.Teacher_module.Teacher_core.entities;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "teachers")
@Data
@AllArgsConstructor(staticName = "create")
@NoArgsConstructor
@Getter
@Setter
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "identification_type", length = 50, nullable = false)
    private String identificationType;

    @Column(name = "teacher_type", length = 50, nullable = false)
    private String teacherType;

    @Column(name = "names", length = 100, nullable = false)
    private String names;

    @Column(name = "last_names", length = 100, nullable = false)
    private String lastNames;

    @Column(name = "identification", length = 100, nullable = false)
    private String identification;

    @Column(name = "title", length = 100, nullable = false)
    private String title;

}
