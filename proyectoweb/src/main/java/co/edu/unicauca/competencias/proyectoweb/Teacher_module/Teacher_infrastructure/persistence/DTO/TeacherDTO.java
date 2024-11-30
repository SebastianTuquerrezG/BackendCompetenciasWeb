package co.edu.unicauca.competencias.proyectoweb.Teacher_module.Teacher_infrastructure.persistence.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeacherDTO {
    private Integer id;
    private String identificationType;
    private String teacherType;
    private String names;
    private String lastNames;
    private String identification;
    private String title;
    private String status;
    private Date created_at;
    private Date updated_at;
}
