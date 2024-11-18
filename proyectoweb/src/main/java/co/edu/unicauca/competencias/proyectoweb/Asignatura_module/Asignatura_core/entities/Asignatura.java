package co.edu.unicauca.competencias.proyectoweb.Asignatura_module.Asignatura_core.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor(staticName = "create")
@NoArgsConstructor
public class Asignatura {
    private Integer id;
    private String nombre;
    private Integer creditos;
    private String objetivos;
    private Integer semestre;
    private Status status;
    private Date created_at;
    private Date updated_at;

    public enum Status {
        ACTIVO,
        INACTIVO
    }
}
