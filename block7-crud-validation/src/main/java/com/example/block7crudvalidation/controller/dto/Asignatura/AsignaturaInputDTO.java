package com.example.block7crudvalidation.controller.dto.Asignatura;


import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AsignaturaInputDTO {

    private Integer id_asignatura;
    private String asignatura;
    private String comment;
    private Date initial_date;
    private Date finish_date;

}
