package com.bosonit.blockcrudvalidation.entity;

import com.bosonit.blockcrudvalidation.controller.dto.Asignatura.AsignaturaInputDTO;
import com.bosonit.blockcrudvalidation.controller.dto.Asignatura.AsignaturaOutputDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "asignatura")
public class Asignatura {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer idAsignatura;
    private String asignatura;
    private String comments;
    @Column(name = "fecha_inicial", nullable = false)
    private Date initialDate;
    @Column(name = "fecha_final")
    private Date finishDate;

    @ManyToMany(mappedBy = "asignatura")
    Set<Student> student;

    public Asignatura(AsignaturaInputDTO asignaturaInputDTO){
        this.idAsignatura = asignaturaInputDTO.getIdAsignatura();
        this.asignatura = asignaturaInputDTO.getAsignatura();
        this.comments = asignaturaInputDTO.getComment();
        this.initialDate = asignaturaInputDTO.getInitialDate();
        this.finishDate = asignaturaInputDTO.getFinishDate();
    }

    public AsignaturaOutputDTO asignaturaToOutputDto(){
        return new AsignaturaOutputDTO(
                this.idAsignatura,
                this.asignatura,
                this.comments,
                this.initialDate,
                this.finishDate

        );
    }
}
