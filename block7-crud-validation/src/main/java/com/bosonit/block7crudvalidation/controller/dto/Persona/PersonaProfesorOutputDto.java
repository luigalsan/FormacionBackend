package com.bosonit.block7crudvalidation.controller.dto.Persona;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonaProfesorOutputDto {
    private Integer id_persona;
    private String name;
    private String surname;
    private String company_email;
    private String personal_email;
    private String city;
    private boolean active;
    private Date created_date;
    private String imagen_url;
    private Date termination_date;
    private Integer id_profesor;
    private String comments;
    private String branch;
}
