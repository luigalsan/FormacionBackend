package com.bosonit.blockcrudvalidation.entity;

import com.bosonit.blockcrudvalidation.controller.dto.Persona.PersonaInputDTO;
import com.bosonit.blockcrudvalidation.controller.dto.Persona.PersonaOutputDTO;
import com.bosonit.blockcrudvalidation.controller.dto.Persona.PersonaProfesorOutputDto;
import com.bosonit.blockcrudvalidation.controller.dto.Persona.PersonaStudentOutputDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "persona", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
public class Persona implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id_persona;
    @Column(nullable = false)
    private String username;
    private String password;
    private String name;
    private String surname;
    private String company_email;
    private String personal_email;
    private String city;
    private boolean active;
    private Date created_date;
    private String imagen_url;
    private Date termination_date;
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne
    private Student student;

    @OneToOne
    private Profesor profesor;


    public Persona(PersonaInputDTO personaInputDTO){
        this.username = personaInputDTO.getUsername();
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
        this.role = personaInputDTO.getRole();

    }

    public PersonaOutputDTO personaToPersonaOutputDto(){

        return new PersonaOutputDTO(
            this.id_persona,
            this.username,
            this.password,
            this.name,
            this.surname,
            this.company_email,
            this.personal_email,
            this.city,
            this.active,
            this.created_date,
            this.imagen_url,
            this.termination_date,
                this.role
        );
    }

    //Métodos para convertir los inputDtoPersona a inputDtoPersonaEstudiante o inputDtoPersonaProfesor según parámetro

    public PersonaStudentOutputDto personaToPersonaStudentDto(){
        return new PersonaStudentOutputDto(
                this.id_persona,
                this.username,
                this.password,
                this.name,
                this.surname,
                this.company_email,
                this.personal_email,
                this.city,
                this.active,
                this.created_date,
                this.imagen_url,
                this.termination_date,
                this.student.getId_student(),
                this.student.getNum_hours_week(),
                this.student.getComments(),
                this.student.getBranch()
        );
    }

    public PersonaProfesorOutputDto personaToPersonaProfesorDto(){
        return new PersonaProfesorOutputDto(
                this.id_persona,
                this.username,
                this.password,
                this.name,
                this.surname,
                this.company_email,
                this.personal_email,
                this.city,
                this.active,
                this.created_date,
                this.imagen_url,
                this.termination_date,
                this.profesor.getId_profesor(),
                this.profesor.getComments(),
                this.profesor.getBranch()
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority((role.name())));
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }

}
