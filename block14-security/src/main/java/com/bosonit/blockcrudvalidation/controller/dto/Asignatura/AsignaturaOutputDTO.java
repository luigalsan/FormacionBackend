package com.bosonit.blockcrudvalidation.controller.dto.Asignatura;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AsignaturaOutputDTO {

    private Integer id_asignatura;
    private String asignatura;
    private String comment;
    private Date initial_date;
    private Date finish_date;

}
