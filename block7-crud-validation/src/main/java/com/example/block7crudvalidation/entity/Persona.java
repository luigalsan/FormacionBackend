package com.example.block7crudvalidation.entity;

import com.example.block7crudvalidation.controller.dto.Persona.PersonaInputDTO;
import com.example.block7crudvalidation.controller.dto.Persona.PersonaOutputDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "persona")
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id_persona;
    @Column(nullable = false)
    private String usuario;
    @Column(nullable = false)
    private String password;
    @Column(name = "nombre", nullable = false)
    private String name;
    @Column(name = "apellido")
    private String surname;
    @Column(name = "email_compania", nullable = false)
    private String company_email;
    @Column(name = "email_personal", nullable = false)
    private String personal_email;
    @Column(name = "ciudad", nullable = false)
    private String city;
    @Column(nullable = false)
    private boolean active;
    @Temporal(TemporalType.DATE)
    @Column(name="fecha_creacion", nullable = false)
    private Date created_date;
    private String imagen_url;
    @Temporal(TemporalType.DATE)
    @Column(name="fecha_terminacion")
    private Date termination_date;

    @OneToOne
    private Student student;

    @OneToOne
    private Profesor profesor;

    public Persona(PersonaInputDTO personaInputDTO){
        this.id_persona = personaInputDTO.getId_persona();
        this.usuario = personaInputDTO.getUsuario();
        this.password = personaInputDTO.getPassword();
        this.name = personaInputDTO.getName();
        this.surname = personaInputDTO.getSurname();
        this.company_email = personaInputDTO.getCompany_email();
        this.personal_email = personaInputDTO.getPersonal_email();
        this.city = personaInputDTO.getCity();
        this.active = personaInputDTO.isActive();
        this.created_date = personaInputDTO.getCreated_date();
        this.imagen_url = personaInputDTO.getImagen_url();
        this.termination_date = personaInputDTO.getTermination_date();

    }

    public PersonaOutputDTO personaToPersonaOutputDto(){

        return new PersonaOutputDTO(
            this.id_persona,
            this.name,
            this.surname,
            this.company_email,
            this.personal_email,
            this.city,
            this.active,
            this.created_date,
            this.imagen_url,
            this.termination_date
        );
    }
}
