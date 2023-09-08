package com.example.block7crudvalidation.controller.dto.Student;

import com.example.block7crudvalidation.entity.Persona;
import com.example.block7crudvalidation.entity.Profesor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentInputDTO {

    private Integer id_student;
    private int num_hours_week;
    private String comments;
    private String branch;
    private Integer id_persona;


}
