package com.bosonit.blockcrudvalidation.entity;

import com.bosonit.blockcrudvalidation.controller.dto.Persona.PersonaInputDTO;
import com.bosonit.blockcrudvalidation.controller.dto.Persona.PersonaOutputDTO;
import com.bosonit.blockcrudvalidation.controller.dto.Persona.PersonaProfesorOutputDto;
import com.bosonit.blockcrudvalidation.controller.dto.Persona.PersonaStudentOutputDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "persona")
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer idPersona;
    @Column(nullable = false)
    private String usuario;
    @Column(nullable = false)
    private String password;
    @Column(name = "nombre", nullable = false)
    private String name;
    @Column(name = "apellido")
    private String surname;
    @Column(name = "email_compania", nullable = false)
    private String companyEmail;
    @Column(name = "email_personal", nullable = false)
    private String personalEmail;
    @Column(name = "ciudad", nullable = false)
    private String city;
    @Column(nullable = false)
    private boolean active;
    @Temporal(TemporalType.DATE)
    @Column(name="fecha_creacion", nullable = false)
    private Date createdDate;
    private String imagenUrl;
    @Temporal(TemporalType.DATE)
    @Column(name="fecha_terminacion")
    private Date terminationDate;

    @OneToOne
    private Student student;

    @OneToOne
    private Profesor profesor;

    public Persona(PersonaInputDTO personaInputDTO){
        this.idPersona = personaInputDTO.getIdPersona();
        this.usuario = personaInputDTO.getUsuario();
        this.password = personaInputDTO.getPassword();
        this.name = personaInputDTO.getName();
        this.surname = personaInputDTO.getSurname();
        this.companyEmail = personaInputDTO.getCompanyEmail();
        this.personalEmail = personaInputDTO.getPersonalEmail();
        this.city = personaInputDTO.getCity();
        this.active = personaInputDTO.isActive();
        this.createdDate = personaInputDTO.getCreatedDate();
        this.imagenUrl = personaInputDTO.getImagenUrl();
        this.terminationDate = personaInputDTO.getTerminationDate();

    }

    public PersonaOutputDTO personaToPersonaOutputDto(){

        return new PersonaOutputDTO(
            this.idPersona,
            this.usuario,
            this.name,
            this.surname,
            this.companyEmail,
            this.personalEmail,
            this.city,
            this.active,
            this.createdDate,
            this.imagenUrl,
            this.terminationDate
        );
    }

    //Métodos para convertir los inputDtoPersona a inputDtoPersonaEstudiante o inputDtoPersonaProfesor según parámetro

    public PersonaStudentOutputDto personaToPersonaStudentDto(){
        return new PersonaStudentOutputDto(
                this.idPersona,
                this.name,
                this.surname,
                this.companyEmail,
                this.personalEmail,
                this.city,
                this.active,
                this.createdDate,
                this.imagenUrl,
                this.terminationDate,
                this.student.getIdStudent(),
                this.student.getNumHoursWeek(),
                this.student.getComments(),
                this.student.getBranch()
        );
    }

    public PersonaProfesorOutputDto personaToPersonaProfesorDto(){
        return new PersonaProfesorOutputDto(
                this.idPersona,
                this.name,
                this.surname,
                this.companyEmail,
                this.personalEmail,
                this.city,
                this.active,
                this.createdDate,
                this.imagenUrl,
                this.terminationDate,
                this.profesor.getIdProfesor(),
                this.profesor.getComments(),
                this.profesor.getBranch()
        );
    }
}
