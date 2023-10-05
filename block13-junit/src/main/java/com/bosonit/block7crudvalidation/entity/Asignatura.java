package com.bosonit.block7crudvalidation.entity;

import com.bosonit.block7crudvalidation.controller.dto.Asignatura.AsignaturaInputDTO;
import com.bosonit.block7crudvalidation.controller.dto.Asignatura.AsignaturaOutputDTO;
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
    private Integer id_asignatura;
    private String asignatura;
    private String comments;
    @Column(name = "fecha_inicial", nullable = false)
    private Date initial_date;
    @Column(name = "fecha_final")
    private Date finish_date;

    @ManyToMany(mappedBy = "asignatura")
    Set<Student> student;

    public Asignatura(AsignaturaInputDTO asignaturaInputDTO){
        this.id_asignatura = asignaturaInputDTO.getId_asignatura();
        this.asignatura = asignaturaInputDTO.getAsignatura();
        this.comments = asignaturaInputDTO.getComment();
        this.initial_date = asignaturaInputDTO.getInitial_date();
        this.finish_date = asignaturaInputDTO.getFinish_date();
    }

    public AsignaturaOutputDTO asignaturaToOutputDto(){
        return new AsignaturaOutputDTO(
                this.id_asignatura,
                this.asignatura,
                this.comments,
                this.initial_date,
                this.finish_date

        );
    }
}
