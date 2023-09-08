package com.example.block7crudvalidation.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "profesor")
public class Profesor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id_profesor;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_persona", nullable = false, unique = true)
    Persona persona;
    private String comments;

    @OneToMany(mappedBy = "profesor")
    private Set<Student> students;

}
