package com.example.block7crudvalidation.entity;

import com.example.block7crudvalidation.controller.dto.Student.StudentOutputDtoFull;
import com.example.block7crudvalidation.controller.dto.Student.StudentOutputDtoSimple;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id_student;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_persona", nullable = true, unique = true) //Une en relación 1:1 la columna. Si el campo que relaciona con la tabla referenciada tiene el mismo nombre, no haría falta poner el parámetro referencedColumnName
    private Persona persona;
    @Column(name= "numero_horas_semana", nullable = false)
    private int num_hours_week;
    @Column(name = "comentarios", nullable = false)
    private String comments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_profesor", nullable = true, unique = true) //Une en relación 1:1 la columna. Si el campo que relaciona con la tabla referenciada tiene el mismo nombre, no haría falta poner el parámetro referencedColumnName
    private Profesor profesor;

    @Column(nullable = false)
    private String branch;

    @OneToMany(mappedBy = "student")
    Set<Asignatura> asignaturas;

    public Student(StudentOutputDtoSimple studentInputDTO){

        this.id_student = studentInputDTO.getId_student();
        this.num_hours_week =studentInputDTO.getNum_hours_week();
        this.comments = studentInputDTO.getComments();
        this.branch = studentInputDTO.getBranch();

    }

    public StudentOutputDtoSimple toStudentOutputDtoSimple(){
        return new StudentOutputDtoSimple(
          this.id_student,
          this.num_hours_week,
          this.comments,
                this.branch,
                this.persona.getId_persona()
        );
    }

    public StudentOutputDtoFull toStudentOutputDtoFull(){
        return new StudentOutputDtoFull(
                this.id_student,
                this.num_hours_week,
                this.comments,
                this.branch,
                this.persona.getId_persona(),
                this.persona.getUsuario(),
                this.persona.getPassword(),
                this.persona.getName(),
                this.persona.getSurname(),
                this.persona.getCompany_email(),
                this.persona.getPersonal_email(),
                this.persona.getCity(),
                this.persona.isActive(),
                this.persona.getCreated_date(),
                this.persona.getImagen_url(),
                this.persona.getTermination_date()
        );
    }

}