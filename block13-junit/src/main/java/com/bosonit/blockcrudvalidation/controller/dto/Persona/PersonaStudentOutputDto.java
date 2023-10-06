package com.bosonit.blockcrudvalidation.controller.dto.Persona;


import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonaStudentOutputDto {

    private Integer idPersona;
    private String name;
    private String surname;
    private String companyEmail;
    private String personalEmail;
    private String city;
    private boolean active;
    private Date createdDate;
    private String imagenUrl;
    private Date terminationDate;
    private Integer idStudent;
    private int numHoursWeek;
    private String comments;
    private String branch;

}
