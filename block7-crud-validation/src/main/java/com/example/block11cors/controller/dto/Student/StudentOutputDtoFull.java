package com.example.block11cors.controller.dto.Student;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentOutputDtoFull {
    private Integer id_student;
    private int num_hours_week;
    private String comments;
    private String branch;
    private Integer id_persona;
    private String usuario;
    private String password = "secreto";
    private String name;
    private String surname;
    private String company_email;
    private String personal_email;
    private String city;
    private boolean active;
    private Date created_date;
    private String imagen_url;
    private Date termination_date;
}
