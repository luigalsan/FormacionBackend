package com.example.block11cors.entity;


import com.example.block11cors.controller.dto.Profesor.ProfesorInputDTO;
import com.example.block11cors.controller.dto.Profesor.ProfesorOutputDTO;
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
    private Integer id_profesor;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_persona", nullable = false, unique = true)
    private Persona persona;
    @Column
    private String comments;
    private String branch;
    @OneToMany(mappedBy = "profesor")
    private Set<Student> students;


    public Profesor(ProfesorInputDTO profesorInputDTO){

        this.id_profesor = profesorInputDTO.getId_profesor();
        this.comments = profesorInputDTO.getComments();
        this.branch = profesorInputDTO.getBranch();
    }

    public ProfesorOutputDTO profesorToOutPutDto(){

        return new ProfesorOutputDTO(
                this.id_profesor,
                this.comments,
                this.branch,
                this.persona.getId_persona()
        );
    }

}
