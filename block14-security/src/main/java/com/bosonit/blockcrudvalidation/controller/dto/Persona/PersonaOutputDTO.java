package com.bosonit.blockcrudvalidation.controller.dto.Persona;

import com.bosonit.blockcrudvalidation.entity.Role;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonaOutputDTO {

    private Integer id_persona;
    private String username;
    private String surname;
    private String company_email;
    private String personal_email;
    private String city;
    private boolean active;
    private Date created_date;
    private String imagen_url;
    private Date termination_date;
    private Role role;

}
