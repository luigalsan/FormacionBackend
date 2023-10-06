package com.bosonit.blockcrudvalidation.entity;

import com.bosonit.blockcrudvalidation.controller.dto.Student.StudentInputDto;
import com.bosonit.blockcrudvalidation.controller.dto.Student.StudentOutputDtoFull;
import com.bosonit.blockcrudvalidation.controller.dto.Student.StudentOutputDtoSimple;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer idStudent;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPersona", nullable = true, unique = true) //Une en relación 1:1 la columna. Si el campo que relaciona con la tabla referenciada tiene el mismo nombre, no haría falta poner el parámetro referencedColumnName
    private Persona persona;
    @Column(name= "numHoursWeek", nullable = false)
    private int numHoursWeek;
    @Column(name = "comentarios", nullable = false)
    private String comments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idProfesor") //Une en relación 1:1 la columna. Si el campo que relaciona con la tabla referenciada tiene el mismo nombre, no haría falta poner el parámetro referencedColumnName
    private Profesor profesor;

    @Column(nullable = false)
    private String branch;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "student_asignatura",
            joinColumns = @JoinColumn(name = "idStudent", referencedColumnName = "idStudent"),
            inverseJoinColumns = @JoinColumn(name = "idAsignatura", referencedColumnName = "idAsignatura"))
    Set<Asignatura> asignatura;

    public Student(StudentInputDto studentInputDTO){

        this.idStudent = studentInputDTO.getIdStudent();
        this.numHoursWeek =studentInputDTO.getNumHoursWeek();
        this.comments = studentInputDTO.getComments();
        this.branch = studentInputDTO.getBranch();

    }

    public StudentOutputDtoSimple toStudentOutputDtoSimple(){
        return new StudentOutputDtoSimple(
          this.idStudent,
          this.numHoursWeek,
          this.comments,
                this.branch,
                this.persona.getIdPersona()
        );
    }

    public StudentOutputDtoFull toStudentOutputDtoFull(){
        return new StudentOutputDtoFull(
                this.idStudent,
                this.numHoursWeek,
                this.comments,
                this.branch,
                this.persona.getIdPersona(),
                this.persona.getUsuario(),
                this.persona.getPassword(),
                this.persona.getName(),
                this.persona.getSurname(),
                this.persona.getCompanyEmail(),
                this.persona.getPersonalEmail(),
                this.persona.getCity(),
                this.persona.isActive(),
                this.persona.getCreatedDate(),
                this.persona.getImagenUrl(),
                this.persona.getTerminationDate()
        );
    }
}
