package com.bosonit.blockcrudvalidation.controller.dto.Student;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentOutputDtoFull {

    private Integer idStudent;
    private int numHoursWeek;
    private String comments;
    private String branch;
    private Integer idPersona;
    private String usuario;
    private String password = "secreto";
    private String name;
    private String surname;
    private String companyEmail;
    private String personalEmail;
    private String city;
    private boolean active;
    private Date createdDate;
    private String imagenUrl;
    private Date terminationDate;
}
