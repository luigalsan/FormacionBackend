package com.example.block7crudvalidation.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "asignatura")
public class Asignatura {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id_asignatura;
    private String comments;
    @Column(name = "fecha_inicial", nullable = false)
    private Date initial_date;
    @Column(name = "fecha final")
    private Date finish_date;

    @OneToMany(mappedBy = "asignatura")
    private Set<Student> students;
}
