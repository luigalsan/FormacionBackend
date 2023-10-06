package com.bosonit.blockcrudvalidation.entity;


import com.bosonit.blockcrudvalidation.controller.dto.Profesor.ProfesorInputDTO;
import com.bosonit.blockcrudvalidation.controller.dto.Profesor.ProfesorOutputDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "profesor")
public class Profesor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer idProfesor;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idPersona", nullable = false, unique = true)
    private Persona persona;
    @Column
    private String comments;
    private String branch;
    @OneToMany(mappedBy = "profesor")
    private Set<Student> students;


    public Profesor(ProfesorInputDTO profesorInputDTO){

        this.idProfesor = profesorInputDTO.getIdProfesor();
        this.comments = profesorInputDTO.getComments();
        this.branch = profesorInputDTO.getBranch();
    }

    public ProfesorOutputDTO profesorToOutPutDto(){

        return new ProfesorOutputDTO(
                this.idProfesor,
                this.comments,
                this.branch,
                this.persona.getIdPersona()
        );
    }

}
